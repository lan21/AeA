package test;

import static org.junit.Assert.*;

import org.junit.Test;

import Algorithm.MinHeap;

public class MinHeapTest {

	@Test
	public void testTasStructure() {
		MinHeap<Integer> tas = new MinHeap<Integer>();
		assertNotNull(tas);
	}

	@Test
	public void testAjouter() {
		MinHeap<Integer> tas = new MinHeap<Integer>();
		Integer tasTableau[] = new Integer[4];
		tas.ajouter(12);
		tas.ajouter(20);
		tas.ajouter(8);
		tas.ajouter(9);
		tasTableau[0] = 8;
		tasTableau[1] = 9;
		tasTableau[2] = 12;
		tasTableau[3] = 20;
		assertArrayEquals(tasTableau, tas.getTas().toArray());		
	}
	
	@Test
	public void testRetirerRacine() {
		MinHeap<Integer> tas = new MinHeap<Integer>();
		tas.ajouter(12);
		tas.ajouter(20);
		tas.ajouter(8);
		tas.ajouter(9);
		assertEquals(new Integer(8), tas.retirerRacine());
		assertEquals(new Integer(9), tas.retirerRacine());
		assertEquals(new Integer(12), tas.retirerRacine());
		assertEquals(new Integer(20), tas.retirerRacine());
	}
	
	@Test
	public void testRetirer2() {
		MinHeap<Integer> tas = new MinHeap<Integer>();
		tas.ajouter(1);
		tas.ajouter(10);
		tas.ajouter(2);
		tas.ajouter(16);
		tas.ajouter(17);
		assertEquals(new Integer(1), tas.retirerRacine());
		assertEquals(new Integer(2), tas.retirerRacine());
		assertEquals(new Integer(10), tas.retirerRacine());
		assertEquals(new Integer(16), tas.retirerRacine());
		assertEquals(new Integer(17), tas.retirerRacine());

	}
	
	@Test
	public void testRetirer() {
		MinHeap<Integer> tas = new MinHeap<Integer>();
		tas.ajouter(12);
		tas.ajouter(20);
		tas.ajouter(8);
		tas.ajouter(9);
		Integer tasTableau[] = new Integer[3];
		tasTableau[0] = 8;
		tasTableau[1] = 9;
//		tasTableau[2] = 12;
		tasTableau[2] = 20;
		assertEquals(new Integer(12), tas.retirer(12));
		assertArrayEquals(tasTableau, tas.getTas().toArray());		
		assertEquals(new Integer(8), tas.retirerRacine());
		assertEquals(new Integer(9), tas.retirerRacine());
		assertEquals(new Integer(20), tas.retirerRacine());
	}
	
	@Test
	public void testReplace() {
		MinHeap<Integer> tas = new MinHeap<Integer>();
		tas.ajouter(12);
		tas.ajouter(20);
		tas.ajouter(6);
		tas.ajouter(9);
		Integer tasTableau[] = new Integer[4];
		tasTableau[0] = 6;
		tasTableau[1] = 7;
		tasTableau[2] = 20;
		tasTableau[3] = 9;
		tas.changeValue(12,7);
		assertArrayEquals(tasTableau, tas.getTas().toArray());
	}
	

}
