package br.com.taxone.kotlin.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.taxone.kotlin.dto.ScheduleLogStatisticDTO
import br.com.taxone.kotlin.entity.ScheduleLog
import br.com.taxone.kotlin.enums.ScheduleLogStatus

@Repository
public interface ScheduleLogRepository : JpaRepository<ScheduleLog, Int>{

	fun findByStatus(status: ScheduleLogStatus): List<ScheduleLog> 

	fun findByStatus(status: ScheduleLogStatus , pageable: Pageable): Page<ScheduleLog>

	@Query("select new br.com.taxone.kotlin.dto.ScheduleLogStatisticDTO(sl.status, count(sl.id)) from ScheduleLog sl group by sl.status")
	fun groupByStatus(): List<ScheduleLogStatisticDTO>

	fun countByScheduleIdAndStatus(scheduleId: Int, status: ScheduleLogStatus): Int

}
