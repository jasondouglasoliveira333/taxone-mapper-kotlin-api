package br.com.taxone.kotlin.dto


import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.User

import lombok.Data

class POCUser(username: String, password: String, enabled: Boolean, accountNonExpired: Boolean,
			credentialsNonExpired: Boolean, accountNonLocked: Boolean,
			 authorities: Collection<GrantedAuthority>)
	: User(username, password, enabled, accountNonExpired,
			credentialsNonExpired, accountNonLocked,
			authorities) {
	
//	constructor (id: Int, username: String, password: String, authorities: Collection<GrantedAuthority> ){
//		super(username, password, true, true, true, true, authorities)
//	}
	var id: Int? = null

//	public POCUser(Integer id, String username, String password, Collection<? extends GrantedAuthority> authorities) {
//		this(username, password, true, true, true, true, authorities)
//		this.id = id
//	}
	
//	public POCUser(String username, String password, boolean enabled, boolean accountNonExpired,
//			boolean credentialsNonExpired, boolean accountNonLocked,
//			Collection<? extends GrantedAuthority> authorities) {
//		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities)
//	}

	fun getId(): Int{
		return id as Int
	}

	fun setId(id: Int) {
		this.id = id
	}

//	@Override
//	public String toString() {
//		return "POCUser [id=" + id + "]"
//	}

	
}
