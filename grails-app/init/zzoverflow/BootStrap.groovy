package zzoverflow

import fr.isima.zzoverflow.*

class BootStrap {

    def init = { servletContext ->

        def tag         = new Tag(name : 'grails')

        def requirement = new Requirement(name :'score', value :5)
        

        def badgeType   = new BadgeType(name :'question')
                            

        def badge       = new Badge(name : 'altruist', requirement : requirement, badgeType : badgeType )
                        

        def user        = new User(username : 'toto', reputation : 5) 
                                .addToBadges(badge)
                        
        def user2       = new User(username : 'john', reputation : 20) 
                                .addToBadges(badge)
                        
        def question = new Question(title : 'first asked question', body : 'I need help !', nbView : 1, solved : false, user : user)
                                .addToTags(tag)                    

        def answer   = new Answer(body : 'This is the solution body !!!', vote : 0, user : user2, question : question)                        

        tag.save()
        requirement.save()
        badgeType.save()
        badge.save()
        user.save()
        user2.save()
        question.save()
        answer.save()

    }

    def destroy = {
    }
}
