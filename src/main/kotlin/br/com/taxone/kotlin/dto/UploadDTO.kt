package br.com.taxone.kotlin.dto

import java.time.LocalDateTime

import br.com.taxone.kotlin.enums.UploadStatus
import lombok.Data

@Data
public class UploadDTO {

	var id: Long? = null
	var fileName: String? = null
	var layoutVersion: String? = null
	var creationDate: LocalDateTime? = null
	var status: UploadStatus? = null
	var userName: String? = null
}
