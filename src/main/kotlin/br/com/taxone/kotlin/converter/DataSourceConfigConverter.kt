package br.com.taxone.kotlin.converter;

import br.com.taxone.kotlin.dto.DataSourceDTO;
import br.com.taxone.kotlin.entity.DataSourceConfiguration;

public class DataSourceConfigConverter {
	
	companion object {
		fun convert(dsc: DataSourceConfiguration): DataSourceDTO {
			var dsd = DataSourceDTO();
			dsd.id = dsc.id
			dsd.url = dsc.url
			dsd.username = dsc.username
			dsd.password = dsc.password
			dsd.resourceNames = dsc.resourceNames
			dsd.dataSourceType = dsc.dataSourceType
			return dsd;
		}
	
		fun convert(dsd: DataSourceDTO): DataSourceConfiguration {
			var dsc = DataSourceConfiguration();
			dsc.id = dsd.id
			dsc.url = dsd.url
			dsc.username = dsd.username
			dsc.password = dsd.password
			dsc.resourceNames = dsd.resourceNames
			dsc.dataSourceType = dsd.dataSourceType
			return dsc;
		}
	}
}