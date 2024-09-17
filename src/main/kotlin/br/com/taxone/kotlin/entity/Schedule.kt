package br.com.taxone.kotlin.entity

import java.time.LocalDateTime
import java.util.Date

import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.JoinTable
import javax.persistence.ManyToMany
import javax.persistence.ManyToOne
import javax.persistence.OneToMany

import br.com.taxone.kotlin.enums.ScheduleStatus
import lombok.Data

@Data
@Entity
public class Schedule {

	@Id
	@GeneratedValue
	var id: Int? = null 
	var name: String? = null
	@Enumerated(EnumType.STRING)
	var status: ScheduleStatus? = null
	var days: String? = null
	var hours: String? = null
	var lastExecution: LocalDateTime? = null

	@JoinTable(name = "schedule_safxtable",
		joinColumns = [JoinColumn(name = "SCHEDULE_ID", referencedColumnName = "ID")],
		inverseJoinColumns = [JoinColumn(name = "SAFX_TABLE_ID", referencedColumnName = "ID")])
	@ManyToMany
	var safxTables: List<SAFXTable>? = null

	@OneToMany(mappedBy = "schedule")
	var criterias: List<Criteria>? = null

	@ManyToOne
	var user: User? = null

	

}
