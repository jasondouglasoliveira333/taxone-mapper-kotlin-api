package br.com.taxone.kotlin.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
class Criteria {
	
	@Id
	@GeneratedValue
	var id: Int? = null
	var operator: String? = null
	var value: String? = null
	var additionalValue: String? = null
	
	@ManyToOne
	var safxColumn: SAFXColumn? = null
	
	@ManyToOne
	var schedule: Schedule? = null

}
