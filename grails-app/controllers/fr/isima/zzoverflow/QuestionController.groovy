package fr.isima.zzoverflow

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.plugin.springsecurity.annotation.Secured
import grails.plugin.springsecurity.SpringSecurityService

@Secured('ROLE_USER')
@Transactional(readOnly = true)
class QuestionController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def springSecurityService

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Question.list(params), model:[questionCount: Question.count()]
    }

    def show(Question question) {
        respond question
    }

    def create() {
        def question = new Question(params)
        question.user = springSecurityService.getCurrentUser()
        respond question
    }

    @Transactional
    def save() {

        def tagList = []

        if(params.tags)
            if (params.tags.class.isArray()){
                for(tagName in params.tags){
                    def tag = Tag.findOrCreateByName(tagName).save()
                    tagList << tag.id
                }
            }else{
                def tag = Tag.findOrCreateByName(params.tags).save()
                tagList << tag.id
            }

        params.tags = tagList

        def question = new Question(params)

        if (question == null) {
            print("1")
            transactionStatus.setRollbackOnly()
            notFound() 
            return
        }

        if (question.hasErrors()) {
            print("2")
            transactionStatus.setRollbackOnly()
            print(question.errors)
            respond question.errors, view:'create'
            return
        }

        print("3")
        question.save flush:true

        //Create new Activity
        new Activity(type: ActivityType.ASK_QUESTION, targetId: question.id, user: springSecurityService.getCurrentUser()).save flush:true

        print("4")
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'question.label', default: 'Question'), question.id])
                redirect question
            }
            '*' { respond question, [status: CREATED] }
        }
    }

    def edit(Question question) {
        respond question
    }

    @Transactional
    def update(Question question) {
        if (question == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (question.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond question.errors, view:'edit'
            return
        }

        question.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'question.label', default: 'Question'), question.id])
                redirect question
            }
            '*'{ respond question, [status: OK] }
        }
    }

    @Transactional
    def delete(Question question) {

        if (question == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        question.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'question.label', default: 'Question'), question.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'question.label', default: 'Question'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
