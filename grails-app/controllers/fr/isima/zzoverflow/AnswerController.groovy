package fr.isima.zzoverflow

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.plugin.springsecurity.annotation.Secured
import grails.plugin.springsecurity.SpringSecurityService

@Secured(['IS_AUTHENTICATED_ANONYMOUSLY']) 
@Transactional(readOnly = true)
class AnswerController {

    static allowedMethods = [save: ["POST","GET"], update: "PUT", delete: "DELETE", process:"POST"]

    def springSecurityService

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Answer.list(params), model:[answerCount: Answer.count()]
    }

    def show(Answer answer) {
        respond answer
    }

    def create() {
        respond new Answer(params)
    }

 
    @Secured("ROLE_USER")
    @Transactional
    def save() {

        def answer
        if(session.body && session.idQst){
            answer = new Answer(
                                body : session.body, 
                                upvote : 0, 
                                downvote : 0,
                                user : springSecurityService.getCurrentUser(), 
                                question : session.question) 
        }

        println(answer)

        if (answer == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (answer.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond answer.errors, view:'create'
            return
        }

        answer.save flush:true

        //Create new Activity
        new Activity(type: ActivityType.ANSWER_QUESTION, answer: answer, user: springSecurityService.getCurrentUser()).save flush:true

        redirect(controller : "question", action : "show", id : answer.question.id )
          
    }

    def process() {

        if(params.body && params.question.id){
            session["body"]     = params.body
            session["idQst"]    = params.question.id
            session["question"] = params.question
            println(session)
        }
        redirect(action : "save")

    }

    

    def edit(Answer answer) {
        respond answer
    }

    @Transactional
    def update(Answer answer) {

        if (answer == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (answer.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond answer.errors, view:'edit'
            return
        }

        answer.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'answer.label', default: 'Answer'), answer.id])
                redirect answer
            }
            '*'{ respond answer, [status: OK] }
        }
    }

    @Transactional
    def delete(Answer answer) {

        if (answer == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        answer.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'answer.label', default: 'Answer'), answer.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'answer.label', default: 'Answer'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
