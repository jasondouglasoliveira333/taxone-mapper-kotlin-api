package br.com.taxone.kotlin.entity

import java.time.LocalDateTime

import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToOne

import br.com.taxone.kotlin.enums.UploadStatus
import lombok.Data

@Entity
class Upload {
	
	@Id
	@GeneratedValue
	var id: Long? = null
	var fileName: String? = null
	var layoutVersion: String? = null
	var creationDate: LocalDateTime? = null
	
	@Enumerated(EnumType.STRING)
	var status: UploadStatus? = null
	
	@ManyToOne
	var user: User? = null
	
}
