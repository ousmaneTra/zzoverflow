package fr.isima.zzoverflow

class Question {

    String title
    String body
    int nbView
    boolean solved
    Answer correct

    static hasMany = [tags : Tag, answers : Answer]

    static belongsTo = [user : User]

    static constraints = {
    }
}
