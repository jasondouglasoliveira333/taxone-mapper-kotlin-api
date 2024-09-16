package br.com.taxone.kotlin.controller

import java.util.ArrayList

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort.Direction
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

import br.com.taxone.kotlin.dto.EmailDTO
import br.com.taxone.kotlin.dto.Customer
import br.com.taxone.kotlin.dto.PageResponse
import br.com.taxone.kotlin.service.EmailService


@CrossOrigin
@RestController
@RequestMapping("emails")
class EmailController {
	
	var log = LoggerFactory.getLogger("EmailController.class")
	
	@Autowired
	lateinit var emailService : EmailService

	@GetMapping
	fun list(@RequestParam(name="page", defaultValue = "0") page : Int, 
			@RequestParam(name="size", defaultValue = "10") size : Int) : ResponseEntity<PageResponse<EmailDTO>> { 
		try {
			var uPage = emailService.findAll(PageRequest.of(page, size, Direction.DESC, "id"))
			return ResponseEntity.ok(uPage)
		}catch(e : Exception) {
			log.error("Erro listando os email", e)
			return ResponseEntity.badRequest().build()
		}
	}

	@PostMapping
	fun save(@RequestBody emails : MutableList<EmailDTO> ) : ResponseEntity<Any> {
		try {
			for (email in emails){
				emailService.save(email)
			}
			return ResponseEntity.ok().build()
		}catch (e : Exception) {
			log.error("Erro salvando o email", e);
			return ResponseEntity.badRequest().build()
		}
	}
	
	@DeleteMapping("{emailId}")
	fun delete(@PathVariable("emailId") emailId : Int) : ResponseEntity<Any>{
		try {
			emailService.delete(emailId)
			return ResponseEntity.ok().build()
		}catch (e : Exception) {
			log.error("Erro delete o email", e);
			return ResponseEntity.badRequest().build()
		}
	}

}
