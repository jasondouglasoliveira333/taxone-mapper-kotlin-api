package br.com.taxone.kotlin.dto

import br.com.taxone.kotlin.enums.DataSourceType
import lombok.Data

@Data
public class DataSourceDTO {
	var id: Int? = null
	var url: String? = null
	var username: String? = null
	var password: String? = null
	var resourceNames: String? = null
	var dataSourceType: DataSourceType? = null
	
}
