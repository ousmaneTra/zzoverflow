package fr.isima.zzoverflow

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.plugin.springsecurity.annotation.Secured
import grails.plugin.springsecurity.SpringSecurityService
import grails.converters.JSON

@Secured(['IS_AUTHENTICATED_ANONYMOUSLY']) 
@Transactional(readOnly = true)
class QuestionController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE", solved : "POST"]

    def springSecurityService
    def badgeService 
    def questionService  

    def index(Integer max) {

        // Retireve a questions list to display
        params.max   = Math.min(max ?: 10, 100)
        params.sort  = "dateCreated"
        params.order = "desc"
        def list        = []
        def duration    = [:]
        def nbComments  = [:]
        list = Question.list(params)

        list.each { post ->
            duration[post.id]   = getDiffernceInString(post.dateCreated)
            nbComments[post.id] = post.answers.size()
        }

        // Retireve the most important questions for right sidebar
        params.max   = 7 
        params.sort  = "upvote"
        params.order = "desc"
        def importantQst    = Question.list(params)

        // Retrieve Tags for right sidebar
        def occuTag     = 0
        def listTag     = Tag.list()
        listTag.sort{ a,b-> 
            b.questions.size().compareTo(a.questions.size())
        }
        listTag.each { tag ->
            occuTag += tag.questions.size()
        }

        // Get current user
        def currentUser = springSecurityService.getCurrentUser() 
        
        [questionCount: Question.count(), list : list, duration : duration, comments : nbComments, currentUser : currentUser, 
                                          listTag : listTag, occuTag : occuTag,
                                          importantQst : importantQst]
    }

    def show(Question question) {
        // Tags of right sidebar
        def occuTag     = 0
        def listTag     = Tag.list()
        listTag.sort{ a,b-> 
            b.questions.size().compareTo(a.questions.size())
        }
        listTag.each { tag ->
            occuTag += tag.questions.size()
        }

        // Retireve the most important questions for right sidebar
        params.max   = 7
        params.sort  = "upvote"
        params.order = "desc"
        def importantQst    = Question.list(params)

        // Increment nbView
        question.nbView++
 
        badgeService.processQuestionBadges(question)

        // Retrieve loggedin user
        def currentUser = springSecurityService.getCurrentUser() 
        [question : question, currentUser : currentUser, listTag : listTag, occuTag : occuTag, importantQst : importantQst ]
    }

    @Secured('ROLE_USER')
    def create() {
        def question = new Question(params)
        question.user   = springSecurityService.getCurrentUser()
        def currentUser = springSecurityService.getCurrentUser() 
        [question:question, currentUser : currentUser]
    }


    @Secured('ROLE_USER')
    @Transactional
    def save() {

        def tagList = []

        if(params.tags)
            if (params.tags.class.isArray()){
                for(tagName in params.tags){
                    def tag = Tag.findOrCreateByName(tagName.toLowerCase()).save()
                    tagList << tag.id
                }
            }else{
                def tag = Tag.findOrCreateByName(params.tags.toLowerCase()).save()
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
        new Activity(type: ActivityType.ASK_QUESTION, post: question, user: springSecurityService.getCurrentUser()).save flush:true

        print("4")
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'question.label', default: 'Question'), question.id])
                redirect question
            }
            '*' { respond question, [status: CREATED] }
        }
    }



    @Secured('ROLE_USER')
    @Transactional
    def solved() {
        println("solved")
        def question = Question.get(params.question.id)
        def previous = (question.correct ? question.correct.id : 0)
        question.correct  = Answer.get(params.answer.id)
        question.correct.user.reputation+=20
        def responseData = [
            'previous': previous,
            'current' : question.correct.id
        ]
        //Create new Activity
        new Activity(type: ActivityType.MARK_AS_SOLVED, post: question, user: springSecurityService.getCurrentUser()).save flush:true
        render responseData as JSON
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

    /** 
    * Returns differnce b/w new Date and old date as Map holding difference in years, 
    weeks, days, hrs, mins & secs 
    */ 
    public static Map getDiffernceInDates(Date oldDate, Date newDate = new Date()) { 
        Long difference = newDate.time - oldDate.time 
        Map diffMap = [:] 
        difference = difference / 1000 
        diffMap.seconds = difference % 60 
        difference = (difference - diffMap.seconds) / 60 
        diffMap.minutes = difference % 60 
        difference = (difference - diffMap.minutes) / 60 
        diffMap.hours = difference % 24 
        difference = (difference - diffMap.hours) / 24 
        diffMap.years = (difference / 365).toInteger() 
        if(diffMap.years) 
            difference = (difference) % 365 
        diffMap.days = difference % 7 
        diffMap.weeks = (difference - diffMap.days) / 7 
        return diffMap 
    } 

    public static String getDiffernceInString(Date date) {
            Map diff = getDiffernceInDates(date) 
            String result = diff.years ? diff.years + "y ago" : "" 
            if (result) 
                return result 
            result += diff.weeks ? diff.weeks + "w ago" : "" 
            if (result) 
                return result 
            result += diff.days ? diff.days + "d ago" : "" 
            if (result) 
                return result 
            result += diff.hours ? diff.hours + "h ago" : "" 
            if (result) 
                return result 
            result += diff.minutes ? diff.minutes + " min ago" : "" 
            if (result) 
                return result 
            result += diff.seconds ? diff.seconds + " sec ago" : "" 
                return result;
    }

    def getAll(Integer max) {
        def questions = questionService.getAll(params)
        JSON.use('deep')
        render questions as JSON
    }

    def getAllMeta() {
        def meta = questionService.getAllMeta(params)
        render meta as JSON
    }


    def add() {

        def params = request.JSON

        def response = questionService.createQuestion(params)
        
        render response as JSON
    }

    def getQuestion() {

        def id = params.id

        def question = questionService.getQuestion(Integer.parseInt(id))

        JSON.use('deep')

        render question as JSON
    }

    
}
