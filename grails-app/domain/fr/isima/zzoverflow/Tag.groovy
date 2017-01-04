package fr.isima.zzoverflow

class Tag {

    String name

    static hasMany = [questions : Question]
    static belongsTo = Question

    static constraints = {
    }

    String toString(){
        return name
    }
}
