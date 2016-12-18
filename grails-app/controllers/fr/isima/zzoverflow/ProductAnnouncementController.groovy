package fr.isima.zzoverflow

import grails.plugin.springsecurity.annotation.Secured

class ProductAnnouncementController {


    @Secured('ROLE_USER')
    def index() { 

        def announcements = ProductAnnouncement.createCriteria().list {
            order("dateCreated", "desc")
            maxResults(1)
        }
        render announcements.first()?.message
    }
}
