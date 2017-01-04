package fr.isima.zzoverflow

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class BadgeTypeController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond BadgeType.list(params), model:[badgeTypeCount: BadgeType.count()]
    }

    def show(BadgeType badgeType) {
        respond badgeType
    }

    def create() {
        respond new BadgeType(params)
    }

    @Transactional
    def save(BadgeType badgeType) {
        if (badgeType == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (badgeType.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond badgeType.errors, view:'create'
            return
        }

        badgeType.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'badgeType.label', default: 'BadgeType'), badgeType.id])
                redirect badgeType
            }
            '*' { respond badgeType, [status: CREATED] }
        }
    }

    def edit(BadgeType badgeType) {
        respond badgeType
    }

    @Transactional
    def update(BadgeType badgeType) {
        if (badgeType == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (badgeType.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond badgeType.errors, view:'edit'
            return
        }

        badgeType.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'badgeType.label', default: 'BadgeType'), badgeType.id])
                redirect badgeType
            }
            '*'{ respond badgeType, [status: OK] }
        }
    }

    @Transactional
    def delete(BadgeType badgeType) {

        if (badgeType == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        badgeType.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'badgeType.label', default: 'BadgeType'), badgeType.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'badgeType.label', default: 'BadgeType'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
