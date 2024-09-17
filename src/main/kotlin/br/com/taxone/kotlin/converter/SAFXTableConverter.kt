package br.com.taxone.kotlin.converter

import java.util.stream.Collectors

import br.com.taxone.kotlin.dto.SAFXColumnDTO
import br.com.taxone.kotlin.dto.SAFXTableDTO
import br.com.taxone.kotlin.dto.SAFXTableDetailtDTO
import br.com.taxone.kotlin.entity.DSTable
import br.com.taxone.kotlin.entity.SAFXTable
import br.com.taxone.kotlin.entity.SAFXColumn

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
	
		fun convertWithColumns(safxTable: SAFXTable): SAFXTableDetailtDTO {
			var tDTO = SAFXTableDetailtDTO()
			tDTO.id = safxTable.id
			tDTO.name = safxTable.name
			var safxColumnsDTOList = mutableListOf<SAFXColumnDTO>()
			for (safxColumn in safxTable.safxColumns as List<SAFXColumn>){
				var safxTDTO = SAFXColumnConverter.convert(safxColumn)
				safxColumnsDTOList.add(safxTDTO)
			}
			tDTO.safxColumns = safxColumnsDTOList
			return tDTO
		}
	
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
