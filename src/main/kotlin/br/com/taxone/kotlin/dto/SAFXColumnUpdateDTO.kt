package br.com.taxone.kotlin.dto

import br.com.taxone.kotlin.enums.ColumnType
import lombok.Data

@Data
class SAFXColumnUpdateDTO {

	var id: Int? = null
	var name: String? = null
	var columnType: ColumnType? = null
	var required: Boolean? = null
	var position: Int? = null
	var size: Int? = null
	
	var dsColumnId: Int? = null
	var dsColumnName: String? = null
	
}
