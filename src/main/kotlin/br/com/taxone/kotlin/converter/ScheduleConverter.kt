package br.com.taxone.kotlin.converter

import java.util.stream.Collectors

import br.com.taxone.kotlin.dto.CriteriaDTO
import br.com.taxone.kotlin.dto.SAFXTableDTO
import br.com.taxone.kotlin.dto.ScheduleDTO
import br.com.taxone.kotlin.entity.Criteria
import br.com.taxone.kotlin.entity.SAFXTable
import br.com.taxone.kotlin.entity.Schedule
import br.com.taxone.kotlin.entity.User

public class ScheduleConverter {

	companion object {
		fun convert(schedule: Schedule): ScheduleDTO  {
			var sDTO = ScheduleDTO()
			sDTO.id = schedule.id
			sDTO.name = schedule.name
			sDTO.days = schedule.days
			sDTO.hours = schedule.hours
			sDTO.status = schedule.status
			sDTO.userName = schedule.user?.name
			return sDTO
		}
		
		fun convertWithDetail(schedule: Schedule): ScheduleDTO  {
			var sDTO = convert(schedule)
			var safxtDTOList = mutableListOf<SAFXTableDTO>()
			for (safxTable in schedule.safxTables as List<SAFXTable>){
				var safxDTO = SAFXTableConverter.convertIdName(safxTable)
				safxtDTOList.add(safxDTO)
			}
			sDTO.safxTables = safxtDTOList
			
			var criteriaDTOList = mutableListOf<CriteriaDTO>()
			for (criteria in schedule.criterias as List<Criteria>){
				var criteriaDTO = CriteriaConverter.convert(criteria)
				criteriaDTOList.add(criteriaDTO)
				
			}
			sDTO.criterias = criteriaDTOList
			return sDTO
		}
	
		fun convert(sDTO: ScheduleDTO): Schedule  {
			var s = Schedule()
			s.id = sDTO.id
			s.name = sDTO.name
			s.days = sDTO.days
			s.hours = sDTO.hours
			s.status = sDTO.status
			var u = User()
			u.id = 1
			s.user = u
			
			var safxtList = mutableListOf<SAFXTable>()
			for (safxTDTO in sDTO.safxTables as List<SAFXTableDTO>){
				var safxT = SAFXTableConverter.convertIdName(safxTDTO)
				safxtList.add(safxT)
			}
			s.safxTables = safxtList
			
			var criteriaList = mutableListOf<Criteria>()
			for (criteria in sDTO.criterias as List<CriteriaDTO>){
				var c = CriteriaConverter.convert(criteria)
				criteriaList.add(c)
				
			}
			s.criterias = criteriaList
			return s
		}
	
	}
}