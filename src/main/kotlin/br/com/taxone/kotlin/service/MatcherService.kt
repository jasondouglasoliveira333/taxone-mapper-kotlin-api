package br.com.taxone.kotlin.service

import java.util.stream.Collectors

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

import br.com.taxone.kotlin.converter.DSColumnConverter
import br.com.taxone.kotlin.converter.DSTableConverter
import br.com.taxone.kotlin.converter.SAFXColumnConverter
import br.com.taxone.kotlin.converter.SAFXTableConverter
import br.com.taxone.kotlin.dto.DSColumnDTO
import br.com.taxone.kotlin.dto.DSTableDTO
import br.com.taxone.kotlin.dto.DataSourceDTO
import br.com.taxone.kotlin.dto.PageResponse
import br.com.taxone.kotlin.dto.SAFXColumnDTO
import br.com.taxone.kotlin.dto.SAFXColumnUpdateDTO
import br.com.taxone.kotlin.dto.SAFXTableDTO
import br.com.taxone.kotlin.entity.DSColumn
import br.com.taxone.kotlin.entity.SAFXColumn
import br.com.taxone.kotlin.entity.SAFXTable
import br.com.taxone.kotlin.enums.DataSourceType
import br.com.taxone.kotlin.repository.DSColumnRepository
import br.com.taxone.kotlin.repository.DSTableRepository
import br.com.taxone.kotlin.repository.SAFXColumnRepository
import br.com.taxone.kotlin.repository.SAFXTableRepository
import br.com.taxone.kotlin.util.DatabaseHelper
//import br.com.taxone.kotlin.util.FTPHelper
//import br.com.taxone.kotlin.util.FileHelper
import br.com.taxone.kotlin.util.StringUtil

@Service
public class MatcherService {
	
	@Autowired
	lateinit private var safxTableRepository: SAFXTableRepository 

	@Autowired
	lateinit private var safxColumnRepository: SAFXColumnRepository 
	
	@Autowired
	lateinit private var dsTableRepository: DSTableRepository

	@Autowired
	lateinit private var dsColumnRepository: DSColumnRepository

	fun findAllSafx(name: String?, justAssociated: Boolean, page: PageRequest): PageResponse<SAFXTableDTO> {
		var safxPage = safxTableRepository.findByNameAndAssociated(StringUtil.putPercent(name), justAssociated, page)
		var sfResponse = PageResponse<SAFXTableDTO>()
//		System.out.println("safxPage.getContent().size:" + safxPage.getContent().size)
		var safxTableList = mutableListOf<SAFXTableDTO>()
		for (safxTable in safxPage.getContent()){
			var safxTableDTO = SAFXTableConverter.convert(safxTable)
			safxTableList.add(safxTableDTO)
		}
		sfResponse.content = safxTableList 
		sfResponse.totalPages = safxPage.totalPages
		return sfResponse
	}

	fun getSAFXTable(id: Int): SAFXTableDTO {
		return SAFXTableConverter.convert(safxTableRepository.getOne(id))
	}

	fun getSAFXColumns(id: Int, associated: Boolean): List<SAFXColumnDTO> {
		var scList = safxColumnRepository.findBysafxTableId(id)
		var scFitered =	 scList.filter{sc -> if (associated) if (sc.dsColumn != null) true else false else true}
		var safxDTOList = mutableListOf<SAFXColumnDTO>()
		for (safxColumn in scFitered){
			var safxCDTO = SAFXColumnConverter.convert(safxColumn)
			safxDTOList.add(safxCDTO)
		}
		return safxDTOList
	}

	fun getDSColumns(id: Int, page: Pageable): PageResponse<DSColumnDTO> {
		var dcPage = dsColumnRepository.findBydsTableId(id, page)
		var sfResponse = PageResponse<DSColumnDTO>()
		var dsColumns = mutableListOf<DSColumnDTO>()
		for (dsColumn in dcPage.getContent()){
			var dsColumDTO = DSColumnConverter.convert(dsColumn)
			dsColumns.add(dsColumDTO)
		}
		sfResponse.content = dsColumns 
		sfResponse.totalPages = dcPage.totalPages
		return sfResponse
	}

	fun getDSTables(): List<DSTableDTO> {
		var dsTables = dsTableRepository.findAll()
		var dsTablesDTO = mutableListOf<DSTableDTO>()
		for (dsTable in dsTables){
			var dsTableDTO = DSTableConverter.convert(dsTable)
			dsTablesDTO.add(dsTableDTO)
		}
		return dsTablesDTO
	}

	fun getDSMetadata(dataSourceDTO: DataSourceDTO): List<DSColumnDTO>{
		var dsList: List<DSColumnDTO>? = null
		if (dataSourceDTO.dataSourceType?.equals(DataSourceType.Database) as Boolean) {
			dsList = DatabaseHelper.getTableMetadata(dataSourceDTO)
//		}else if (dataSourceDTO.getDataSourceType().equals(DataSourceType.TXT)) {
//			dsList = FileHelper.getFileMetadata(dataSourceDTO)
//		}else {
//			dsList = FTPHelper.getFileMetadata(dataSourceDTO)
		}
		return dsList as List<DSColumnDTO>
	}

}
