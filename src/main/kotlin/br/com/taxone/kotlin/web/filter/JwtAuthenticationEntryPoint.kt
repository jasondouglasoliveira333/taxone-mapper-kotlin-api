package br.com.taxone.kotlin.web.filter;

import java.io.IOException;
import java.io.Serializable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
class JwtAuthenticationEntryPoint : AuthenticationEntryPoint{
	
	
	@Override
	override fun commence(request: HttpServletRequest, response: HttpServletResponse,
			authException: AuthenticationException) {//throws IOException 
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
	}
}