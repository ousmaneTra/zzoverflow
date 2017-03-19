package fr.isima.zzoverflow

import grails.transaction.Transactional

@Transactional
class QuestionService {

    public final static int MAX_TITLE_SIZE = 55

    public final static int MAX_BODY_SIZE = 140

    def getAll(params) {
        params.max   = 3
        params.sort  = "dateCreated"
        params.order = "desc"
        def questions = Question.list(params)  
        def result = questions

        result.each { question ->

            question.duration = getDiffernceInString(question.dateCreated)

            println("HB")

            if( question.title.size() > MAX_TITLE_SIZE ){
                question.title = question.title.substring(0, MAX_TITLE_SIZE) + " ..."
            }

            if( question.body.size() > MAX_BODY_SIZE ){
                question.body = question.body.substring(0, MAX_BODY_SIZE) + " ..."
            }

        }


        return result
    }


    def getAllMeta(params) {
        
        def nbComments  = [:]

        def list = Question.list(params)

        list.each { post ->
            nbComments[post.id] = post.answers.size()
            println(post.answers.size())
        }

        // Retireve the most important questions for right sidebar
        params.max   = 7 
        params.sort  = "upvote"
        params.order = "desc"
        def importantQst    = Question.list(params)

        // Retrieve Tags for right sidebar
        def totalTags     = 0
        def listTag     = Tag.list()
        listTag.sort{ a,b-> 
            b.questions.size().compareTo(a.questions.size())
        }
        listTag.each { tag ->
            totalTags += tag.questions.size()
        }

        return [    comments : nbComments, 
                    importantQst : importantQst, 
                    listTag : listTag,
                    totalTags : totalTags]
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

}
