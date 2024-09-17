package br.com.taxone.kotlin.entity

import java.time.LocalDateTime

import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToOne
import javax.persistence.OneToMany

import br.com.taxone.kotlin.enums.ScheduleLogStatus
import lombok.Data

@Entity
class ScheduleLog {

	@Id
	@GeneratedValue
	var id: Int? = null
	var numLote: String? = null
	var executionDate: LocalDateTime? = null
	var errorMessage: String? = null
	@Enumerated(EnumType.STRING)
	var status: ScheduleLogStatus? = null
	var integrationStatus: IntegrationStatus? = null
	
	@ManyToOne
	var schedule: Schedule? = null
	
	@OneToMany(mappedBy = "scheduleLog")
	var taxOneErrors: List<ScheduleLogIntergrationError>? = null
	

}
