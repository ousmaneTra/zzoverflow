package fr.isima.zzoverflow

class Vote {

    User user 
    Post post 
    Boolean up
    Date dateCreated

    
    static mapping = {
        autoTimestamp true
    }

    static constraints = {
    }
}
