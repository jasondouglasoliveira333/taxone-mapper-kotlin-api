package br.com.taxone.kotlin.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
class SAFXTable {
	
	@Id
	@GeneratedValue
	var id: Int? = null
	
	var name: String? = null
	
	var description: String? = null 
	
	@OneToMany(mappedBy = "safxTable")
	var safxColumns: List<SAFXColumn>? = null
	
	@ManyToOne
	var dsTable: DSTable? = null
	
}
