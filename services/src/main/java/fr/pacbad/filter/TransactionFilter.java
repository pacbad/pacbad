package fr.pacbad.filter;

import java.io.IOException;

import javax.annotation.Priority;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Priorities;

import fr.pacbad.Application;
import fr.pacbad.logger.PacbadLogger;

@Priority(Priorities.USER + 20)
@WebFilter(urlPatterns = { "/*" })
public class TransactionFilter implements Filter {

	private static final PacbadLogger LOGGER = PacbadLogger.getLogger(TransactionFilter.class);

	private static final ThreadLocal<EntityManager> THREAD_LOCAL_ENTITY_MANAGER = new ThreadLocal<>();

	public static EntityManagerFactory emf;

	public static EntityManager getEntityManager() {
		return THREAD_LOCAL_ENTITY_MANAGER.get();
	}

	private boolean shouldStartTransaction(final ServletRequest request) {
		return true;
		// final String method = request.getMethod();
		// return HttpMethod.POST.equals(method) || HttpMethod.PUT.equals(method) ||
		// HttpMethod.DELETE.equals(method);
	}

	private boolean shouldRollbackTransaction(final ServletResponse response) {
		if (response instanceof HttpServletResponse) {
			final int status = ((HttpServletResponse) response).getStatus();
			return status >= 400;
		} else {
			LOGGER.warning("Ce n'est pas une requête HTTP : " + response.getClass());
		}
		return true;
	}

	@Override
	public void init(final FilterConfig filterConfig) throws ServletException {
		LOGGER.info("Démarrage de l'application en mode " + Application.getEnvironnement());
		emf = Persistence.createEntityManagerFactory("pacbad-persistence-" + Application.getEnvironnement());
	}

	@Override
	public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain)
			throws IOException, ServletException {
		// System.out.println("Start TransactionFilter");
		initEntityManager();

		beforeRequest(request);

		try {
			chain.doFilter(request, response);
		} finally {

			afterRequest(response);
		}
		// System.out.println("End TransactionFilter");

	}

	public void initEntityManager() {
		final EntityManager entityManager = emf.createEntityManager();
		THREAD_LOCAL_ENTITY_MANAGER.set(entityManager);
	}

	public EntityTransaction beforeRequest(final ServletRequest request) {
		// Démarrage d'une transaction
		EntityTransaction transaction = null;
		if (shouldStartTransaction(request)) {
			transaction = getEntityManager().getTransaction();
			transaction.begin();
		}
		return transaction;
	}

	public void afterRequest(final ServletResponse response) {
		// Fin de la transaction
		final EntityTransaction transaction = getEntityManager().getTransaction();
		if (transaction != null && transaction.isActive()) {
			if (shouldRollbackTransaction(response)) {
				transaction.rollback();
			} else {
				transaction.commit();
			}
		}
	}

	@Override
	public void destroy() {
		emf.close();
	}

}
