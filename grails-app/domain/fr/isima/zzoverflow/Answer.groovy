package fr.isima.zzoverflow

class Answer {

    String body
    int vote
    Date dateCreated
    Date lastUpdated

    static belongsTo = [user : User, question : Question]

    static constraints = {
    }

    static mapping = {
        autoTimestamp true
    }

    String toString(){
        return  "body = " + body + " /user = " + user + "\n";
    }

}
