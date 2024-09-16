package br.com.taxone.kotlin.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.taxone.kotlin.entity.DSColumn;

@Repository
public interface DSColumnRepository: JpaRepository<DSColumn, Int>{

	fun findBydsTableId(id: Int, page: Pageable): Page<DSColumn>

	fun findFirstBydsTableIdAndName(dsTableId: Int, name: String): DSColumn 

}
