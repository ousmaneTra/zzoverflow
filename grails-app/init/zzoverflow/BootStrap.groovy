package zzoverflow

import fr.isima.zzoverflow.*
import grails.plugin.mail.*

class BootStrap {

    def init = { servletContext ->
    
        def userRole = new Role('ROLE_USER').save()
        def adminRole = new Role('ROLE_ADMIN').save()
        def actuatorRole = new Role('ROLE_ACTUATOR').save() //

        def me       = new User(username: 'user@gmail.com', password: 'pass', name:'USER user').save()

        UserRole.create me, userRole

        
        def tag         = new Tag(name : 'grails').save()
        def tag2         = new Tag(name : 'groovy').save()

        def requirement = new Requirement(name :'score', value :5).save()
                            

        def badge       = new Badge(name : 'altruist').save()
   

        def user        = new User(username : 'toto', password : 'toto', name: 'To to') 
                                .save()
        UserBadge.create user, badge

        UserRole.create user, userRole
        UserRole.create user, adminRole
        UserRole.create user, actuatorRole
                        
        def user2       = new User(username : 'john', password : 'john', name: 'John Doe') 
                                .save()  
        UserBadge.create user2, badge
        UserRole.create user2, userRole


        UserRole.withSession {
            it.flush()
            it.clear()
        }
        def questionBadge1       = new Badge(name : 'Nice Question').save()
        def questionBadge2       = new Badge(name : 'Famous Question').save()  
        def question = new Question(
                                title : '10 Kids Unaware of Their Halloween Costume', 
                                body :  'Today, were looking at three particularly interesting stories. Pinterest added a new location-based feature on Wednesday that uses Place Pins as a way to map out vacations and favorite areas. Southwest Airlines is providing Wi-Fi access from gate to gate for 8 per day through an onboard hotspot. And in an effort to ramp up its user base, Google Wallet is offering a debit card that can take out cash from.', 
                                nbView : 200000, 
                                upvote : 15,
                                downvote : 0)
                                .addToTags(tag)

        question.setUser(user)   
        question.save() 

        QuestionBadge.create question, questionBadge1
        QuestionBadge.create question, questionBadge2           

        def question2 = new Question(
                                title : 'What Instagram Ads Will Look Like, What Other social networks Ads Will Look Like', 
                                body :  "Today, we're looking at three particularly interesting stories. Pinterest added a new location-based feature on Wednesday that uses Place Pins as a way to map out vacations and favorite areas. Southwest Airlines is providing Wi-Fi access from gate to gate for 8 per day through an onboard hotspot. And in an effort to ramp up its user base, Google Wallet is offering a debit card that can take out cash from.",
                                nbView : 1, 
                                upvote : 11,
                                downvote : 0)
                                .addToTags(tag2)
        question2.setUser(user) 
        question2.save() 
        QuestionBadge.create question2, questionBadge1              

        def question3 = new Question(
                                title : 'The Future of Magazines Is on Tablets', 
                                body :  " Eric Schmidt has seen the future of magazines, and it's on the tablet. At a Magazine Publishers Association. Pinterest added a new location-based feature on Wednesday that uses Place Pins as a way to map out vacations and favorite areas. Southwest Airlines is providing Wi-Fi access from gate to gate for 8 per day through an onboard hotspot. And in an effort to ramp up its user base, Google Wallet is offering a debit card that can take out cash from.",
                                nbView : 1, 
                                upvote : 0,
                                downvote : 0)
                                .addToTags(tag) 
        question3.setUser(user)
        question3.save() 
                                                                                  
        def answerBadge1       = new Badge(name : 'Great Answer').save()
        def answer   = new Answer(
                                body : 'This is the solution body !!!', 
                                upvote : 200, 
                                downvote : 5,
                                question : question)
        answer.setUser(user)                 
        answer.save() 
        question.setCorrect(answer)
        AnswerBadge.create answer, answerBadge1         

    }

    def destroy = {
    }
}
