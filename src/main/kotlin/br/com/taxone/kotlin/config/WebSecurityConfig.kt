package br.com.taxone.kotlin.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

import br.com.taxone.kotlin.web.filter.JwtAuthenticationEntryPoint
import br.com.taxone.kotlin.web.filter.JwtRequestFilter
import kotlin.reflect.KClass

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
open class WebSecurityConfig : WebSecurityConfigurerAdapter() {
	
	@Autowired
	lateinit private var jwtAuthenticationEntryPoint: JwtAuthenticationEntryPoint 
	
	@Autowired
	lateinit private var jwtUserDetailsService: UserDetailsService 
	
	@Autowired
	lateinit private var jwtRequestFilter: JwtRequestFilter 
	
	
	@Autowired
	fun configureGlobal(auth: AuthenticationManagerBuilder) { //throws Exception 
		auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder())
	}
	
	@Bean
	open fun passwordEncoder(): PasswordEncoder {
		return BCryptPasswordEncoder()
	}
	
	@Bean
	@Override
	override fun authenticationManagerBean(): AuthenticationManager {//throws Exception 
		return super.authenticationManagerBean()
	}
	
	@Override
	override fun configure(httpSecurity: HttpSecurity)  {
		// We don't need CSRF for this example
		httpSecurity.csrf().disable()
				// dont authenticate theses particular requests
				.authorizeRequests()
					.antMatchers("/authenticate").permitAll()
					.antMatchers("/h2-console/*").permitAll()
					.antMatchers("/swagger-ui/**").permitAll()
					.antMatchers("/swagger-ui.html").permitAll()
					.antMatchers("/v3/**").permitAll()
					
				// all other requests need to be authenticated
				.anyRequest().authenticated().and().
				// make sure we use stateless session session won't be used to
				// store user's state.
				exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
				.and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		// Add a filter to validate the tokens with every request
		httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter::class.java)
		//h2-console
		httpSecurity.headers().frameOptions().disable()
	}
	
	// to implement a new encoder/decoder to show tha password in edit user
//	@Autowired
//	private UserDetailsService userDetailsService
//	
//	@Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(authProvider())
//    }
//	
//	@Bean
//	public DaoAuthenticationProvider authProvider() {
//		System.out.println("IN DaoAuthenticationProvider factory")
//	    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider()
//	    authProvider.setUserDetailsService(userDetailsService)
//	    authProvider.setPasswordEncoder(new OURPasswordEncoder())
//	    return authProvider
//	}
//	
//	class OURPasswordEncoder extends BCryptPasswordEncoder{
//		
//		@Override
//		public boolean matches(CharSequence rawPassword, String encodedPassword) {
//			System.out.println("In OURPasswordEncoder.matches rawPassword:" + rawPassword + " - encodedPassword:" + encodedPassword)
//			return super.matches(rawPassword, encodedPassword)
//		}
//		
//	}

}