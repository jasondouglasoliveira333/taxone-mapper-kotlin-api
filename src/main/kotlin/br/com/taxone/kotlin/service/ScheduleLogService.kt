package br.com.taxone.kotlin.service;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.taxone.kotlin.converter.ScheduleLogConverter;
import br.com.taxone.kotlin.dto.PageResponse;
import br.com.taxone.kotlin.dto.ScheduleLogDTO;
import br.com.taxone.kotlin.dto.ScheduleLogIntergrationErrorDTO;
import br.com.taxone.kotlin.dto.ScheduleLogStatisticDTO;
import br.com.taxone.kotlin.entity.ScheduleLog;
import br.com.taxone.kotlin.entity.ScheduleLogIntergrationError;
import br.com.taxone.kotlin.enums.ScheduleLogStatus;
import br.com.taxone.kotlin.repository.ScheduleLogIntergrationErrorRepository;
import br.com.taxone.kotlin.repository.ScheduleLogRepository;

@Service
class ScheduleLogService {
	
	@Autowired
	lateinit private var scheduleLogRepository: ScheduleLogRepository 

	@Autowired
	lateinit private var scheduleLogIntergrationErrorRepository: ScheduleLogIntergrationErrorRepository 

	//Just for that
//	@Transactional(propagation = Propagation.REQUIRES_NEW)
	fun save(sLog: ScheduleLog) {
		scheduleLogRepository.save(sLog);
	}

	fun findAll(status: ScheduleLogStatus, pageable: PageRequest): PageResponse<ScheduleLogDTO> {
		var slPage = scheduleLogRepository.findByStatus(status, pageable);
		var pageResponse = PageResponse<ScheduleLogDTO>()
		pageResponse.totalPages = slPage.totalPages
		var scheduleLogs = mutableListOf<ScheduleLogDTO>()
		for (sl in slPage.getContent() as List<ScheduleLog>){
			var sDTO = ScheduleLogConverter.convert(sl)
			scheduleLogs.add(sDTO)
		}
		pageResponse.content = scheduleLogs
		return pageResponse;
	}

	fun groupByStatus(): List<ScheduleLogStatisticDTO> {
		return scheduleLogRepository.groupByStatus();
	}

	fun get(id: Int): ScheduleLogDTO  {
		return ScheduleLogConverter.convert(scheduleLogRepository.getOne(id));
	}

	fun getTaxtOneErrors(id: Int, pageable: PageRequest): PageResponse<ScheduleLogIntergrationErrorDTO>  {
		var sliePage = scheduleLogIntergrationErrorRepository.findByScheduleLogId(id, pageable);
		var pageResponse = PageResponse<ScheduleLogIntergrationErrorDTO>();
		pageResponse.totalPages = sliePage.totalPages
		var scheduleLogErrors = mutableListOf<ScheduleLogIntergrationErrorDTO>()
		for (sl in sliePage.getContent() as List<ScheduleLogIntergrationError>){
			var sDTO = ScheduleLogConverter.convert(sl)
			scheduleLogErrors.add(sDTO)
		}
		pageResponse.content = scheduleLogErrors
		return pageResponse;
	}

}
