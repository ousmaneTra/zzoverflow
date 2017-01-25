package fr.isima.zzoverflow

class AnswerBadge {
    Answer answer
    Badge badge
    Date dateCreated

    static constraints = {
    }


	static mapping = {
		autoTimestamp true
	}

    static AnswerBadge create(Answer answer, Badge badge, boolean flush = false) {
		def instance = new AnswerBadge(answer: answer, badge: badge)
		instance.save(flush: flush, insert: true)
		instance
	}
}
