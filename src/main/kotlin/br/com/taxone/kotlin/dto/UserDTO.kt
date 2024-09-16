package br.com.lkm.taxone.mapper.dto;

import java.time.LocalDateTime

import org.springframework.format.annotation.DateTimeFormat

class UserDTO {
	var id: Int? = null
	var name: String? = null
	var password: String? = null
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) 
	var creationDate: LocalDateTime? = null
}
