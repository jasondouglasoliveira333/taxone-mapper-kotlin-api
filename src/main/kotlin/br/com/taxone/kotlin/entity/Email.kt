package br.com.taxone.kotlin.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

import br.com.taxone.kotlin.enums.EmailType

@Entity
@Table(name = "email")
class Email {

	@Id
	@GeneratedValue
	var id : kotlin.Int? = 0
	var email : String? = null
	var type : EmailType? = null
}
