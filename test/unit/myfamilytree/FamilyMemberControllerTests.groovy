package myfamilytree


import org.junit.*
import grails.test.mixin.*

@TestFor(FamilyMemberController)
@Mock(FamilyMember)
class FamilyMemberControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/familyMember/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.familyMemberInstanceList.size() == 0
        assert model.familyMemberInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.familyMemberInstance != null
    }

    void testSave() {
        controller.save()

        assert model.familyMemberInstance != null
        assert view == '/familyMember/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/familyMember/show/1'
        assert controller.flash.message != null
        assert FamilyMember.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/familyMember/list'

        populateValidParams(params)
        def familyMember = new FamilyMember(params)

        assert familyMember.save() != null

        params.id = familyMember.id

        def model = controller.show()

        assert model.familyMemberInstance == familyMember
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/familyMember/list'

        populateValidParams(params)
        def familyMember = new FamilyMember(params)

        assert familyMember.save() != null

        params.id = familyMember.id

        def model = controller.edit()

        assert model.familyMemberInstance == familyMember
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/familyMember/list'

        response.reset()

        populateValidParams(params)
        def familyMember = new FamilyMember(params)

        assert familyMember.save() != null

        // test invalid parameters in update
        params.id = familyMember.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/familyMember/edit"
        assert model.familyMemberInstance != null

        familyMember.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/familyMember/show/$familyMember.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        familyMember.clearErrors()

        populateValidParams(params)
        params.id = familyMember.id
        params.version = -1
        controller.update()

        assert view == "/familyMember/edit"
        assert model.familyMemberInstance != null
        assert model.familyMemberInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/familyMember/list'

        response.reset()

        populateValidParams(params)
        def familyMember = new FamilyMember(params)

        assert familyMember.save() != null
        assert FamilyMember.count() == 1

        params.id = familyMember.id

        controller.delete()

        assert FamilyMember.count() == 0
        assert FamilyMember.get(familyMember.id) == null
        assert response.redirectedUrl == '/familyMember/list'
    }
}
