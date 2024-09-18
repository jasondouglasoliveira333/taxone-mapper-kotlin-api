package br.com.taxone.kotlin.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.taxone.kotlin.entity.ScheduleLogIntergrationError;

@Repository
public interface ScheduleLogIntergrationErrorRepository : JpaRepository<ScheduleLogIntergrationError, Int>{

	fun findByScheduleLogId(id: Int, pageable: Pageable): Page<ScheduleLogIntergrationError> 


}
