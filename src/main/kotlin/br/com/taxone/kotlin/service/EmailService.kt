package br.com.taxone.kotlin.service

import java.util.Arrays
import java.util.stream.Collectors

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

import br.com.taxone.kotlin.converter.EmailConverter
import br.com.taxone.kotlin.dto.EmailDTO
import br.com.taxone.kotlin.dto.PageResponse
import br.com.taxone.kotlin.entity.Email
import br.com.taxone.kotlin.repository.EmailRepository
import kotlin.collections.listOf
import kotlin.collections.mutableListOf

@Service
class EmailService {
	
	@Autowired
	lateinit var emailRepository : EmailRepository
	
	fun findAll(pageable : PageRequest) : PageResponse<EmailDTO> {
		var pr = PageResponse<EmailDTO>()
		var pEmail = emailRepository.findAll(pageable)
		pr.totalPages = pEmail.totalPages
		var emails = mutableListOf<EmailDTO>()
		for (emailEntity in pEmail.getContent()){
			var emailDTO = EmailConverter.convert(emailEntity)
			emails.add(emailDTO)
		}
		pr.content = emails
		return pr
	}

	fun save(eDTO : EmailDTO) {
		var e = EmailConverter.convert(eDTO)
		emailRepository.save(e)
	}

	fun delete(emailId : Int) {
		emailRepository.deleteById(emailId)
	}
	

}


