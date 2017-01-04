package fr.isima.zzoverflow

class BadgeType {

    String name

    static hasMany = [badges : Badge]

    static constraints = {
    }

    String toString(){
        return name
    }
}
