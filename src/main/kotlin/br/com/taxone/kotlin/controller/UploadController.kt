package br.com.taxone.kotlin.controller

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort.Direction
import org.springframework.http.ResponseEntity
//import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

//import br.com.taxone.kotlin.dto.POCUser
import br.com.taxone.kotlin.dto.PageResponse
import br.com.taxone.kotlin.dto.UploadDTO
import br.com.taxone.kotlin.service.UploadService

@CrossOrigin
@RestController
@RequestMapping("uploads")
public class UploadController {
	
	var log = LoggerFactory.getLogger("UploadController.class")
	
	val HI_FOLKS : String = "Hi FOLKS"
	
	@Autowired 
	lateinit var uploadService: UploadService 
	
//	@GetMapping("ping")
//	fun ping(): String {
//		log.info("IN UploadController.ping")
//		return HI_FOLKS
//	}
	
	@PostMapping
	fun upload(@RequestParam(name="layoutVersion") layoutVersion: String,  @RequestParam(name="file") file: MultipartFile): ResponseEntity<Any> {
		try {
			log.info("In UploadController.upload:" + file.getOriginalFilename() + " - layoutVersion:" + layoutVersion)
			uploadService.parseFileAndStore(file.getOriginalFilename(), layoutVersion, file.getBytes())
			return ResponseEntity.ok().build()
		}catch (e : Exception) {
			log.error("Erro efetuando parser do arquivo", e)
			return ResponseEntity.badRequest().build()
		}
	}
	
	@GetMapping
	fun list(@RequestParam(name="page", defaultValue = "0") page: Int, 
			@RequestParam(name="size", defaultValue = "10") size: Int) : ResponseEntity<PageResponse<UploadDTO>> {
		try {
			var uPage = uploadService.findAll(PageRequest.of(page, size, Direction.DESC, "id"))
			return ResponseEntity.ok(uPage)
		}catch(e: Exception) {
			log.error("Erro listando os uploads", e)
			return ResponseEntity.badRequest().build()
		}
	}
}
