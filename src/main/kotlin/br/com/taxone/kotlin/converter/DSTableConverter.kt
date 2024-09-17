package br.com.taxone.kotlin.converter

import br.com.taxone.kotlin.dto.DSTableDTO
import br.com.taxone.kotlin.entity.DSTable

public class DSTableConverter {

	companion object{
		fun convert(dsTable: DSTable): DSTableDTO {
			var dDTO = DSTableDTO()
			dDTO.id = dsTable.id
			dDTO.name = dsTable.name
			return dDTO
		}
	}
	
}
