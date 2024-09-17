package br.com.taxone.kotlin.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

import br.com.taxone.kotlin.entity.SAFXColumn

@Repository
public interface SAFXColumnRepository: JpaRepository<SAFXColumn, Int>{

	fun findBysafxTableId(id: Int): List<SAFXColumn> 

	@Modifying
	@Query("update SAFXColumn s set s.dsColumn.id = :dsColumnId where s.id = :id")
	fun updateSAFXColumn(@Param("id") id: Int, @Param("dsColumnId") dsColumnId: Int?)

	fun findFirstBysafxTableIdAndName(id: Int, name: String ): SAFXColumn 

}
