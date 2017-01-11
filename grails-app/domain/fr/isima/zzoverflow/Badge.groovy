package fr.isima.zzoverflow

class Badge {

    String name

    Requirement requirement
    
    static hasMany   = [users : User]
    static belongsTo = [badgeType : BadgeType]
 
    static constraints = {
    }
    
    String toString(){
        return name
    }
}
