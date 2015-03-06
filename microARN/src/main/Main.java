package main;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import algo.RecherchePreMiARN;
import reader.FastaReader;
import exception.InvalidFastaFileException;

/**
 * se charge de lire un fichier fasta et de détecter tous les préMiARN dans ce fichier
 * @author rakotoarivony
 *
 */
public class Main {
	private static void usage(){
		System.out.println("Usage : main.ReadAdnFasta <number> <filename>");
		System.out.println("\t<filename> : name of a fasta file");
	}

	public static void main(String[] args) {
		try {
			//int longueur = 4;
			FastaReader fr = new FastaReader("data-mirna/hsa-miR-17-5p_preMI_ARN.fasta");
			//FastaReader fr = new FastaReader(args[1]);
			String allSequence = fr.readSequence();
			int i = 0;
			int length = allSequence.length();
			String sequence;
			String reverseSequence;
			List<Integer> listeIndicePreMiARN = new LinkedList<Integer>();
			RecherchePreMiARN recherche;
			while (i+100<length){
				sequence = allSequence.substring(i, i+100);
				reverseSequence = new StringBuilder(sequence).reverse().toString();
				recherche = new RecherchePreMiARN(sequence, reverseSequence);
				if(recherche.trouvePreMiARN(true)){
					listeIndicePreMiARN.add(i);
					i= i+ 100;
					System.out.println("PréMiARN trouvé entre "+i+" à "+(i+100));
					System.out.println();
				}
				else{
					i++;
				}
			}
			sequence = allSequence.substring(i, length);
			System.out.println(sequence);
			reverseSequence = new StringBuilder(sequence).reverse().toString();
			recherche = new RecherchePreMiARN(sequence, reverseSequence);
			if(recherche.trouvePreMiARN(true)){
				listeIndicePreMiARN.add(i);
				System.out.println("PréMiARN trouvé entre "+i+" à "+length);
				System.out.println();
			}
			System.out.println("Nombre de préMiARN trouvé = "+listeIndicePreMiARN.size());
			
		
		} catch (ArrayIndexOutOfBoundsException e) {
			usage();
		} catch (NumberFormatException e) {
			usage();
		} catch (IOException e) {
			System.out.println("Erreur : " + e.getMessage());
		} catch (InvalidFastaFileException e) {
			System.out.println("Erreur : " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Error : " + e.getMessage());
			;
		}

	}
}
