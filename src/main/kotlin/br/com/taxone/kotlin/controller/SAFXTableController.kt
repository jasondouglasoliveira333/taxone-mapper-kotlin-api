package br.com.taxone.kotlin.controller

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

import br.com.taxone.kotlin.dto.PageResponse
import br.com.taxone.kotlin.dto.SAFXColumnDTO
import br.com.taxone.kotlin.dto.SAFXColumnUpdateDTO
import br.com.taxone.kotlin.dto.SAFXTableDTO
import br.com.taxone.kotlin.service.MatcherService
import br.com.taxone.kotlin.service.MatcherTXService
import lombok.extern.slf4j.Slf4j

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("safxTables")
class SAFXTableController {
	
	var log = LoggerFactory.getLogger("SAFXTableController.class")
	
	@Autowired
	lateinit private var matcherService: MatcherService
	
	@Autowired
	lateinit private var matcherTXService: MatcherTXService
	

	@GetMapping
	fun list(@RequestParam(name="tableName", required = false) tableName: String?, 
			@RequestParam(name="justAssociated", defaultValue = "false") justAssociated: Boolean,
			@RequestParam(name="page", defaultValue = "0") page: Int, 
			@RequestParam(name="size", defaultValue = "10") size: Int): ResponseEntity<Any> {
		try {
			var sPage = matcherService.findAllSafx(tableName, justAssociated, PageRequest.of(page, size))
			return ResponseEntity.ok(sPage)
		}catch(e: Exception) {
			log.error("Erro listando as tablelas safx", e)
			return ResponseEntity.badRequest().build()
		}
	}
	
	@GetMapping("{id}")
	fun get(@PathVariable("id") id: Int): ResponseEntity<Any> {
		try {
			var safxTable = matcherService.getSAFXTable(id)
			return ResponseEntity.ok().body(safxTable)
		}catch (e: Exception) {
			log.error("Error obtendo a definicao da tabela", e)
			return ResponseEntity.badRequest().build()
		}
	}
	
			
	@GetMapping("{id}/safxColumns")
	fun listSAFXColumns(@PathVariable("id") id: Int, @RequestParam(name="associated", defaultValue = "false") associated: Boolean): ResponseEntity<Any>{
		try {
			var safxColumns = matcherService.getSAFXColumns(id, associated)
			return ResponseEntity.ok().body(safxColumns)
		}catch (e: Exception) {
			log.error("Error obtendo a definicao da tabela", e)
			return ResponseEntity.badRequest().build()
		}
	}
	
	@PutMapping("{id}/safxColumns")
	fun updateSAFXColumns(@RequestBody safxColumns: MutableList<SAFXColumnUpdateDTO>): ResponseEntity<Any>{
		try {
			System.out.println("safxColumns.size():" + safxColumns.size)
			matcherTXService.updateSAFXColumns(safxColumns)
			return ResponseEntity.ok().build()
		}catch (e: Exception) {
			log.error("Error atualizando as safx columns", e)
			return ResponseEntity.badRequest().build()
		}
	}

	@PutMapping("{id}/dsTables/{dsTableId}")
	fun updateSAFXTable(@PathVariable("id") id: Int, @PathVariable("dsTableId") dsTableId: Int): ResponseEntity<Any>{
		try {
			System.out.println("dsTableId:" + dsTableId)
			matcherTXService.updateSAFXTable(id, dsTableId)
			return ResponseEntity.ok().build()
		}catch (e: Exception) {
			log.error("Error atualizando as safx columns", e)
			return ResponseEntity.badRequest().build()
		}
	}
}
