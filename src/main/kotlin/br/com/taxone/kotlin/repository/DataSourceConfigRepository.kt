package br.com.taxone.kotlin.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

import br.com.taxone.kotlin.entity.DataSourceConfiguration
import br.com.taxone.kotlin.enums.DataSourceType

@Repository
public interface DataSourceConfigRepository : JpaRepository<DataSourceConfiguration, Int>{

	fun findByDataSourceType(dataSourceType: DataSourceType): DataSourceConfiguration 

}
