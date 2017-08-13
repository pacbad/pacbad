package fr.pacbad.filter;

import java.io.IOException;
import java.util.logging.Logger;

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

@Priority(Priorities.USER + 10)
@WebFilter(urlPatterns = { "/*" })
public class LogFilter implements Filter {

	private static final Logger LOGGER = Logger.getLogger(LogFilter.class.getSimpleName());

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		LOGGER.info("Initialisation du Logger");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		if (request instanceof HttpServletRequest && response instanceof HttpServletResponse) {
			HttpServletRequest httpRequest = (HttpServletRequest) request;
			HttpServletResponse httpResponse = (HttpServletResponse) response;
			
			long startTime = System.currentTimeMillis();

			try {
				chain.doFilter(request, response);
			} finally {
//				if (LOGGER.isLoggable(Level.INFO)) {
					long endTime = System.currentTimeMillis();
					long duration = endTime - startTime;
					String message = "(";
					if (AuthFilter.getUserLogin() != null) {
						message += AuthFilter.getUserLogin();
					} else {
						message += "???";
					}
					message  += ") ";
					message += httpRequest.getMethod() + ' ';
					message += httpRequest.getRequestURI() + " --> ";
					message += httpResponse.getStatus();
					message += " (" + duration + "ms)";
					LOGGER.info(message);
//				}
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
