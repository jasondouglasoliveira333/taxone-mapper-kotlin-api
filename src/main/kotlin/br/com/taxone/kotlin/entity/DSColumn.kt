package br.com.taxone.kotlin.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import br.com.taxone.kotlin.enums.ColumnType;
import lombok.Data;

@Data
@Entity
public class DSColumn {
	
	@Id
	@GeneratedValue
	var id: Int? = null
	var name: String? = null
	@Enumerated(EnumType.STRING)
	var columnType: ColumnType? = null
	var size: Int? = null

	@ManyToOne
	var dsTable: DSTable = DSTable()
}
