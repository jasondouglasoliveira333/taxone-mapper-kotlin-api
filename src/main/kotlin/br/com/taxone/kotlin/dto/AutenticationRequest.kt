package br.com.taxone.kotlin.dto

import java.io.Serializable

class AutenticationRequest {//implements Serializable
	
	var username: String? = null
	var password: String? = null 
	
//	@Override
//	public String toString() {
//		return "JwtRequest [username=" + username + ", password=" + password + "]"
//	}
}