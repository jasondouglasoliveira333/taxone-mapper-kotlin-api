package br.com.taxone.kotlin.dto;

import java.time.LocalDateTime;

import br.com.taxone.kotlin.enums.ScheduleLogStatus;

class ScheduleLogDTO {
	var id: Int? = null
	var scheduleName: String? = null
	var executionDate: LocalDateTime? = null
	var status: ScheduleLogStatus? = null

}
