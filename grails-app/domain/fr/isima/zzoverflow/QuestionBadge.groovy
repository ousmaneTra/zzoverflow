package fr.isima.zzoverflow

class QuestionBadge {
    Question question
    Badge badge
    Date dateCreated

    static constraints = {
    }


	static mapping = {
		autoTimestamp true
	}

    static QuestionBadge create(Question question, Badge badge, boolean flush = false) {
		def instance = new QuestionBadge(question: question, badge: badge)
		instance.save(flush: flush)
		instance
	}
}
