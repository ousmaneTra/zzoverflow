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
        //1000 view ==> 'Popular Question'
        if(question.nbView == 3) addQuestionBadge(question,"3 views Question") 
        else if(question.nbView == 1000) addQuestionBadge(question,"Popular Question") 
        else if(question.nbView == 2500) addQuestionBadge(question,"Notable Question") 
        else if(question.nbView == 10000) addQuestionBadge(question,"Famous Question") 
    }
}
