/**
 * 
 */
package ubu.gii.dass.test.c01;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ubu.gii.dass.c01.DuplicatedInstanceException;
import ubu.gii.dass.c01.NotFreeInstanceException;
import ubu.gii.dass.c01.Reusable;
import ubu.gii.dass.c01.ReusablePool;

/**
 * @author Javier Martín Castro (jmc1005@alu.ubu.es)
 *
 */
public class ReusablePoolTest {

	// Propiedades
	private ReusablePool pool;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		pool = ReusablePool.getInstance();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		pool = null;
	}

	/**
	 * Test method for {@link ubu.gii.dass.c01.ReusablePool#getInstance()}.
	 */
	@Test
	public void testGetInstance() {

		pool = ReusablePool.getInstance();

		// Comprobación el objeto no es nulo
		assertNotNull(pool);

		// Comprobación si el objeto es instancia de ReusablePool
		assertTrue(pool instanceof ReusablePool);
	}

	/**
	 * Test method for {@link ubu.gii.dass.c01.ReusablePool#acquireReusable()}.
	 */
	@Test
	public void testAcquireReusable() {

		try {
			
			Reusable r = pool.acquireReusable();
			assertNotNull(r);
			assertTrue(r instanceof Reusable);

			// obtenemos los reusables que tenemos en el pool
			while (r != null) {

				assertNotNull(r);
				assertTrue(r instanceof Reusable);
				r = pool.acquireReusable();
			}
			
		} catch (NotFreeInstanceException e) {
			System.err.println(e.getMessage());
		}
	}

	/**
	 * Test method for
	 * {@link ubu.gii.dass.c01.ReusablePool#releaseReusable(ubu.gii.dass.c01.Reusable)}.
	 */
	@Test
	public void testReleaseReusable() {

		try {

			// liberar objeto reusable
			pool = ReusablePool.getInstance();
			Reusable aux = null;
			String util = null;
			assertNotNull(pool);
			assertTrue(pool instanceof ReusablePool);
			Reusable r = pool.acquireReusable();
			assertNotNull(r);
			assertTrue(r instanceof Reusable);

			while (r != null) {

				pool.releaseReusable(r);
				aux = pool.acquireReusable();
				assertTrue(r.util().equals(aux.util()));
				pool.releaseReusable(aux);

				assertNotNull(r);
				assertTrue(r instanceof Reusable);
				assertNotNull(aux);
				assertTrue(aux instanceof Reusable);
			}

		} catch (NotFreeInstanceException | DuplicatedInstanceException e) {
			System.err.println(e.getMessage());
		}
	}

}
