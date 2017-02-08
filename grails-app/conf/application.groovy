

// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.userLookup.userDomainClassName = 'fr.isima.zzoverflow.User'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'fr.isima.zzoverflow.UserRole'
grails.plugin.springsecurity.authority.className = 'fr.isima.zzoverflow.Role'
grails.plugin.springsecurity.controllerAnnotations.staticRules = [
	[pattern: '/',               access: ['permitAll']],
    [pattern: '/eureka/**',         access: ['permitAll']],
	[pattern: '/error',          access: ['permitAll']],
	[pattern: '/index',          access: ['permitAll']],
	[pattern: '/index.gsp',      access: ['permitAll']],
	[pattern: '/shutdown',       access: ['permitAll']],
	[pattern: '/assets/**',      access: ['permitAll']],
	[pattern: '/**/js/**',       access: ['permitAll']],
	[pattern: '/**/css/**',      access: ['permitAll']],
	[pattern: '/**/images/**',   access: ['permitAll']],
	[pattern: '/**/favicon.ico', access: ['permitAll']],
	[pattern: '/dbconsole/**', access: ['permitAll']],
	[pattern: '/register/**', access: ['permitAll']],
	[pattern: '/info', access: ['permitAll']],
	[pattern: '/health', access: ['permitAll']]
]

grails.plugin.springsecurity.filterChain.chainMap = [
	[pattern: '/assets/**',      filters: 'none'],
	[pattern: '/**/js/**',       filters: 'none'],
	[pattern: '/**/css/**',      filters: 'none'],
	[pattern: '/**/images/**',   filters: 'none'],
	[pattern: '/**/favicon.ico', filters: 'none'],
	[pattern: '/api/**',
        filters: 'JOINED_FILTERS,-exceptionTranslationFilter,-authenticationProcessingFilter,-securityContextPersistenceFilter,-rememberMeAuthenticationFilter'
    ],
	[pattern: '/**',             filters: 'JOINED_FILTERS,-restTokenValidationFilter,-restExceptionTranslationFilter']
]



grails.plugin.springsecurity.rememberMe.persistent = true
grails.plugin.springsecurity.rememberMe.persistentToken.domainClassName = 'fr.isima.zzoverflow.PersistentLogin'

grails {
   mail {
     host = "smtp.gmail.com"
     port = 465
     username = "zzoverflow@gmail.com"
     password = "isi3f2g3"
     props = ["mail.smtp.auth":"true",
              "mail.smtp.socketFactory.port":"465",
              "mail.smtp.socketFactory.class":"javax.net.ssl.SSLSocketFactory",
              "mail.smtp.socketFactory.fallback":"false"]
   }
   grails.mail.default.from="zzoverflow@gmail.com"
}

grails.plugin.springsecurity.ui.register.emailFrom = 'zzoverflow@gmail.com'
grails.plugin.springsecurity.ui.forgotPassword.emailFrom = 'zzoverflow@gmail.com'

//Actuator endpoints
server.port=8080

//Spring cloud Eureka configs
spring.application.name='zzoverflow'
eureka.client.serviceUrl.defaultZone='http://localhost:8761/eureka/'

