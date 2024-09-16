package br.com.taxone.kotlin.service;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.taxone.kotlin.converter.DSColumnConverter;
import br.com.taxone.kotlin.converter.DSTableConverter;
import br.com.taxone.kotlin.converter.DataSourceConfigConverter;
import br.com.taxone.kotlin.dto.DSColumnDTO;
import br.com.taxone.kotlin.dto.DSTableDTO;
import br.com.taxone.kotlin.dto.DataSourceDTO;
import br.com.taxone.kotlin.entity.DSColumn;
import br.com.taxone.kotlin.entity.DSTable;
import br.com.taxone.kotlin.entity.DataSourceConfiguration;
import br.com.taxone.kotlin.enums.DataSourceType;
import br.com.taxone.kotlin.repository.DSColumnRepository;
import br.com.taxone.kotlin.repository.DSTableRepository;
import br.com.taxone.kotlin.repository.DataSourceConfigRepository;

@Service
public class DataSourceConfigService {

	@Autowired
	lateinit var dataSourceConfigRepository: DataSourceConfigRepository 
	
	@Autowired
	lateinit var dsTableRepository: DSTableRepository

	@Autowired
	lateinit var dsColumnRepository: DSColumnRepository 

	fun list(): List<DataSourceDTO> {
		var dataSourceDTOList = mutableListOf<DataSourceDTO>()
		var dataSourceList = dataSourceConfigRepository.findAll()
		for (dataSource in dataSourceList){
			var dataSourceDTO = DataSourceConfigConverter.convert(dataSource)
			dataSourceDTOList.add(dataSourceDTO)
		}
		return dataSourceDTOList;
	}

	fun get(dataSourceType: String): DataSourceDTO  {
		return DataSourceConfigConverter.convert(dataSourceConfigRepository.findByDataSourceType(DataSourceType.valueOf(dataSourceType)))
	}

	fun getDSTables(dataSourceType: String): List<DSTableDTO> {
		var dsTables = dsTableRepository.findBydataSourceConfigurationDataSourceType(DataSourceType.valueOf(dataSourceType))
		var dsTablesDTO = mutableListOf<DSTableDTO>()
		for (dsTable in dsTables){
			var dsTableDTO = DSTableConverter.convert(dsTable)
			dsTablesDTO.add(dsTableDTO)
		}
		return dsTablesDTO
	}

	fun saveDataSourrce(dsDTO: DataSourceDTO ): Int {
		var dsc = DataSourceConfigConverter.convert(dsDTO)
		var saved = dataSourceConfigRepository.save(dsc)
		if (saved.id != null){
			return saved.id as Int
		}else{
			return 0
		}
	}

	fun saveTablesAndColumns(dataSourceConfigId: Int, dsTable: DSTableDTO, dsColumnsList: List<DSColumnDTO>) {
		
		var dst = dsTableRepository.findFirstBydataSourceConfigurationIdAndName(dataSourceConfigId, dsTable.name as String)
		if (dst == null) {
			dst = DSTable()
			dst.dataSourceConfiguration = dataSourceConfigRepository.getOne(dataSourceConfigId)
			dst.name = dsTable.name
			dsTableRepository.save(dst);
		}
		
		for (dsColumnDTO in dsColumnsList)  {
			var dsc = dsColumnRepository.findFirstBydsTableIdAndName(dst.id as Int, dsColumnDTO.name as String)
			if (dsc == null) {
				dsc = DSColumnConverter.converter(dsColumnDTO);
				dsc.dsTable = dst
			}else {
				DSColumnConverter.marge(dsColumnDTO, dsc);
			}
			dsColumnRepository.save(dsc);
		}
	}





}
