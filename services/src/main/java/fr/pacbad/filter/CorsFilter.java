package fr.pacbad.filter;

import java.io.IOException;

import javax.annotation.Priority;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Priorities;

import fr.pacbad.logger.PacbadLogger;

@Priority(Priorities.HEADER_DECORATOR)
@WebFilter(urlPatterns = { "/*" })
public class CorsFilter implements Filter {

	private static final PacbadLogger LOGGER = PacbadLogger.getLogger(CorsFilter.class);

	@Override
	public void init(final FilterConfig filterConfig) throws ServletException {
		LOGGER.info("Initialisation du CORS Filter");
	}

	@Override
	public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain)
			throws IOException, ServletException {
		if (request instanceof HttpServletRequest && response instanceof HttpServletResponse) {
			final HttpServletRequest httpRequest = (HttpServletRequest) request;

			final HttpServletResponse httpResponse = (HttpServletResponse) response;
			httpResponse.addHeader("Access-Control-Allow-Origin", "*");
			httpResponse.addHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE");
			httpResponse.addHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");

			// Just ACCEPT and REPLY OK if OPTIONS
			if (httpRequest.getMethod().equals("OPTIONS")) {
				httpResponse.setStatus(HttpServletResponse.SC_OK);
				return;
			}
		}
		chain.doFilter(request, response);

	}

	@Override
	public void destroy() {
		// Rien à faire
	}

}
