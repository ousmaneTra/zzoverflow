package fr.isima.zzoverflow

class Activity {
    
    ActivityType type
    Long targetId

    static belongsTo = [user : User]

    static constraints = {
    }
}
