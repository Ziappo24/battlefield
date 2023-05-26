package battlefield;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/* Modificare la classe Position affinche' 
 * il primo test abbia successo (vedi DOMANDA 1) 
 */
public class BattlefieldTest {
	
	private Battlefield field;
	
	@Before
	public void setUp() throws Exception {
		this.field = new Battlefield(2);
	}

	@Test
	public void testAddWalker() {
		assertEquals(0, this.field.getAllWalkers().size());
		this.field.addWalker(new Walker(new Position(0,0)));
		assertEquals(1, this.field.getAllWalkers().size());
	}
	
	/*@Test
	public void testRaggruppaRobotDiDueTipiDiversi() {
		fail("vedi DOMANDA 3");
	}*/

}
