package test;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import exception.InvalidFastaFileException;
import reader.FastaReader;

public class FastaReaderTest {

	@Test
	public void testFastaReader() {
		FastaReader fr;
		try {
			fr = new FastaReader("ADNtest.fasta");
			assertNotNull(fr);
			assertNotNull(fr.getFilename());
		} catch (IOException e) {
			fail("test failed");
		} catch (InvalidFastaFileException e) {
			fail("test failed");
		}
		
	}


	@Test
	public void testGetChromosome() {
		try {
			FastaReader fr = new FastaReader("ADNtest.fasta");
			assertEquals("bon fichier lu","GATACATATATATAGATA", fr.readSequence());
			assertEquals("bon fichier lu","TATATA", fr.readSequence());
		} catch (IOException e) {
			fail("test failed");
		} catch (InvalidFastaFileException e) {
			fail("test failed");
		}
	}

}
