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

		// obtenemos reusable de lista
		Reusable r;
		String util = null;
		
		try {

			r = pool.acquireReusable();

			// obtenemos los reusables que tenemos en el pool
			while (r != null) {
				util = r.util();
				r = pool.acquireReusable();	
				assertNotNull(r);
				assertTrue(r instanceof Reusable);
			}

		} catch (NotFreeInstanceException e) {

			System.err.println(util + ". " + e.getMessage());
		}
	}

	/**
	 * Test method for
	 * {@link ubu.gii.dass.c01.ReusablePool#releaseReusable(ubu.gii.dass.c01.Reusable)}.
	 */
	@Test
	public void testReleaseReusable() {

		// liberar objeto reusable
		pool = ReusablePool.getInstance();
		Reusable r;
		Reusable aux;
		String util = null;
		
		try {

			assertNotNull(pool);
			r = pool.acquireReusable();
			
			while (r != null) {
				util = r.util();
				pool.releaseReusable(r);
				aux = pool.acquireReusable();
				util = r.util();
				assertTrue(util.equals(aux.util()));
				pool.releaseReusable(aux);		
				
				assertNotNull(r);
				assertTrue(r instanceof Reusable);
				assertNotNull(aux);
				assertTrue(aux instanceof Reusable);
			}

		} catch (NotFreeInstanceException | DuplicatedInstanceException e) {
			System.err.println(util + ". " + e.getMessage());
		}

	}

}
