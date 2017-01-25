package fr.isima.zzoverflow

class Badge {

    String name
    BadgeType badgeType
    
    static hasMany   = [users : User]
    static belongsTo = [User,BadgeType]
 
    static constraints = {
    }
    
    String toString(){
        return name
    }
}
