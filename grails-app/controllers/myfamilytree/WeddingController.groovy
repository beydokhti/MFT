package myfamilytree

import org.springframework.dao.DataIntegrityViolationException

class WeddingController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [weddingInstanceList: Wedding.list(params), weddingInstanceTotal: Wedding.count()]
    }

    def create() {
        [weddingInstance: new Wedding(params)]
    }

    def save() {
        def weddingInstance = new Wedding(params)
        if (!weddingInstance.save(flush: true)) {
            render(view: "create", model: [weddingInstance: weddingInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'wedding.label', default: 'Wedding'), weddingInstance.id])
        redirect(action: "show", id: weddingInstance.id)
    }

    def show(Long id) {
        def weddingInstance = Wedding.get(id)
        if (!weddingInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'wedding.label', default: 'Wedding'), id])
            redirect(action: "list")
            return
        }

        [weddingInstance: weddingInstance]
    }

    def edit(Long id) {
        def weddingInstance = Wedding.get(id)
        if (!weddingInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'wedding.label', default: 'Wedding'), id])
            redirect(action: "list")
            return
        }

        [weddingInstance: weddingInstance]
    }

    def update(Long id, Long version) {
        def weddingInstance = Wedding.get(id)
        if (!weddingInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'wedding.label', default: 'Wedding'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (weddingInstance.version > version) {
                weddingInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'wedding.label', default: 'Wedding')] as Object[],
                        "Another user has updated this Wedding while you were editing")
                render(view: "edit", model: [weddingInstance: weddingInstance])
                return
            }
        }

        weddingInstance.properties = params

        if (!weddingInstance.save(flush: true)) {
            render(view: "edit", model: [weddingInstance: weddingInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'wedding.label', default: 'Wedding'), weddingInstance.id])
        redirect(action: "show", id: weddingInstance.id)
    }

    def delete(Long id) {
        def weddingInstance = Wedding.get(id)
        if (!weddingInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'wedding.label', default: 'Wedding'), id])
            redirect(action: "list")
            return
        }

        try {
            weddingInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'wedding.label', default: 'Wedding'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'wedding.label', default: 'Wedding'), id])
            redirect(action: "show", id: id)
        }
    }
}
