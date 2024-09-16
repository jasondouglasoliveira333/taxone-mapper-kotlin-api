package br.com.taxone.kotlin.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Entity
class User {
	
	@Id
	@GeneratedValue
	var id: Int? = null
	var name: String? = null
	var password: String? = null
	var creationDate: LocalDateTime? = null
	var lastAccess: LocalDateTime? = null
	
}
