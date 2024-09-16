package br.com.taxone.kotlin.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

import br.com.taxone.kotlin.entity.Upload
import br.com.taxone.kotlin.enums.UploadStatus

@Repository
interface UploadRepository: JpaRepository<Upload, Int>{

	@Modifying
	@Query("update Upload set status = :status")
	fun updateStatus(@Param("status")status: UploadStatus)

}
