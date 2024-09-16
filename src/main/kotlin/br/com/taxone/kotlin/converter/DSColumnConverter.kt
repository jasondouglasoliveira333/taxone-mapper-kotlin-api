package br.com.taxone.kotlin.converter

import br.com.taxone.kotlin.dto.DSColumnDTO
import br.com.taxone.kotlin.entity.DSColumn
import br.com.taxone.kotlin.entity.DSTable

public class DSColumnConverter {
	
	companion object {
		fun convert(dsColumn: DSColumn): DSColumnDTO  {
			var dDTO = DSColumnDTO();
			dDTO.id = dsColumn.id
			dDTO.name = dsColumn.name
			dDTO.columnType = dsColumn.columnType
			dDTO.size = dsColumn.size
			dDTO.dsTable = DSTableConverter.convert(dsColumn.dsTable as DSTable)
			return dDTO
		}
	
		fun converter(dsd: DSColumnDTO): DSColumn {
			var dsc = DSColumn()
			dsc.name = dsd.name
			dsc.columnType = dsd.columnType
			dsc.size = dsd.size
			return dsc;
		}
	
		fun marge(dsd: DSColumnDTO, dsc: DSColumn) {
			dsc.columnType = dsd.columnType
			dsc.size = dsd.size
		}
	
	}

}