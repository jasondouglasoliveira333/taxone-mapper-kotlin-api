package br.com.taxone.kotlin.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.taxone.kotlin.dto.DSColumnDTO;
import br.com.taxone.kotlin.dto.DSTableDTO;
import br.com.taxone.kotlin.dto.PageResponse;
import br.com.taxone.kotlin.service.MatcherService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("dsTables")
public class DSTableController {
	
	var log = LoggerFactory.getLogger("DSTableController.class")
	
	@Autowired
	lateinit private var matcherService: MatcherService 

	
	@GetMapping
	fun list(): ResponseEntity<Any> {
		try {
			var dsTables = mutableListOf<DSTableDTO>() // matcherService.getDSTables();
			return ResponseEntity.ok().body(dsTables);
		}catch (e: Exception) {
			log.error("Error obtendo a definicao da tabela", e);
			return ResponseEntity.badRequest().build();
		}
	}
	
			
	@GetMapping("{id}/dsColumns")
	fun listDSColumns(@PathVariable("id") id: Integer, 
			@RequestParam(name="page", defaultValue = "0") page: Int, 
			@RequestParam(name="size", defaultValue = "10") size: Int): ResponseEntity<Any> {
		try {
//			PageResponse<DSColumnDTO> dsColumns = matcherService.getDSColumns(id, PageRequest.of(page, size));
			var dsColumns = PageResponse<DSColumnDTO>();
			return ResponseEntity.ok().body(dsColumns);
		}catch (e: Exception) {
			log.error("Error obtendo a definicao da tabela", e);
			return ResponseEntity.badRequest().build();
		}
	}
}
