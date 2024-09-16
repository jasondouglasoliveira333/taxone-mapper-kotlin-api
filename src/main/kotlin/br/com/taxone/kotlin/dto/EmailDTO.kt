package br.com.taxone.kotlin.dto

import br.com.taxone.kotlin.enums.EmailType

class EmailDTO {
	var id : Int? = null
//		get() = this.id
//		set(value: Int?) {
//			this.id = value
//		}
		
	var email : String? = null
	var type : EmailType? = null
}
