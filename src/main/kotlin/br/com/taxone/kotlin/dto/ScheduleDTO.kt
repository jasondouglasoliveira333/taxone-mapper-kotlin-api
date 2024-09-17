package br.com.taxone.kotlin.dto

import br.com.taxone.kotlin.enums.ScheduleStatus
import lombok.Data

class ScheduleDTO {
	var id: Int? = null
	var name: String? = null
	var days: String? = null
	var hours: String? = null
	var safxTables: List<SAFXTableDTO>? = null
	var criterias: List<CriteriaDTO>? = null
	var userName: String? = null
	var status: ScheduleStatus? = null
}
