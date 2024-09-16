package br.com.taxone.kotlin.converter

import br.com.taxone.kotlin.dto.UploadDTO
import br.com.taxone.kotlin.entity.Upload

class UploadConverter {

	companion object{
		fun convert(upload: Upload): UploadDTO  {
			var uDTO = UploadDTO()
			uDTO.id = upload.id
			uDTO.fileName = upload.fileName
			uDTO.layoutVersion = upload.layoutVersion
			uDTO.creationDate = upload.creationDate
			uDTO.status = upload.status
			uDTO.userName = upload.user?.name
			return uDTO
		}
	}
}
