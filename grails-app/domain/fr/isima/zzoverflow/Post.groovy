package fr.isima.zzoverflow

class Post {

    String body
    int upvote
    int downvote
    Date dateCreated
    Date lastUpdated

    static belongsTo = [user : User]
    
     static mapping = {
        tablePerHierarchy false
        autoTimestamp true
    }

    static constraints = {
        body nullable: false, type: 'text'      
    }
}
