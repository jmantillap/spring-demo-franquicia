package work.javiermantilla.appfranquicia.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import work.javiermantilla.appfranquicia.basic.security.JwtAuthenticationFilter;

import java.io.IOException;

@TestConfiguration
@ContextConfiguration(classes = JwtAuthenticationFilter.class)
public class TestSecurityConfig extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
	
		 filterChain.doFilter(request, response);
         return;
	}
}
