package br.com.taxone.kotlin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.taxone.kotlin.entity.DSTable;
import br.com.taxone.kotlin.enums.DataSourceType;

@Repository
public interface DSTableRepository: JpaRepository<DSTable, Int>{

	fun findBydataSourceConfigurationDataSourceType(dataSourceType: DataSourceType ): List<DSTable>

	fun findFirstBydataSourceConfigurationIdAndName(dataSourceConfigId: Int, name: String): DSTable 

}
