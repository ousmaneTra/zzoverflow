package fr.isima.zzoverflow

class Answer extends Post{

    static belongsTo = [question : Question]

    static constraints = {
    }

    static mapping = {
    }

    String toString(){
        return  "body = " + body + " /user = " + user + "\n";
    }

}
