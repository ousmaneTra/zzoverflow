package fr.isima.zzoverflow

class Activity {
    
    ActivityType type
    Long targetId
    Date dateCreated

    static belongsTo = [user : User]

    static constraints = {
    }
}
