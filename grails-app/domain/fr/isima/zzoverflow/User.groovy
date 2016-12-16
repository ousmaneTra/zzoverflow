package fr.isima.zzoverflow

class User {

    String username
    int reputation 

    static hasMany = [questions : Question, 
                        answers : Answer,
                        badges  : Badge]

    static belongsTo = Badge
                        

    static constraints = {
    }

    String toString(){
        return username
    }
}
