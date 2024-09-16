package br.com.taxone.kotlin.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.taxone.kotlin.entity.SAFXTable;

@Repository
public interface SAFXTableRepository: JpaRepository<SAFXTable, Int>{

	fun findByName(tableName: String): SAFXTable 

	@Query("select s from SAFXTable s where (s.name like :name or :name is null) and (:justAssociated = true and dsTable is not null or :justAssociated = false)") 
	fun findByNameAndAssociated(@Param("name") name: String, @Param("justAssociated") justAssociated: Boolean, page: Pageable): Page<SAFXTable> 

	@Modifying
	@Query("update SAFXTable s set s.dsTable.id = :dsTableId where s.id = :id")
	fun updateDSTable(@Param("id") id: Int, @Param("dsTableId") dsTableId: Int)

}
