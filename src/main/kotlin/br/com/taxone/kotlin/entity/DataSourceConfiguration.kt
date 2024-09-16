package br.com.taxone.kotlin.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import br.com.taxone.kotlin.enums.DataSourceType;
import lombok.Data;

@Data
@Entity
public class DataSourceConfiguration {
	
	@Id
	@GeneratedValue
	var id: Int? = null
	var url: String? = null
	var username: String? = null
	var password: String? = null
	var resourceNames: String? = null
	
	@Enumerated(EnumType.STRING)
	var dataSourceType: DataSourceType? = null  
	
	@OneToMany(mappedBy = "dataSourceConfiguration")
	var dsTables: List<DSTable>? = null

}
