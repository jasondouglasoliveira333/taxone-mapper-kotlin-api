package br.com.taxone.kotlin.dto

import br.com.taxone.kotlin.enums.ColumnType
import lombok.Data

@Data
public class DSColumnDTO {
	var id: Int? = null
	var name: String? = null
	var columnType: ColumnType? = null
	var size: Int? = null
	var dsTable: DSTableDTO? = null
}
