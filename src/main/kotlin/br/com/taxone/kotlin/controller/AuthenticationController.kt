package br.com.taxone.kotlin.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.DisabledException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

import br.com.taxone.kotlin.dto.AutenticationRequest
import br.com.taxone.kotlin.dto.AutenticationResponse
import br.com.taxone.kotlin.service.JwtUserDetailsService
import br.com.taxone.kotlin.util.JwtTokenUtil

@RestController
@CrossOrigin
class AuthenticationController {
	
	@Autowired
	lateinit private var authenticationManager: AuthenticationManager
	@Autowired
	lateinit private var jwtTokenUtil: JwtTokenUtil 
	@Autowired
	lateinit private var userDetailsService: JwtUserDetailsService
	
	
//	@Autowired
//	private UserRepository userRepository 
//
//	@GetMapping
//	public void createUser() {
//		User u = new User()
//		u.setName("jason_2")
//		u.setPassword("$2a$10$VHMKDc4JQtHB9vhTKxGYxuwk.A9vxgkho/ufVR5c5IrmTg5tX24WS")
//		u.setCreationDate(LocalDateTime.now())
//		userRepository.save(u)
//	}
//	
	
	@PostMapping("/authenticate")
	fun createAuthenticationToken(@RequestBody authenticationRequest: AutenticationRequest) : ResponseEntity<Any> {// throws Exception 
		authenticate(authenticationRequest.username as String, authenticationRequest.password as String)
		var userDetails = userDetailsService.loadUserByUsername(authenticationRequest.username as String)
		var token = jwtTokenUtil.generateToken(userDetails)
		return ResponseEntity.ok(AutenticationResponse(token))
	}

	fun authenticate(username: String, password: String) {//throws Exception
		try {
			var o = authenticationManager.authenticate(UsernamePasswordAuthenticationToken(username, password))
			System.out.println("authenticated:" + o)
		} catch (e: DisabledException) {
			throw Exception("USER_DISABLED", e)
		} catch (e: BadCredentialsException) {
			throw Exception("INVALID_CREDENTIALS", e)
		}
	}
}