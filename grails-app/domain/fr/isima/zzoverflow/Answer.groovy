package fr.isima.zzoverflow

class Answer {

    String body
    int vote

    static belongsTo = [user : User, question : Question]

    static constraints = {
    }
}
