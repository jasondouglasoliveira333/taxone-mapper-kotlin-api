package br.com.taxone.kotlin.converter

import java.util.stream.Collectors

import br.com.taxone.kotlin.dto.SAFXTableDTO
//import br.com.taxone.kotlin.dto.SAFXTableDetailtDTO
import br.com.taxone.kotlin.entity.DSTable
import br.com.taxone.kotlin.entity.SAFXTable

public class SAFXTableConverter {

	companion object {
	
		fun convert(safxTable: SAFXTable): SAFXTableDTO  {
			var tDTO = SAFXTableDTO()
			tDTO.id = safxTable.id
			tDTO.name = safxTable.name
			tDTO.description = safxTable.description
			var dsTable = safxTable.dsTable 
			if (dsTable != null) {
				tDTO.dsTableId = dsTable.id
				tDTO.dsTableName = dsTable.name
			}
			return tDTO
		}
	
//		fun convertWithColumns(safxTable: SAFXTable): SAFXTableDetailtDTO {
//			var tDTO = SAFXTableDetailtDTO()
//			tDTO.id(safxTable.Id())
//			tDTO.name(safxTable.Name())
//			tDTO.SafxColumns(safxTable.SafxColumns().stream().map(SAFXColumnConverter::convert).collect(Collectors.toList()))
//			return tDTO
//		}
	
		fun convertIdName(safxTable: SAFXTable): SAFXTableDTO {
			var tDTO = SAFXTableDTO()
			tDTO.id = safxTable.id
			tDTO.name = safxTable.name
			return tDTO
		}
	
		fun convertIdName(safxTable: SAFXTableDTO): SAFXTable {
			var tDTO = SAFXTable()
			tDTO.id = safxTable.id
			tDTO.name = safxTable.name
			return tDTO
		}
	}

}
