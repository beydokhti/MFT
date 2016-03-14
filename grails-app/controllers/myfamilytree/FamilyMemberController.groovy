package myfamilytree

import org.springframework.dao.DataIntegrityViolationException

class FamilyMemberController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [familyMemberInstanceList: FamilyMember.list(params), familyMemberInstanceTotal: FamilyMember.count()]
    }

    def create() {
        [familyMemberInstance: new FamilyMember(params)]
    }

    def save() {
        def familyMemberInstance = new FamilyMember(params)
        if (!familyMemberInstance.save(flush: true)) {
            render(view: "create", model: [familyMemberInstance: familyMemberInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'familyMember.label', default: 'FamilyMember'), familyMemberInstance.id])
        redirect(action: "show", id: familyMemberInstance.id)
    }

    def show(Long id) {
        def familyMemberInstance = FamilyMember.get(id)
        if (!familyMemberInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'familyMember.label', default: 'FamilyMember'), id])
            redirect(action: "list")
            return
        }

        [familyMemberInstance: familyMemberInstance]
    }

    def edit(Long id) {
        def familyMemberInstance = FamilyMember.get(id)
        if (!familyMemberInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'familyMember.label', default: 'FamilyMember'), id])
            redirect(action: "list")
            return
        }

        [familyMemberInstance: familyMemberInstance]
    }

    def update(Long id, Long version) {
        def familyMemberInstance = FamilyMember.get(id)
        if (!familyMemberInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'familyMember.label', default: 'FamilyMember'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (familyMemberInstance.version > version) {
                familyMemberInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'familyMember.label', default: 'FamilyMember')] as Object[],
                          "Another user has updated this FamilyMember while you were editing")
                render(view: "edit", model: [familyMemberInstance: familyMemberInstance])
                return
            }
        }

        familyMemberInstance.properties = params

        if (!familyMemberInstance.save(flush: true)) {
            render(view: "edit", model: [familyMemberInstance: familyMemberInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'familyMember.label', default: 'FamilyMember'), familyMemberInstance.id])
        redirect(action: "show", id: familyMemberInstance.id)
    }

    def delete(Long id) {
        def familyMemberInstance = FamilyMember.get(id)
        if (!familyMemberInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'familyMember.label', default: 'FamilyMember'), id])
            redirect(action: "list")
            return
        }

        try {
            familyMemberInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'familyMember.label', default: 'FamilyMember'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'familyMember.label', default: 'FamilyMember'), id])
            redirect(action: "show", id: id)
        }
    }
}
