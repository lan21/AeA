package test;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import exception.InvalidDNAException;
import algorithme.ShiftOr;

public class ShiftOrTest {

	@Test
	public void decalage_test() {
		boolean tab[] = { false, false, true };
		boolean expected[] = { true, false, false };
		ShiftOr s = new ShiftOr("AAA".toCharArray(), "AA".toCharArray());
		boolean resultat[] = s.decalage(tab);
		Assert.assertTrue(Arrays.equals(expected, resultat));
	}

	@Test
	public void AND_test() {
		boolean tab1[] = { false, true, true };
		boolean tab2[] = { true, false, true };
		boolean excpected[] = { false, false, true };
		ShiftOr s = new ShiftOr("AAA".toCharArray(), "AA".toCharArray());
		boolean resultat[] = null;
		try {
			resultat = s.AND(tab1, tab2);
		} catch (Exception e) {
		}
		Assert.assertTrue(Arrays.equals(excpected, resultat));
	}

	@Test
	public void AND_exception_test() {
		boolean tab1[] = { false, true, true };
		boolean tab2[] = { true, false };
		ShiftOr s = new ShiftOr("AAA".toCharArray(), "AA".toCharArray());
		try {
			s.AND(tab1, tab2);
			fail("ArrayLengthException expected!");
		} catch (Exception e) {
		}
	}

	@Test
	public void genererLesVerctorsU_test() {
		boolean A[] = { true, false, false };
		boolean T[] = { false, true, false };
		boolean G[] = { false, false, true };
		boolean C[] = { false, false, false };
		ShiftOr s = new ShiftOr("CATAGA".toCharArray(), "ATG".toCharArray());
		try {
			s.genererLesVerctorsU();
		} catch (InvalidDNAException e) {
			fail("Exception ADN invalide reçu");
		}
		Assert.assertTrue(Arrays.equals(A, s.U_A));
		Assert.assertTrue(Arrays.equals(T, s.U_T));
		Assert.assertTrue(Arrays.equals(G, s.U_G));
		Assert.assertTrue(Arrays.equals(C, s.U_C));
	}

	@Test
	public void genererLesVerctorsU_exception_test() {
		ShiftOr s = new ShiftOr("CATAGA".toCharArray(), "ABG".toCharArray());
		try {
			s.genererLesVerctorsU();
			fail("InvalidADNException expected!");
		} catch (Exception e) {
		}
	}

	@Test
	public void remplireMatrice_test() throws Exception {
		ShiftOr s = new ShiftOr("CATG".toCharArray(), "AT".toCharArray());
		s.genererLesVerctorsU();
		s.remplireMatrice();
		boolean[][] expected = { { false, false }, { true, false },
				{ false, true }, { false, false } };

		for (int i = 0; i < expected.length; i++) {
			Assert.assertTrue(Arrays.equals(expected[i], s.matrice[i]));

		}
	}

	@Test
	public void remplireMatrice_exception_test() {
		ShiftOr s = new ShiftOr("FATAGA".toCharArray(), "AGG".toCharArray());
		try {
			s.genererLesVerctorsU();
			s.remplireMatrice();
			fail("InvalidADNException expected!");
		} catch (Exception e) {
		}
	}

}
