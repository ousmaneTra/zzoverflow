package fr.isima.zzoverflow

class Answer extends Post{

    static hasMany = [badges: AnswerBadge]

    static belongsTo = [question : Question]

    static constraints = {
    }

    static mapping = {
    }

    String toString(){
        return  "body = " + body + " /user = " + user + "\n";
    }

}
