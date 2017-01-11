package fr.isima.zzoverflow

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.plugin.springsecurity.annotation.Secured

@Secured('ROLE_USER')
@Transactional(readOnly = true)
class RequirementController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Requirement.list(params), model:[requirementCount: Requirement.count()]
    }

    def show(Requirement requirement) {
        respond requirement
    }

    def create() {
        respond new Requirement(params)
    }

    @Transactional
    def save(Requirement requirement) {
        if (requirement == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (requirement.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond requirement.errors, view:'create'
            return
        }

        requirement.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'requirement.label', default: 'Requirement'), requirement.id])
                redirect requirement
            }
            '*' { respond requirement, [status: CREATED] }
        }
    }

    def edit(Requirement requirement) {
        respond requirement
    }

    @Transactional
    def update(Requirement requirement) {
        if (requirement == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (requirement.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond requirement.errors, view:'edit'
            return
        }

        requirement.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'requirement.label', default: 'Requirement'), requirement.id])
                redirect requirement
            }
            '*'{ respond requirement, [status: OK] }
        }
    }

    @Transactional
    def delete(Requirement requirement) {

        if (requirement == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        requirement.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'requirement.label', default: 'Requirement'), requirement.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'requirement.label', default: 'Requirement'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
