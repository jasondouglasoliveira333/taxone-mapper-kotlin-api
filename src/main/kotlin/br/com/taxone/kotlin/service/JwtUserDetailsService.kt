package br.com.taxone.kotlin.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.taxone.kotlin.dto.POCUser;
import br.com.taxone.kotlin.entity.User;
import br.com.taxone.kotlin.repository.UserRepository

@Service
class JwtUserDetailsService : UserDetailsService {
	
	@Autowired
	lateinit private var userRepository: UserRepository
	
	@Override
	override fun loadUserByUsername(username: String): UserDetails {// throws UsernameNotFoundException 
		var u = userRepository.findByName(username);
		if (u != null) {
			var p = POCUser(u.name as String, u.password as String, true, true, true, true, emptyList());
			p.id = u.id
			return p
		}else {
			throw UsernameNotFoundException("User not found with username: " + username);
		}
		
//		if ("javainuse".equals(username)) {
//			return new User("javainuse", "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6",
//					new ArrayList<>());
//		} else {
//			throw new UsernameNotFoundException("User not found with username: " + username);
//		}
	}
}