package br.com.taxone.kotlin.dto;

import br.com.taxone.kotlin.enums.ScheduleLogStatus;

class ScheduleLogStatisticDTO {
	var status: ScheduleLogStatus? = null
	var quantity: Long? = null

	constructor(status: ScheduleLogStatus? = null, quantity: Long? = null){
		this.status = status
		this.quantity = quantity
	}
	
}