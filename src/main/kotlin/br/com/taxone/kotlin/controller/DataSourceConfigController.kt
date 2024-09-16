package br.com.taxone.kotlin.controller

import java.util.ArrayList
import java.util.Arrays
import java.util.HashMap
import java.util.stream.Collectors

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.http.ResponseEntity
//import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

import br.com.taxone.kotlin.dto.DSColumnDTO
import br.com.taxone.kotlin.dto.DSTableDTO
import br.com.taxone.kotlin.dto.DataSourceDTO
//import br.com.taxone.kotlin.dto.POCUser
import br.com.taxone.kotlin.dto.PageResponse
import br.com.taxone.kotlin.enums.DataSourceType
import br.com.taxone.kotlin.service.DataSourceConfigService
import br.com.taxone.kotlin.service.MatcherService
import lombok.extern.slf4j.Slf4j

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("dataSourceConfigs")
class DataSourceConfigController {
	
	var log = LoggerFactory.getLogger("DataSourceConfigController.class")
	
	@Autowired
	lateinit private var dataSourceConfigService: DataSourceConfigService 
	
	@Autowired
	lateinit private var matcherService: MatcherService
	
	var dsTableTemporary = HashMap<Int, List<DSTableDTO>>()
	var dsColumnsTemporary = HashMap<Int, List<DSColumnDTO>>()
	
	@GetMapping
	fun list(): ResponseEntity<Any>{
		try {
			var dss = dataSourceConfigService.list()
			return ResponseEntity.ok().body(dss)
		}catch (e: Exception) {
			log.error("Error obtendo a definicao do data source", e)
			return ResponseEntity.badRequest().build()
		}
	}
	
	@GetMapping("{dataSourceType}")
	fun get(@PathVariable("dataSourceType") dataSourceType: String): ResponseEntity<Any> {
		try {
			clearUserTableAndColumns()//clear the tables and columns of cache
			var ds = dataSourceConfigService.get(dataSourceType)
			return ResponseEntity.ok().body(ds)
		}catch (e: Exception) {
			log.error("Error obtendo a definicao do data source", e)
			return ResponseEntity.badRequest().build()
		}
	}
	
	@GetMapping("{dataSourceType}/dsTables")
	fun listDsTables(@PathVariable("dataSourceType") dataSourceType: String): ResponseEntity<Any> {
		try {
//			POCUser user = (POCUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal()
			var dsTs = dsTableTemporary.get(1)
			if (dsTs == null) {
				dsTs = dataSourceConfigService.getDSTables(dataSourceType)
			}
			return ResponseEntity.ok().body(dsTs)
		}catch (e: Exception) {
			log.error("Error obtendo a definicao da tabela do data source", e)
			return ResponseEntity.badRequest().build()
		}
	}
	
	@GetMapping("{dataSourceType}/dsTables/{dsTableId}/dsColumns")
	fun listDsColumns(@PathVariable("dsTableId") dsTableId: Int,
			@RequestParam(name="page", defaultValue = "0") page: Int, 
			@RequestParam(name="size", defaultValue = "10") size: Int): ResponseEntity<Any> {
		try {
//			var dsCPage = null as PageResponse<DSColumnDTO>
//			List<DSColumnDTO> dscList = dsColumnsTemporary.get(dsTableId)
//			if (dscList != null) {
//				dsCPage = new PageResponse<>()
//				int lastIdx = page * size + size
//				if (lastIdx > dscList.size()) {
//					lastIdx = dscList.size()  
//				}
//				dsCPage.setContent(dscList.subList(page * size, lastIdx))
//				int totalPages = dscList.size() / size + (dscList.size() % size == 0 ? 0 : 1)
//				System.out.println("totalPages:" + totalPages)
//				dsCPage.setTotalPages(totalPages)
//			}else {
				var dsCPage = matcherService.getDSColumns(dsTableId, PageRequest.of(page, size))
//			}
			return ResponseEntity.ok().body(dsCPage)
		}catch (e: Exception) {
			log.error("Error obtendo a definicao das colunas da tabela do data source", e)
			return ResponseEntity.badRequest().build()
		}
	}
	
	@PostMapping("{dataSourceType}/metadata")
	fun getMetadata(@PathVariable("dataSourceType") dataSourceType: String,  @RequestBody dataSourceDTO: DataSourceDTO):
		ResponseEntity<Any> {
		try {
			dataSourceDTO.dataSourceType = DataSourceType.valueOf(dataSourceType)
			var dsList = mutableListOf<DSColumnDTO>()//matcherService.getDSMetadata(dataSourceDTO)
			System.out.println("dsListMetadata.size:" + dsList.size)
			clearUserTableAndColumns()
			loadTableAndColumns(dataSourceDTO.resourceNames as String, dsList)
			return ResponseEntity.ok().build()
		}catch (e: Exception) {
			log.error("Error obtendo a definicao das colunas da tabela do data source", e)
			return ResponseEntity.badRequest().build()
		}
	}
	
	
	@PostMapping("{dataSourceType}")
//	@Transactional
	fun saveDataSourrce(@PathVariable("dataSourceType") dataSourceType: String,  @RequestBody dataSourceDTO: DataSourceDTO):
			ResponseEntity<Any> {
		try {
//			POCUser user = (POCUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal()
			dataSourceDTO.dataSourceType = DataSourceType.valueOf(dataSourceType)
			var dsId = dataSourceConfigService.saveDataSourrce(dataSourceDTO)
			var userId = 1
			if (dsTableTemporary.get(userId) != null) {
				var dsTables = dsTableTemporary.get(userId) as MutableList<DSTableDTO>
				for (dsTable in dsTables){
					dataSourceConfigService.saveTablesAndColumns(dsId, dsTable, dsColumnsTemporary.get(dsTable.id) as MutableList<DSColumnDTO>)
				}
			}
			clearUserTableAndColumns()
			return ResponseEntity.ok().build()
		}catch (e: Exception) {
			log.error("Error obtendo a definicao das colunas da tabela do data source", e)
			return ResponseEntity.badRequest().build()
		}
	}


	fun loadTableAndColumns(tableNames: String, dsList: List<DSColumnDTO>) {
//		POCUser user = (POCUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal()
		var tables = Arrays.asList(tableNames.split(","))
		for (tableName in tables) {
			var dsCList = mutableListOf<DSColumnDTO>()
			for (dsColumn in dsList){
				if (dsColumn.dsTable?.name?.equals(tableName) as Boolean){
					dsCList.add(dsColumn)
				}
			}
			var pseudoId = (Math.random() * 100000)
			var dstDTO = DSTableDTO()
			dstDTO.id = pseudoId as Int
			dstDTO.name = tableName as String
			var userId = 1
			var dstList = dsTableTemporary.get(userId) as MutableList<DSTableDTO>
			if (dstList == null) {
				dstList = mutableListOf<DSTableDTO>()
				dsTableTemporary.put(userId, dstList)
			}
			dstList.add(dstDTO)
			dsColumnsTemporary.put(pseudoId, dsCList)
		}

	}

	fun clearUserTableAndColumns() {
//		POCUser user = (POCUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal()
		var userId = 1
		if (dsTableTemporary.get(userId) != null) {
			var dsTables = dsTableTemporary.get(userId)
			for (dsTable in dsTables as MutableList<DSTableDTO>){
				dsColumnsTemporary.remove(dsTable.id)
			}
			dsTableTemporary.remove(userId)
		}
	}
	
}
