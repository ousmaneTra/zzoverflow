package fr.isima.zzoverflow

class Question {

    String title
    String body
    int nbView
    boolean solved
    Answer correct
    Date dateCreated
    Date lastUpdated

    static hasMany = [tags : Tag, answers : Answer]

    static belongsTo = [user : User]

    static constraints = {
        correct(nullable : true)
    }

    static mapping = {
        autoTimestamp true
    }
}