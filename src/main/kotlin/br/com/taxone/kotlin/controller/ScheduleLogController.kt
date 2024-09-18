package br.com.taxone.kotlin.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.taxone.kotlin.dto.PageResponse;
import br.com.taxone.kotlin.dto.ScheduleLogDTO;
import br.com.taxone.kotlin.dto.ScheduleLogIntergrationErrorDTO;
import br.com.taxone.kotlin.dto.ScheduleLogStatisticDTO;
import br.com.taxone.kotlin.enums.ScheduleLogStatus;
import br.com.taxone.kotlin.service.ScheduleLogService;

@CrossOrigin
@RestController
@RequestMapping("/schedulelogs")
class ScheduleLogController {
	
	var log = LoggerFactory.getLogger("ScheduleLogController");
	
	@Autowired
	lateinit private var scheduleLogService: ScheduleLogService 

	@GetMapping
	fun list(@RequestParam(name="status") status: ScheduleLogStatus,  @RequestParam(name="page", defaultValue = "0") page: Int, 
			@RequestParam(name="size", defaultValue = "10") size: Int): ResponseEntity<Any> {
		try {
			var sPage = scheduleLogService.findAll(status, PageRequest.of(page, size, Direction.DESC, "executionDate"));
			return ResponseEntity.ok(sPage);
		}catch(e: Exception) {
			log.error("Erro listando os logs de agendamento", e);
			return ResponseEntity.badRequest().build();
		}
	}
	@GetMapping("statistics")
	fun generateStatitics(): ResponseEntity<Any>{
		try {
			var slsList = scheduleLogService.groupByStatus();
			return ResponseEntity.ok(slsList);
		}catch(e: Exception) {
			log.error("Erro listando os logs de agendamento", e);
			return ResponseEntity.badRequest().build();
		}
	}
	
	@GetMapping("{id}")
	fun get(@PathVariable("id") id: Int): ResponseEntity<Any> {
		try {
			var slDTO = scheduleLogService.get(id);
			return ResponseEntity.ok(slDTO);
		}catch(e: Exception) {
			log.error("Erro listando os logs de agendamento", e);
			return ResponseEntity.badRequest().build();
		}
	}

	@GetMapping("{id}/taxOneErrors")
	fun getTaxOneErrors(@PathVariable("id") id: Int, @RequestParam(name="page", defaultValue = "0") page: Int, 
			@RequestParam(name="size", defaultValue = "10") size: Int): ResponseEntity<Any> {
		try {
			var taxOneErrors = scheduleLogService.getTaxtOneErrors(id, PageRequest.of(page, size));
			return ResponseEntity.ok(taxOneErrors);
		}catch(e: Exception) {
			log.error("Erro listando os logs de agendamento", e);
			return ResponseEntity.badRequest().build();
		}
	}


}
