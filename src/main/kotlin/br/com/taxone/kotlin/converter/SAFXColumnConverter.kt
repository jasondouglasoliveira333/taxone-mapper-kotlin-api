package br.com.taxone.kotlin.converter

import br.com.taxone.kotlin.dto.SAFXColumnDTO
import br.com.taxone.kotlin.dto.SAFXTableDTO
import br.com.taxone.kotlin.entity.DSColumn
import br.com.taxone.kotlin.entity.SAFXColumn
import br.com.taxone.kotlin.entity.SAFXTable

public class SAFXColumnConverter {

	companion object {
	
		fun convert(safxcolumn: SAFXColumn): SAFXColumnDTO {
			var cDTO = SAFXColumnDTO()
			cDTO.id = safxcolumn.id
			cDTO.name = safxcolumn.name
			cDTO.columnType = safxcolumn.columnType
			cDTO.required = safxcolumn.required
			cDTO.size = safxcolumn.size
			cDTO.position = safxcolumn.position
			
			var dsColumn = safxcolumn.dsColumn
			if (dsColumn != null) {
				cDTO.dsColumnId = dsColumn.id
				cDTO.dsColumnName = dsColumn.name
			}
			return cDTO
		}
	
		fun convertCriteria(safxcolumn: SAFXColumn): SAFXColumnDTO {
			var cDTO = SAFXColumnDTO()
			cDTO.id = safxcolumn.id
			cDTO.name = safxcolumn.name
			var safxTableDTO = SAFXTableDTO()
			var safxTable = safxcolumn.safxTable
			safxTableDTO.id = safxTable?.id
			safxTableDTO.name = safxTable?.name
			cDTO.safxTable = safxTableDTO
			return cDTO
		}
		
		fun convert(safxcolumn: SAFXColumnDTO): SAFXColumn {
			var c = SAFXColumn()
			c.id = safxcolumn.id
			return c 
		}
	
	}
}