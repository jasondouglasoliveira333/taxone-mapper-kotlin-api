package br.com.taxone.kotlin.controller

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.domain.PageRequest
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

import br.com.taxone.kotlin.dto.ErrorResponse
import br.com.taxone.kotlin.dto.PageResponse
import br.com.taxone.kotlin.dto.PeriodeDTO
import br.com.taxone.kotlin.dto.ScheduleDTO
import br.com.taxone.kotlin.entity.TaxOneApi
import br.com.taxone.kotlin.enums.ScheduleStatus
//import br.com.taxone.kotlin.integration.OncoClinicasTaxtOneService
//import br.com.taxone.kotlin.integration.OncoClinicasTaxtOneServiceBuilder
import br.com.taxone.kotlin.repository.ScheduleRepository
import br.com.taxone.kotlin.repository.TaxOneApiRepository
//import br.com.taxone.kotlin.service.ScheduleSenderService
import br.com.taxone.kotlin.service.ScheduleService

@RestController
@CrossOrigin
@RequestMapping("schedules")
class ScheduleController {

	var log = LoggerFactory.getLogger("ScheduleController.class")
	
	@Autowired
	lateinit private var scheduleService: ScheduleService 

//	@Autowired
//	lateinit private var scheduleSenderService: ScheduleSenderService 
	
	@Autowired
	lateinit private var scheduleRepository: ScheduleRepository
	
//	@Autowired
//	lateinit private var oncoIntegrationBuilder: OncoClinicasTaxtOneServiceBuilder
	
	@Autowired
	lateinit private var taxOneApiRepository: TaxOneApiRepository 

	
	@GetMapping
	fun list(@RequestParam(name="page", defaultValue = "0") page: Int, 
			@RequestParam(name="size", defaultValue = "10") size: Int): ResponseEntity<Any>{
		try {
			var sPage = scheduleService.list(PageRequest.of(page, size))
			return ResponseEntity.ok(sPage)
		}catch (e: Exception) { 
			log.error("Erro listando os agendamentos", e)
			return ResponseEntity.badRequest().build()
		}
	}
	
	@GetMapping("{scheduleId}")
	fun get(@PathVariable("scheduleId") id: Int): ResponseEntity<Any> {
		try {
			var sDTO = scheduleService.get(id)
			return ResponseEntity.ok(sDTO)
		}catch (e: Exception) {
			log.error("Erro obtendo o agendamento", e)
			return ResponseEntity.badRequest().build()
		}
	}

	//Will be removed soon
	@GetMapping("{scheduleId}/periodes")
	fun getPeriodes(@PathVariable("scheduleId") id: Int): ResponseEntity<Any> {
		try {
			 var pDTO = scheduleService.getPeriode(id)
			return ResponseEntity.ok(pDTO)
		}catch (e: Exception) {
			log.error("Erro obtendo o periodo do agendamento", e)
			return ResponseEntity.badRequest().build()
		}
	}

	
	@PostMapping
//	@Transactional
	fun save(@RequestBody sDTO: ScheduleDTO): ResponseEntity<Any> {
		try {
			scheduleService.save(sDTO)
			return ResponseEntity.ok().build()
		}catch (e: Exception) {
			log.error("Erro salvando o agendamento", e)
			return ResponseEntity.badRequest().build()
		}
	}
	
	@DeleteMapping("{scheduleId}")
//	@Transactional
	fun delete(@PathVariable("scheduleId") scheduleId: Int): ResponseEntity<Any>{
		try {
			if (!scheduleService.isWaitingTaxoneResponse(scheduleId)) {
				log.info("can delete scheduleId")
				scheduleService.updateStatus(scheduleId, ScheduleStatus.INACTIVE)
				return ResponseEntity.ok().build()
			}else {
				var er = ErrorResponse(1, "Agendamento com retorno do TaxOne pendente")
				return ResponseEntity.badRequest().body(er)
			}
		}catch (e: Exception) {
			log.error("Erro delete o agendamento", e)
			return ResponseEntity.badRequest().build()
		}
	}

//	@GetMapping("{scheduleId}/process")
//	fun process(@PathVariable("scheduleId") scheduleId: Int) {
//		try {
//			OncoClinicasTaxtOneService oncoIntegrationService = oncoIntegrationBuilder.createService(null)
//			log.info("Autenticando no api de integracao OncoClinicas")
//			TaxOneApi taxOneApi = taxOneApiRepository.getOne(1)
//			String token = oncoIntegrationService.authentication(taxOneApi.getUsername(), taxOneApi.getPassword()).execute().body().string()
////			String token = "wot T1RLAQLbA9R0H7vF4vtX2YU-38cYmYCUnhDw56lNkdpb3frQxXCTC3SwAAHQftxXHEY3E0zL8aiEg_UqmtTzDIP4QcsEnVq4nnvHwOyWmBLftx3z6M-HiRkJxF0hdDp4drX4c9XD_exMQzPgHgx0oRv33a6fDC2tsgR3H4PHWMQkPEPJAw4ye_h1iF2WUGWHKWOL3laKXuntjCtU4I3BHfQvl8S6OR8OawVEhKZUmsUyhyPvopcHFHv3Ncbt6NW5Ss9xWr4U2JnnVtNcJylF4PDHJP8hU2vJlVa4rM9NxPdWwB4NyWpf7g0xR8E-jHua-h71D_PJq3Y_UrKjWGm2UjyLqzHDVoZSWMrglGgFC1a6AeRZw8hGOpBS0oJIJuvTN2r5AxTodsulyiq1UnvMBjGUI6RY2KYm4cRf34GLfJnWxDDb2YkazSOXUo-xZyl09bM_iAJp-Sg5BnNaN7M5RiZfMYUL81nmh7YOAIoTmq3LFFQ-6eBXupchsYdyesiGxHRbyiHXKMWqMAzmATRGH6sJa5iFHiiOKM7eWEROqv5q2CEdUlEY8lyVt_IHlzqDchi7VJLuuSMU3pQLUagzLJel_w4YwWd0kN6TG75g-RicWRE2PU0i0PnJzEsR16vErmuyJgwOeAWhSgm8oIlKnp9-_zy5boBiQBVWsc4*"
//			System.out.println("token:" + token)
//			scheduleSenderService.process(scheduleRepository.getOne(scheduleId), token)
//		}catch (e: Exception) {
//			log.error("Erro na executao do agendamento", e)
//		}
//	}
	
	
}
