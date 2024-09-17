package br.com.taxone.kotlin.controller

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

//import br.com.taxone.kotlin.dto.PageResponse
//import br.com.taxone.kotlin.dto.UserDTO
//import br.com.taxone.kotlin.service.UserService

@CrossOrigin
@RestController
@RequestMapping("/users")
class UserController {

	var log = LoggerFactory.getLogger("UserController.class")
	
//	@Autowired
//	lateinit var UserService userService
//
//	@GetMapping
//	fun list(@RequestParam("page") page : Int, @RequestParam("size") size : Int) : ResponseEntity<PageResponse<UserDTO>> {
//		try {
//			var prUser = userService.findAll(PageRequest.of(page, size))
//			return ResponseEntity.ok(prUser)
//		}catch (Exception e) {
//			log.error("Erro listando os usuarios", e)
//			return ResponseEntity.badRequest().build()
//		}
//	}
//	
//	@GetMapping("{id}")
//	public ResponseEntity<?> get(@PathVariable("id") Integer id){
//		try {
//			UserDTO uDTO = userService.getOne(id)
//			return ResponseEntity.ok(uDTO)
//		}catch (Exception e) {
//			log.error("Erro listando os usuarios", e)
//			return ResponseEntity.badRequest().build()
//		}
//	}
//	
//	
//	@PostMapping
//	public ResponseEntity<?> save(@RequestBody UserDTO user){
//		try {
//			userService.save(user)
//			return ResponseEntity.ok().build()
//		}catch (Exception e) {
//			log.error("Erro salvando o usuario", e)
//			return ResponseEntity.badRequest().build()
//		}
//	}
//
//	@DeleteMapping("{id}")
//	public ResponseEntity<?> deleteById(@PathVariable("id") Integer id){
//		try {
//			userService.deleteById(id)
//			return ResponseEntity.ok().build()
//		}catch (Exception e) {
//			log.error("Erro excluindo o usuario", e)
//			return ResponseEntity.badRequest().build()
//		}
//	}
	
}
