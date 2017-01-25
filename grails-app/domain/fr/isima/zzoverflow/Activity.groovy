package fr.isima.zzoverflow

class Activity {
    
    ActivityType type
    Date dateCreated
    Post post

    static belongsTo = [user : User]

    static constraints = {
        post nullable: true
    }
}
