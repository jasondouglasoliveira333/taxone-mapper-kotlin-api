package br.com.taxone.kotlin.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
public class ScheduleLogIntergrationError {

	@Id
	@GeneratedValue
	var id: Long? = null
	var numeroReg: Int? = null
	var codigoErro: String? = null
	var descricaoErro: String? = null
	var nomeCampo: String? = null
	var chaveRegistro: String? = null

	@ManyToOne
	var scheduleLog: ScheduleLog? = null

}
