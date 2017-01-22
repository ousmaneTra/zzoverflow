package fr.isima.zzoverflow

class Question {

    String title
    String body
    int nbView
    int upvote
    int downvote
    boolean solved
    Answer correct
    Date dateCreated
    Date lastUpdated

    static hasMany = [tags : Tag, answers : Answer]

    static belongsTo = [user : User]

    static constraints = {
        correct(nullable : true)
        tags (nullable : true)
    }

    static mapping = {
        autoTimestamp true
        body type: 'text'
    }

    String toString(){
        return "tags  = " + tags + " /title = " + title + " / body = " + body + " /nbView = " + nbView + " /solved = " + solved + " /user = " + user + "\n";
    }

}
