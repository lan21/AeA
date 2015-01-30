package test;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import algorithme.KMP;

public class KMPTest {
	String motif1 = "tactaga";
	String texte1 = "tactagatactaga";
	
	String motif2 = "tata";
	String texte2 = "tatatatata";
	
	String motif3 = "tatac";
	
	String motif4 = "ttt";
	String texte4 = "ttattatt";
	String texte4Bis = "ttattattattt";
	

	@Test
	public void testListeIndiceOccurence() {
		
		List<Integer> list1 = Arrays.asList(0,7);
		
		
		List<Integer> list2 = Arrays.asList(0,2,4,6);
		
		
		List<Integer> listVide = Arrays.asList();
		
		
		List<Integer> list4 = Arrays.asList();
		
		
		assertEquals("Bonnes occurences 1",KMP.listeIndiceOccurence(motif1, texte1),list1);
		assertEquals("Bonnes occurences 2",KMP.listeIndiceOccurence(motif2, texte2),list2);
		assertEquals("Bonnes occurences 3",KMP.listeIndiceOccurence(motif3, texte2),listVide);
		assertEquals("Bonnes occurences 4",KMP.listeIndiceOccurence("", "un texte long"),listVide);
		assertEquals("Bonnes occurences 4",KMP.listeIndiceOccurence("", "un texte long"),listVide);
		assertEquals("Bonnes occurences 5",KMP.listeIndiceOccurence(motif4, texte4),list4);
	}

	@Test
	public void testNext() {
		int t1[] = {-1,0,0,-1,0,2,0,0};
		int t2[] = {-1};
		int t3[] = {-1,0};
		int t4[] = {-1,0,0,-1,0,0,3};
		int t5[] = {-1,0,0,-1,1,0,-1,0,0,-1,1,0,6};
		assertArrayEquals(KMP.next("tactaga"), t1);
		assertArrayEquals(KMP.next(""), t2);
		assertArrayEquals(KMP.next("a"), t3);
		assertArrayEquals(KMP.next("b"), t3);
		assertArrayEquals(KMP.next("tactac"), t4);
		assertArrayEquals(KMP.next("1eD1eD"), t4);
		assertArrayEquals(KMP.next("tagtbatagtba"), t5);
	}

	@Test
	public void testNombreOccurence() {
		assertEquals(KMP.nombreOccurence(motif1, texte1),2);
		assertEquals(KMP.nombreOccurence("", texte1),0);
		assertEquals(KMP.nombreOccurence(motif2, texte2),4);
		assertEquals(KMP.nombreOccurence(motif4, texte4Bis),1);
	}

}
