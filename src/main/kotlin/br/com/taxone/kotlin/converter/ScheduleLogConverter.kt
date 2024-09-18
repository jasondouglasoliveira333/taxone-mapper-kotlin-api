package br.com.taxone.kotlin.converter

import br.com.taxone.kotlin.dto.ScheduleLogDTO
import br.com.taxone.kotlin.dto.ScheduleLogIntergrationErrorDTO
import br.com.taxone.kotlin.entity.ScheduleLog
import br.com.taxone.kotlin.entity.ScheduleLogIntergrationError

class ScheduleLogConverter {

	companion object {
	
		fun convert(sl: ScheduleLog): ScheduleLogDTO  {
			var slDTO = ScheduleLogDTO()
			slDTO.id = sl.id
			slDTO.scheduleName = sl.schedule?.name
			slDTO.executionDate = sl.executionDate
			slDTO.status = sl.status
			return slDTO
		}
	
		fun convert(slie: ScheduleLogIntergrationError): ScheduleLogIntergrationErrorDTO {
			var slieDTO = ScheduleLogIntergrationErrorDTO()
			slieDTO.id = slie.id
			slieDTO.numeroReg = slie.numeroReg
			slieDTO.nomeCampo = slie.nomeCampo
			slieDTO.codigoErro = slie.codigoErro
			slieDTO.descricaoErro = slie.descricaoErro
			slieDTO.chaveRegistro = slie.chaveRegistro
			return slieDTO
		}
	}
	
}