package main;

import java.io.IOException;

import exception.InvalidFastaFileException;
import reader.FastaReader;
import algorithme.KMP;

public class ReadAdnFasta {

	public static void main(String[] args) {
		try {
			FastaReader fr = new FastaReader("adn.fasta");
			String motif1 = "TAC";
			String sequence = fr.readSequence();
			System.out.println("Séquence d'adn : "+sequence);
			System.out.println("Motif : "+motif1);
			System.out.print("Indices des occurences : ");
			System.out.println(KMP.listeIndiceOccurence(motif1, sequence));
		} catch (IOException e) {
			System.out.println("Erreur : "+e.getMessage());
		} catch (InvalidFastaFileException e) {
			System.out.println("Erreur : "+e.getMessage());
		}		

	}

}
