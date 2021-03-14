package no.hvl.dat102.adt;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import no.hvl.dat102.mengde.adt.MengdeADT;

public abstract class MengdeADTTest {

	private MengdeADT<Integer> mengde1;
	private MengdeADT<Integer> mengde2;
	private MengdeADT<Integer> result;
	
	private Integer e0 = 1;
	private Integer e1 = 2;
	private Integer e2 = 3;
	private Integer e3 = 4;
	

	protected abstract MengdeADT<Integer> reset();
	
	@BeforeEach
	public void setup() {
		mengde1 = reset();
		mengde2 = reset();
		result = reset();
	}
	
	@Test
	public final void fellesUnion() {
		
		mengde1.leggTil(e0);
		mengde1.leggTil(e1);
		mengde2.leggTil(e1);
		mengde2.leggTil(e3);
		
		result.leggTilAlle(mengde1);
		result.leggTilAlle(mengde2);
		
		assertEquals(result, mengde1.union(mengde2));
	}
	
	@Test
	public final void fellesSnitt() {
		
		mengde1.leggTil(e0);
		mengde1.leggTil(e1);
		mengde1.leggTil(e2);
		mengde2.leggTil(e2);
		mengde2.leggTil(e3);
		
		result.leggTil(e2);
		
		assertEquals(result, mengde1.snitt(mengde2));
	}
	
	@Test
	public final void fellesDifferens() {
		
		mengde1.leggTil(e0);
		mengde1.leggTil(e1);
		mengde1.leggTil(e2);
		mengde1.leggTil(e3);
		mengde2.leggTil(e1);
		mengde2.leggTil(e3);
		
		result.leggTil(e2);
		result.leggTil(e0);
		
		assertEquals(result, mengde1.differens(mengde2));
	}
	
	@Test
	public final void disUnion() {
		
		mengde1.leggTil(e0);
		mengde1.leggTil(e1);
		mengde2.leggTil(e2);
		mengde2.leggTil(e3);
		
		result.leggTilAlle(mengde1);
		result.leggTilAlle(mengde2);
		
		assertEquals(result, mengde1.union(mengde2));
	}
	
	@Test
	public final void disSnitt() {
		
		mengde1.leggTil(e0);
		mengde1.leggTil(e3);
		mengde2.leggTil(e2);
		mengde2.leggTil(e1);
		
		assertEquals(result, mengde1.snitt(mengde2));
	}
	
	@Test
	public final void disDifferens() {
		
		mengde1.leggTil(e0);
		mengde1.leggTil(e1);
		mengde1.leggTil(e2);
		mengde2.leggTil(e3);
		
		result.leggTilAlle(mengde1);
		
		assertEquals(result, mengde1.differens(mengde2));
	}
}
