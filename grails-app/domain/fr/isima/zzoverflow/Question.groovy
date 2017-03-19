package fr.isima.zzoverflow

class Question extends Post{

    String title
    int nbView
    Answer correct
    String duration


    static hasMany = [tags : Tag, answers : Answer,badges: QuestionBadge]

    static constraints = {
        correct(nullable : true)
        tags (nullable : true)
        duration (nullable : true)
    }

    static mapping = {
    }

    String toString(){
        return "tags  = " + tags + " /title = " + title + " / body = " + body + " /nbView = " + nbView  + " /user = " + user + "\n";
    }

}
