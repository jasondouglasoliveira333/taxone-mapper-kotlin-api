package br.com.taxone.kotlin.service

import java.time.LocalDateTime
import java.util.ArrayList
import java.util.stream.Collectors

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

import br.com.taxone.kotlin.converter.ScheduleConverter
import br.com.taxone.kotlin.dto.PageResponse
import br.com.taxone.kotlin.dto.PeriodeDTO
import br.com.taxone.kotlin.dto.ScheduleDTO
import br.com.taxone.kotlin.entity.Criteria
import br.com.taxone.kotlin.entity.Schedule
import br.com.taxone.kotlin.enums.ScheduleLogStatus
import br.com.taxone.kotlin.enums.ScheduleStatus
import br.com.taxone.kotlin.repository.CriteriaRepository
import br.com.taxone.kotlin.repository.ScheduleLogRepository
import br.com.taxone.kotlin.repository.ScheduleRepository

@Service
class ScheduleService {
	
	var log = LoggerFactory.getLogger("ScheduleService.class")
	
	@Autowired
	lateinit private var scheduleRepository: ScheduleRepository

	@Autowired
	lateinit private var criteriaRepository: CriteriaRepository
	
	@Autowired
	lateinit private var scheduleLogRepository: ScheduleLogRepository

	fun list(pageable: Pageable): PageResponse<ScheduleDTO> {
		var page = scheduleRepository.findByStatus(ScheduleStatus.ACTIVE, pageable)
		var sPage = PageResponse<ScheduleDTO>()
		var sDTOList = mutableListOf<ScheduleDTO>()
		for (s in page){
			var sDTO = ScheduleConverter.convert(s)
			sDTOList.add(sDTO)
		}
		sPage.content = sDTOList//page.stream().map().collect(Collectors.toList()))
		sPage.totalPages = page.totalPages
		return sPage
	}

	fun get(id: Int): ScheduleDTO {
		var s = scheduleRepository.getOne(id)
		return ScheduleConverter.convertWithDetail(s)
	}

	fun save(sDTO: ScheduleDTO) {
		var cDeleted = mutableListOf<Int>()
		if (sDTO.id != null) {
			var criterias = scheduleRepository.getOne(sDTO.id).criterias
			for (criteria in criterias as List<Criteria>){
				cDeleted.add(criteria.id as Int)
			}
		}
		var s = ScheduleConverter.convert(sDTO)
		if (s.id == null) {
			s.lastExecution = LocalDateTime.MIN
		}
		s.status = ScheduleStatus.ACTIVE
		scheduleRepository.save(s)
		for (c in s.criterias as List<Criteria>) {
			cDeleted.remove(c.id)
			c.schedule = s
			criteriaRepository.save(c)
		}
		System.out.println("cDeleted:" + cDeleted)
		for (cId in cDeleted) {
			criteriaRepository.deleteById(cId)
		}
	}

//	public void delete(Integer id) {
//		Schedule s = scheduleRepository.getOne(id)
//		s.getCriterias().stream().forEach(c -> criteriaRepository.delete(c))
//		scheduleRepository.delete(s)
//	}

	fun getPeriode(id: Int): PeriodeDTO {
		var s = scheduleRepository.getOne(id)
		var p = PeriodeDTO()
		p.days = s.days
		p.hours = s.hours
		return p
	}

	fun isWaitingTaxoneResponse(scheduleId: Int): Boolean {
		var count = scheduleLogRepository.countByScheduleIdAndStatus(scheduleId, ScheduleLogStatus.SENT)
		if (count > 0) {
			return true
		}
		return false
	}

//	@Transactional
	fun updateStatus(scheduleId: Int, status: ScheduleStatus) {
		scheduleRepository.updateStatus(scheduleId, status)
	}

}
