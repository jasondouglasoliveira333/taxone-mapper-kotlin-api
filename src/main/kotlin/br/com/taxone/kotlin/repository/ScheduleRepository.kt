package br.com.taxone.kotlin.repository

import java.time.LocalDateTime

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

import br.com.taxone.kotlin.entity.Schedule
import br.com.taxone.kotlin.enums.ScheduleStatus

@Repository
public interface ScheduleRepository : JpaRepository<Schedule, Int>{

	fun findByDaysContainingAndLastExecutionLessThanOrDaysAndLastExecutionLessThan(days: String, data: LocalDateTime, wildcard: String, data2: LocalDateTime): List<Schedule>

	@Query("update Schedule s set s.status = :status where s.id = :id")
	@Modifying
	fun updateStatus(@Param("id") id: Int, @Param("status") status: ScheduleStatus)

	fun findByStatus(status: ScheduleStatus, pageable: Pageable): Page<Schedule>

}
