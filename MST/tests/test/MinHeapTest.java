package test;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

import Algorithm.MinHeap;

public class MinHeapTest {


	@Test
	public void testAjouter() {
		MinHeap<Integer> tas = new MinHeap<Integer>();
		Integer tasTableau[] = new Integer[4];
		tas.add(12,12);
		tas.add(20,20);
		tas.add(8,8);
		tas.add(9,9);
		tasTableau[0] = 8;
		tasTableau[1] = 9;
		tasTableau[2] = 12;
		tasTableau[3] = 20;
		assertArrayEquals(tasTableau, tas.toArray());		
	}
	
	@Test
	public void testRetirerRacine() {
		MinHeap<Integer> tas = new MinHeap<Integer>();
		tas.add(12,12);
		tas.add(20,20);
		tas.add(8,8);
		tas.add(9,9);
		assertEquals(new Integer(8), tas.poll());
		assertEquals(new Integer(9), tas.poll());
		assertEquals(new Integer(12), tas.poll());
		assertEquals(new Integer(20), tas.poll());
	}
	
	@Test
	public void testRetirer2() {
		MinHeap<Integer> tas = new MinHeap<Integer>();
		tas.add(1,1);
		tas.add(10,10);
		tas.add(2,2);
		tas.add(16,16);
		tas.add(17);
		assertEquals(new Integer(1), tas.poll());
		assertEquals(new Integer(2), tas.poll());
		assertEquals(new Integer(10), tas.poll());
		assertEquals(new Integer(16), tas.poll());
		assertEquals(new Integer(17), tas.poll());

	}
	
	@Test
	public void testRetirer() {
		MinHeap<Integer> tas = new MinHeap<Integer>();
		tas.add(12,12);
		tas.add(20,20);
		tas.add(8,8);
		tas.add(9,9);
		Integer tasTableau[] = new Integer[3];
		tasTableau[0] = 8;
		tasTableau[1] = 9;
//		tasTableau[2] = 12;
		tasTableau[2] = 20;
		assertTrue(tas.remove(12));
		assertArrayEquals(tasTableau, tas.toArray());		
		assertEquals(new Integer(8), tas.poll());
		assertEquals(new Integer(9), tas.poll());
		assertEquals(new Integer(20), tas.poll());
	}
	
	@Test
	public void testReplace() {
		MinHeap<String> tas = new MinHeap<String>();
		tas.add("a",12);
		tas.add("b",20);
		tas.add("c",6);
		tas.add("d",9);
		String tasTableau[] = new String[4];
		tasTableau[0] = "c";
		tasTableau[1] = "d";
		tasTableau[2] = "a";
		tasTableau[3] = "b";
//		System.out.println(Arrays.toString(tas.toArray()));
		assertArrayEquals(tasTableau, tas.toArray());
		tas.setValue("d",21);
		tasTableau[0] = "c";
		tasTableau[1] = "b";
		tasTableau[2] = "a";
		tasTableau[3] = "d";
		System.out.println(Arrays.toString(tas.toArray()));
		assertArrayEquals(tasTableau, tas.toArray());
	}
	

}
