package fr.isima.zzoverflow

import grails.test.mixin.*
import spock.lang.*

@TestFor(RequirementController)
@Mock(Requirement)
class RequirementControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null

        //Populate valid properties like...
        //params["name"] = 'someValidName'
        params["name"] = 'Vue'
		params["value"] = '100'
    }

    void "Test the index action returns the correct model"() {

        when:"The index action is executed"
            controller.index()

        then:"The model is correct"
            !model.requirementList
            model.requirementCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.requirement!= null
    }

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'POST'
            def requirement = new Requirement()
            requirement.validate()
            controller.save(requirement)

        then:"The create view is rendered again with the correct model"
            model.requirement!= null
            view == 'create'

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            requirement = new Requirement(params)

            controller.save(requirement)

        then:"A redirect is issued to the show action"
            response.redirectedUrl == '/requirement/show/1'
            controller.flash.message != null
            Requirement.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def requirement = new Requirement(params)
            controller.show(requirement)

        then:"A model is populated containing the domain instance"
            model.requirement == requirement
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def requirement = new Requirement(params)
            controller.edit(requirement)

        then:"A model is populated containing the domain instance"
            model.requirement == requirement
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'PUT'
            controller.update(null)

        then:"A 404 error is returned"
            response.redirectedUrl == '/requirement/index'
            flash.message != null

        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def requirement = new Requirement()
            requirement.validate()
            controller.update(requirement)

        then:"The edit view is rendered again with the invalid instance"
            view == 'edit'
            model.requirement == requirement

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            requirement = new Requirement(params).save(flush: true)
            controller.update(requirement)

        then:"A redirect is issued to the show action"
            requirement != null
            response.redirectedUrl == "/requirement/show/$requirement.id"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'DELETE'
            controller.delete(null)

        then:"A 404 is returned"
            response.redirectedUrl == '/requirement/index'
            flash.message != null

        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def requirement = new Requirement(params).save(flush: true)

        then:"It exists"
            Requirement.count() == 1

        when:"The domain instance is passed to the delete action"
            controller.delete(requirement)

        then:"The instance is deleted"
            Requirement.count() == 0
            response.redirectedUrl == '/requirement/index'
            flash.message != null
    }
}
