package work.javiermantilla.appfranquicia.basicos.security;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import work.javiermantilla.appfranquicia.basicos.utils.FranquiciaConstants;

@Component
@RequiredArgsConstructor
@Log4j2
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	//private static final String MESSAGE_REQUIRED_HEADER = "Required headers not specified in the request";

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		if (request.getMethod().equals("OPTIONS")) {
			return;
		}
		String path = request.getRequestURI();
		if (verifyPath(path)) {
			filterChain.doFilter(request, response);
			return;
		}
		HttpServletResponse resp = response;
		resp.sendError(HttpServletResponse.SC_FORBIDDEN,"Url No permitida por seguridad. Contacte con DEVS");

	}

	private boolean verifyPath(String path) {
		Boolean response = false;
		for (String urlAdmitida : FranquiciaConstants.URLS_ADMITIDAS) {
			if (path.contains(urlAdmitida)) {
				return true;
			}
		}
		return response;
	}

}
