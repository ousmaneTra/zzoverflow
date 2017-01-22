package fr.isima.zzoverflow

class Answer {

    String body
    int upvote
    int downvote
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
