package fr.pacbad.services;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.mockito.Mockito;

import fr.pacbad.PacbadTest;
import fr.pacbad.filter.TransactionFilter;

public abstract class AbstractServiceTest<T extends SimpleService<?>> extends PacbadTest {

	protected T service;

	protected abstract T createService();

	protected TransactionFilter transactionFilter;

	@BeforeClass
	public static void setUpBeforeClass() throws ServletException {
		new TransactionFilter().init(null);
	}

	@AfterClass
	public static void tearDownAfterClass() {
		new TransactionFilter().destroy();
	}

	@Before
	public void setUp() {
		transactionFilter = new TransactionFilter();
		transactionFilter.initEntityManager();
		transactionFilter.beforeRequest(null);
		service = createService();
	}

	@After
	public void tearDown() throws IOException {
		final HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
		Mockito.when(response.getStatus()).thenReturn(999);
		transactionFilter.afterRequest(response);
	}

}
