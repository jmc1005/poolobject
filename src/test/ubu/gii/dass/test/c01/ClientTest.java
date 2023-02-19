package ubu.gii.dass.test.c01;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ubu.gii.dass.c01.Client;
import ubu.gii.dass.c01.DuplicatedInstanceException;
import ubu.gii.dass.c01.NotFreeInstanceException;

public class ClientTest {

	private Client client;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		client = new Client();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws NotFreeInstanceException, DuplicatedInstanceException {
		client = null;
	}

	/**
	 * Test method for  {@link ubu.gii.dass.c01.Client#main(ubu.gii.dass.c01.Client)}.
	 * @throws DuplicatedInstanceException 
	 * @throws NotFreeInstanceException 
	 */
	@SuppressWarnings("static-access")
	@Test
	public void testMain() throws NotFreeInstanceException, DuplicatedInstanceException {

		assertNotNull(client);
		assertTrue(client instanceof Client);

		client.main(null);
	}

}
