package zzoverflow

import fr.isima.zzoverflow.*
import grails.plugin.mail.*


class BootStrap {

    def init = { servletContext ->

        new ProductAnnouncement(message: 'Launch day').save()
        new ProductAnnouncement(message: 'We keep adding features').save()

        def userRole = new Role('ROLE_USER').save()

        def me       = new User('user@me.fr', 'pass').save()

        UserRole.create me, userRole

        UserRole.withSession {
            it.flush()
            it.clear()
        }

    }
    def destroy = {
    }
}
