package br.com.taxone.kotlin.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import br.com.taxone.kotlin.enums.ColumnType;
import lombok.Data;

@Entity
class SAFXColumn {

	@Id
	@GeneratedValue 
	var id: Int? = null
	var name: String? = null
	@Enumerated(EnumType.STRING)
	var columnType: ColumnType? = null
	var required: Boolean? = null 
	var position: Int? = null  
	var size: Int? = null
	
	@ManyToOne
	var safxTable: SAFXTable? = null
	
	@ManyToOne
	var dsColumn: DSColumn? = null

}
