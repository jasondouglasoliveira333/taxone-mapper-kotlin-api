package br.com.taxone.kotlin.service

import java.io.IOException
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.time.LocalDateTime
import java.util.List
import java.util.stream.Collectors

import javax.transaction.UserTransaction

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
//import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

import br.com.taxone.kotlin.converter.UploadConverter
//import br.com.taxone.kotlin.dto.POCUser
import br.com.taxone.kotlin.dto.PageResponse
import br.com.taxone.kotlin.dto.UploadDTO
//import br.com.taxone.kotlin.entity.SAFXColumn
//import br.com.taxone.kotlin.entity.SAFXTable
import br.com.taxone.kotlin.entity.Upload
import br.com.taxone.kotlin.entity.User
//import br.com.taxone.kotlin.enums.ColumnType
import br.com.taxone.kotlin.enums.UploadStatus
//import br.com.taxone.kotlin.repository.SAFXColumnRepository
//import br.com.taxone.kotlin.repository.SAFXTableRepository
import br.com.taxone.kotlin.repository.UploadRepository
import kotlin.collections.mutableListOf
import javax.persistence.EntityManager
//import br.com.taxone.kotlin.util.JExcelHelper
//import br.com.taxone.kotlin.util.XLSField
//import br.com.taxone.kotlin.util.XLSTable

@Service
open class UploadService {
	
	@Autowired
	lateinit var uploadReponsitory: UploadRepository
	
	@Transactional
	open fun parseFileAndStore(fileName: String, layoutVersion: String, indata: Any) {
		uploadReponsitory.updateStatus(UploadStatus.CANCELED)
		
//		POCUser user = (POCUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal()
		
		var u = Upload()
		u.fileName = fileName
		u.layoutVersion = layoutVersion
		u.creationDate = LocalDateTime.now()
		u.status = UploadStatus.ACTIVE
		var user = User()
		user.id = 1
		u.user = user
		uploadReponsitory.save(u)
	}

	open fun findAll(page: PageRequest): PageResponse<UploadDTO>  {
		var uPage = uploadReponsitory.findAll(page)
//		System.out.println("uPage:" + uPage.getTotalElements())
		var upResponse = PageResponse<UploadDTO>()
		var uploads = mutableListOf<UploadDTO>()
		for (u in uPage.getContent()){
			var uDTO = UploadConverter.convert(u)
			uploads.add(uDTO)
		}
		upResponse.content = uploads //(.stream().map().collect(Collectors.toList()))
		upResponse.totalPages = uPage.totalPages
		return upResponse
	}
}
