package zzoverflow

import fr.isima.zzoverflow.*
import grails.plugin.mail.*

class BootStrap {

    def init = { servletContext ->

        def userRole = new Role('ROLE_USER').save()

        def me       = new User(username: 'user@gmail.com', password: 'pass', name:'USER user').save()

        UserRole.create me, userRole

        
        def tag         = new Tag(name : 'grails')
        def tag2         = new Tag(name : 'groovy')

        def requirement = new Requirement(name :'score', value :5)
        

        def badgeType   = new BadgeType(name :'question')
                            

        def badge       = new Badge(name : 'altruist', requirement : requirement, badgeType : badgeType )
                        

        def user        = new User(username : 'toto', password : 'toto', name: 'To to') 
                                .addToBadges(badge)
                                .save()

        UserRole.create user, userRole
                        
        def user2       = new User(username : 'john', password : 'john', name: 'John Doe') 
                                .addToBadges(badge)
                                .save()  

        UserRole.create user2, userRole


        UserRole.withSession {
            it.flush()
            it.clear()
        }
                        
        def question = new Question(title : 'first asked question', body : 'I need help !', nbView : 1, solved : false, user : user)
                                .addToTags(tag)                    

        def answer   = new Answer(body : 'This is the solution body !!!', vote : 0, user : user2, question : question)                        

        tag.save()
        tag2.save()
        requirement.save()
        badgeType.save()
        badge.save()
        question.save()
        answer.save()
        

    }

    def destroy = {
    }
}
