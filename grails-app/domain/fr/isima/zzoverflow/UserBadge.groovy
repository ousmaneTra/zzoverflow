package fr.isima.zzoverflow

class UserBadge {
    User user
    Badge badge
    Date dateCreated

    static constraints = {
    }


	static mapping = {
		autoTimestamp true
	}

    static UserBadge create(User user, Badge badge, boolean flush = false) {
		def instance = new UserBadge(user: user, badge: badge)
		instance.save(flush: flush, insert: true)
		instance
	}
}
