package fr.isima.zzoverflow

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.plugin.springsecurity.annotation.Secured

@Secured(['IS_AUTHENTICATED_ANONYMOUSLY']) 
@Transactional(readOnly = true)
class VoteController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def springSecurityService

    def badgeService 

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Vote.list(params), model:[voteCount: Vote.count()]
    }

    def show(Vote vote) {
        respond vote
    }

    def create() {
        respond new Vote(params)
    }

    @Secured('ROLE_USER')
    @Transactional
    def save() {
        println(params)
        def vote = Vote.findByUserAndPost(User.get(params.user.id), Post.get(params.post.id))
        def post = Post.get(params.post.id)
        def activity = new Activity()
        if(!vote){
            if(params.vote == '1'){
                params.up = true
                post.upvote++
                post.user.reputation+=15
                
            }
            else {
                params.up = false
                post.downvote--
            }
            //Create new Activity
            activity.post = post 
            activity.user = springSecurityService.getCurrentUser()

            vote = new Vote(params)
            vote.save flush:true
            if(post instanceof Answer){
                badgeService.processAnswerBadges(post)
                (params.vote == '1' ? activity.setType(ActivityType.UPVOTE_ANSWER) : activity.setType(ActivityType.DOWNVOTE_ANSWER) )
            }
            else if (post instanceof Question){
                badgeService.processQuestionBadges(post)
                (params.vote == '1' ? activity.setType(ActivityType.UPVOTE_QUESTION) : activity.setType(ActivityType.DOWNVOTE_QUESTION) )
            }
            activity.save flush:true

        }
        render (params.vote == '1' ? post.upvote : post.downvote )
    }


    def process() {
        def params = request.JSON
        println(params)
        def vote = Vote.findByUserAndPost(User.get(params.user), Post.get(params.post))
        def post = Post.get(params.post)
        def activity = new Activity()
        if(!vote){
            if(params.vote == '1'){
                params.up = true
                post.upvote++
                post.user.reputation+=15
                
            }
            else {
                params.up = false
                post.downvote--
            }
            //Create new Activity
            activity.post = post 
            activity.user = springSecurityService.getCurrentUser()

            vote = new Vote(params)
            vote.save flush:true
            if(post instanceof Answer){
                badgeService.processAnswerBadges(post)
                (params.vote == '1' ? activity.setType(ActivityType.UPVOTE_ANSWER) : activity.setType(ActivityType.DOWNVOTE_ANSWER) )
            }
            else if (post instanceof Question){
                badgeService.processQuestionBadges(post)
                (params.vote == '1' ? activity.setType(ActivityType.UPVOTE_QUESTION) : activity.setType(ActivityType.DOWNVOTE_QUESTION) )
            }
            activity.save flush:true

        }
        render (params.vote == '1' ? post.upvote : post.downvote )
    }


    def edit(Vote vote) {
        respond vote
    }

    @Transactional
    def update(Vote vote) {
        if (vote == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (vote.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond vote.errors, view:'edit'
            return
        }

        vote.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'vote.label', default: 'Vote'), vote.id])
                redirect vote
            }
            '*'{ respond vote, [status: OK] }
        }
    }

    @Transactional
    def delete(Vote vote) {

        if (vote == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        vote.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'vote.label', default: 'Vote'), vote.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'vote.label', default: 'Vote'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
