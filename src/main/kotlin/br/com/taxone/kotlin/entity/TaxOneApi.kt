package br.com.taxone.kotlin.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class TaxOneApi {

	@Id
	@GeneratedValue
	var id: Int? = null
	var url: String? = null
	var username: String? = null
	var password: String? = null

}
