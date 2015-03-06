package microARN.tests;

import static org.junit.Assert.*;
import microARN.util.nucleotide.*;

import org.junit.Test;

public class NucleotideTest {

	@Test
	public void testNucleotide() {
		Nucleotide a= NucleotideA.instance;
		Nucleotide c= NucleotideC.instance;
		Nucleotide g= NucleotideG.instance;
		Nucleotide u= NucleotideU.instance;
		assertNotNull("bien construit",a);
		assertNotNull("bien construit",c);
		assertNotNull("bien construit",g);
		assertNotNull("bien construit",u);
	}

	@Test
	public void testGetCanonicalComplement() {
		Nucleotide a= NucleotideA.instance;
		Nucleotide c= NucleotideC.instance;
		Nucleotide g= NucleotideG.instance;
		Nucleotide u= NucleotideU.instance;
		assertEquals("bon complément canonique",u,a.getCanonicalComplement());
		assertEquals("bon complément canonique",a,u.getCanonicalComplement());
		assertSame("bon complément canonique",u,a.getCanonicalComplement());
		assertNotEquals("bon complément canonique",c,a.getCanonicalComplement());
		assertNotEquals("bon complément canonique",g,u.getCanonicalComplement());
		assertNotEquals("bon complément canonique",null,u.getCanonicalComplement());
	}

	@Test
	public void testGetNonCanonicalComplement() {
		Nucleotide a= NucleotideA.instance;
		Nucleotide c= NucleotideC.instance;
		Nucleotide g= NucleotideG.instance;
		Nucleotide u= NucleotideU.instance;
		assertNull("bon complément canonique",a.getNonCanonicalComplement());
		assertNull("bon complément canonique",c.getNonCanonicalComplement());
		assertEquals("bon complément canonique",g,u.getNonCanonicalComplement());
		assertSame("bon complément canonique",u,g.getNonCanonicalComplement());
	}

	@Test
	public void testIsCanonicalComplement() {
		Nucleotide a= NucleotideA.instance;
		Nucleotide c= NucleotideC.instance;
		Nucleotide g= NucleotideG.instance;
		Nucleotide u= NucleotideU.instance;
		assertTrue("bon complément canonique",a.isCanonicalComplement(u));
		assertTrue("bon complément canonique",c.isCanonicalComplement(g));
		assertFalse("bon complément canonique",u.isCanonicalComplement(g));
		assertFalse("bon complément canonique",g.isCanonicalComplement(a));
		assertFalse("bon complément canonique",u.isCanonicalComplement(u));
		assertFalse("bon complément canonique",g.isCanonicalComplement(g));
		assertFalse("bon complément canonique",g.isCanonicalComplement(null));
	}

	@Test
	public void testIsComplement() {
		Nucleotide a= NucleotideA.instance;
		Nucleotide c= NucleotideC.instance;
		Nucleotide g= NucleotideG.instance;
		Nucleotide u= NucleotideU.instance;
		assertTrue("bon complément canonique",a.isComplement(u));
		assertTrue("bon complément canonique",c.isComplement(g));
		assertTrue("bon complément canonique",u.isComplement(g));
		assertFalse("bon complément canonique",g.isComplement(a));
		assertFalse("bon complément canonique",g.isCanonicalComplement(null));
	}

}
