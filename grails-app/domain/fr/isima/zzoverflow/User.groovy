package fr.isima.zzoverflow

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includes='username')
@ToString(includes='username', includeNames=true, includePackage=false)
class User implements Serializable {

	private static final long serialVersionUID = 1

	transient springSecurityService

	String username
	String password
	boolean enabled = true
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired
	String name
	String aboutMe
	int reputation
	Date dateCreated
	Date lastUpdated
	byte[] avatar
  	String avatarType

	/*User(String username) {
		this()
		this.username = username  
		this.name = username
	}*/

	User(String username, String password) {
		this()
		this.username = username
		this.password = password
	}
	
	/*
	User(String username, String password, String name) {
		this()
		this.username = username
		this.password = password
		this.name = name
	}*/

	static mapping = {
		password column: '`password`'
		autoTimestamp true
	}

	static hasMany = [posts : Post, 
                        badges  : UserBadge,
						activities: Activity]

	//static belongsTo = Badge

	Set<Role> getAuthorities() {
		UserRole.findAllByUser(this)*.role
	}

	def beforeInsert() {
		encodePassword()
	}

	def beforeUpdate() {
		if (isDirty('password')) {
			encodePassword()
		}
	}

	protected void encodePassword() {
		password = springSecurityService?.passwordEncoder ? springSecurityService.encodePassword(password) : password
	}

	static transients = ['springSecurityService']

	static constraints = {
		username blank: false, unique: true
		password blank: false
		name nullable: true
		aboutMe nullable: true
		avatar nullable:true, maxSize: 16384
    	avatarType nullable:true
	}

}
