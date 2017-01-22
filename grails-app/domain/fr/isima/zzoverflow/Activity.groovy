package fr.isima.zzoverflow

class Activity {
    
    ActivityType type
    Date dateCreated
    Question question
    Answer answer

    static belongsTo = [user : User]

    static constraints = {
        question nullable: true
        answer nullable: true
    }
}
