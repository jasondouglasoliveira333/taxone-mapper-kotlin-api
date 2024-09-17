package br.com.taxone.kotlin.dto

import lombok.Data

class SAFXTableDetailtDTO {
	var id: Int? = null
	var name: String? = null
	
	var safxColumns: List<SAFXColumnDTO>? = null

}
