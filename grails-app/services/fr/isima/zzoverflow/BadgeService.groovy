package fr.isima.zzoverflow

import grails.transaction.Transactional

@Transactional
class BadgeService {

    void addUserBadge(User user, String badgeName){
        def badge = Badge.findOrSaveByName(badgeName)
        UserBadge.create user,badge
    }

    void addQuestionBadge(Question question, String badgeName){
        def badge = Badge.findOrSaveByName(badgeName)
        QuestionBadge.create question,badge
    }

    void addAnswerBadge(Answer answer, String badgeName){
        def badge = Badge.findOrSaveByName(badgeName)
        AnswerBadge.create answer,badge
    }

    void processQuestionBadges(Question question){
        //View Baddges
        if(question.nbView == 3) addQuestionBadge(question,"3 views Question") 
        else if(question.nbView == 1000) addQuestionBadge(question,"Popular Question") 
        else if(question.nbView == 2500) addQuestionBadge(question,"Notable Question") 
        else if(question.nbView == 10000) addQuestionBadge(question,"Famous Question") 

        //Score Badges
        if(question.getScore() == 10) addQuestionBadge(question,"Nice Question") 
        else if(question.getScore() == 25) addQuestionBadge(question,"Good Question") 
        else if(question.getScore() == 100) addQuestionBadge(question,"Great Question")
    }

    void processAnswerBadges(Answer answer){
        if(answer.getScore() == 10) addAnswerBadge(answer,"Nice Answer") 
        else if(answer.getScore() == 25) addAnswerBadge(answer,"Good Answer") 
        else if(answer.getScore() == 100) addAnswerBadge(answer,"Great Answer") 
    }
}
