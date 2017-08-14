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
			final HttpServletResponse httpResponse = (HttpServletResponse) response;
			try {
				chain.doFilter(request, response);
			} finally {
				httpResponse.setHeader("Accept", "*");
			}
		} else {
			chain.doFilter(request, response);
		}

	}

	@Override
	public void destroy() {
		// Rien à faire
	}

}
