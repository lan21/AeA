package test;

import static org.junit.Assert.*;

import org.junit.Test;

import adn.ADN;

public class ADNTest {

	@Test
	public void brin_reverse_test() {
		ADN myADN = new ADN("ATCG".toCharArray());
		char[] resultat = myADN.brin_reverse();
		assertArrayEquals("GCTA".toCharArray(), resultat);
	}

	@Test
	public void complementaire_test() {
		ADN myADN = new ADN("AGCT".toCharArray());
		char[] resultat = null;
		try {
			resultat = myADN.complementaire();
		} catch (Exception e) {

		}
		assertArrayEquals("TCGA".toCharArray(), resultat);
	}

	@Test
	public void reverse_complementaire_test() {
		ADN myADN = new ADN("ACTG".toCharArray());
		char[] resultat = null;
		try {
			resultat = myADN.reverse_complementaire();
		} catch (Exception e) {

		}
		assertArrayEquals("CAGT".toCharArray(), resultat);
	}

	@Test
	public void complementaire_exception_test() {
		ADN myADN = new ADN("RERE".toCharArray());
		try {
			myADN.complementaire();
			fail("expected InvalidADNException");
		} catch (Exception e) {

		}
	}

	@Test
	public void reverse_complementaire_exception_test() {
		ADN myADN = new ADN("RERE".toCharArray());
		try {
			myADN.reverse_complementaire();
			fail("expected InvalidADNException");
		} catch (Exception e) {

		}
	}

}
