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

@Priority(Priorities.USER + 10)
@WebFilter(urlPatterns = { "/*" })
public class LogFilter implements Filter {

	private static final PacbadLogger LOGGER = PacbadLogger.getLogger(LogFilter.class);

	@Override
	public void init(final FilterConfig filterConfig) throws ServletException {
		LOGGER.info("Initialisation du Logger");
	}

	@Override
	public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain)
			throws IOException, ServletException {
		if (request instanceof HttpServletRequest && response instanceof HttpServletResponse) {
			final HttpServletRequest httpRequest = (HttpServletRequest) request;
			final HttpServletResponse httpResponse = (HttpServletResponse) response;

			final long startTime = System.currentTimeMillis();

			try {
				chain.doFilter(request, response);

			} finally {

				final long endTime = System.currentTimeMillis();
				final long duration = endTime - startTime;
				String message = "(";
				if (AuthFilter.getUserLogin() != null) {
					message += AuthFilter.getUserLogin();
				} else {
					message += "???";
				}
				message += ") ";
				message += httpRequest.getMethod() + ' ';
				message += httpRequest.getRequestURI() + " --> ";
				message += httpResponse.getStatus();
				message += " (" + duration + "ms)";
				if (!"OPTIONS".equalsIgnoreCase(httpRequest.getMethod())) {
					LOGGER.info(message);
				}

				// Supprimer l'utilisateur connecté du ThreadLocal. Dépendance entre les filtres
				// :(
				AuthFilter.clear();
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
