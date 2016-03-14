package myfamilytree


import org.junit.*
import grails.test.mixin.*

@TestFor(WeddingController)
@Mock(Wedding)
class WeddingControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/wedding/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.weddingInstanceList.size() == 0
        assert model.weddingInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.weddingInstance != null
    }

    void testSave() {
        controller.save()

        assert model.weddingInstance != null
        assert view == '/wedding/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/wedding/show/1'
        assert controller.flash.message != null
        assert Wedding.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/wedding/list'

        populateValidParams(params)
        def wedding = new Wedding(params)

        assert wedding.save() != null

        params.id = wedding.id

        def model = controller.show()

        assert model.weddingInstance == wedding
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/wedding/list'

        populateValidParams(params)
        def wedding = new Wedding(params)

        assert wedding.save() != null

        params.id = wedding.id

        def model = controller.edit()

        assert model.weddingInstance == wedding
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/wedding/list'

        response.reset()

        populateValidParams(params)
        def wedding = new Wedding(params)

        assert wedding.save() != null

        // test invalid parameters in update
        params.id = wedding.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/wedding/edit"
        assert model.weddingInstance != null

        wedding.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/wedding/show/$wedding.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        wedding.clearErrors()

        populateValidParams(params)
        params.id = wedding.id
        params.version = -1
        controller.update()

        assert view == "/wedding/edit"
        assert model.weddingInstance != null
        assert model.weddingInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/wedding/list'

        response.reset()

        populateValidParams(params)
        def wedding = new Wedding(params)

        assert wedding.save() != null
        assert Wedding.count() == 1

        params.id = wedding.id

        controller.delete()

        assert Wedding.count() == 0
        assert Wedding.get(wedding.id) == null
        assert response.redirectedUrl == '/wedding/list'
    }
}
