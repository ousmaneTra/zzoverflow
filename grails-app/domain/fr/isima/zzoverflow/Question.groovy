package fr.isima.zzoverflow

class Question extends Post{

    String title
    int nbView
    boolean solved
    Answer correct

    static hasMany = [tags : Tag, answers : Answer]

    static constraints = {
        correct(nullable : true)
        tags (nullable : true)
    }

    static mapping = {
    }

    String toString(){
        return "tags  = " + tags + " /title = " + title + " / body = " + body + " /nbView = " + nbView + " /solved = " + solved + " /user = " + user + "\n";
    }

}
