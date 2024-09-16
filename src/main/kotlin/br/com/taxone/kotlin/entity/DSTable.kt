package br.com.taxone.kotlin.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;

@Data
@Entity
public class DSTable {

	@Id
	@GeneratedValue
	var id: Int? = null
	var name: String? = null 

	@ManyToOne
	var dataSourceConfiguration: DataSourceConfiguration? = null
	
	@OneToMany(mappedBy = "dsTable")
	var dsColumns: List<DSColumn>? = null

	
}
