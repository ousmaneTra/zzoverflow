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
                        
        def question = new Question(
                                title : '10 Kids Unaware of Their Halloween Costume', 
                                body :  'Today, were looking at three particularly interesting stories. Pinterest added a new location-based feature on Wednesday that uses Place Pins as a way to map out vacations and favorite areas. Southwest Airlines is providing Wi-Fi access from gate to gate for 8 per day through an onboard hotspot. And in an effort to ramp up its user base, Google Wallet is offering a debit card that can take out cash from.', 
                                nbView : 1, 
                                upvote : 0,
                                downvote : 0,
                                solved : true, 
                                user : user)
                                .addToTags(tag)  

        def question2 = new Question(
                                title : 'What Instagram Ads Will Look Like, What Other social networks Ads Will Look Like', 
                                body :  "Today, we're looking at three particularly interesting stories. Pinterest added a new location-based feature on Wednesday that uses Place Pins as a way to map out vacations and favorite areas. Southwest Airlines is providing Wi-Fi access from gate to gate for 8 per day through an onboard hotspot. And in an effort to ramp up its user base, Google Wallet is offering a debit card that can take out cash from.",
                                nbView : 1, 
                                upvote : 0,
                                downvote : 0,
                                solved : false, 
                                user : user)
                                .addToTags(tag) 

        def question3 = new Question(
                                title : 'The Future of Magazines Is on Tablets', 
                                body :  " Eric Schmidt has seen the future of magazines, and it's on the tablet. At a Magazine Publishers Association. Pinterest added a new location-based feature on Wednesday that uses Place Pins as a way to map out vacations and favorite areas. Southwest Airlines is providing Wi-Fi access from gate to gate for 8 per day through an onboard hotspot. And in an effort to ramp up its user base, Google Wallet is offering a debit card that can take out cash from.",
                                nbView : 1, 
                                upvote : 0,
                                downvote : 0,
                                solved : false, 
                                user : user)
                                .addToTags(tag)                                                   

        def answer   = new Answer(
                                body : 'This is the solution body !!!', 
                                upvote : 0, 
                                downvote : 0,
                                user : user2, 
                                question : question)                        

        tag.save()
        tag2.save()
        requirement.save()
        badgeType.save()
        badge.save()
        question.save()
        question2.save()
        question3.save()
        answer.save()
        

    }

    def destroy = {
    }
}
