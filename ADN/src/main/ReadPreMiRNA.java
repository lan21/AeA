package main;

import java.io.IOException;
import java.util.List;

import reader.FastaReader;
import adn.ADN;
import exception.InvalidFastaFileException;

public class ReadPreMiRNA {
	
	/**
	 * imprime la liste des points pour un ensemble de liste d'occurence.
	 * Si plusieurs points sont sur la même occurence d'une liste alors c'est qu'il sont tous équivalent et donc 
	 * pour cahque couple de la liste, il y a un point
	 * @param listeOccurence une liste d'indice des occurences pour un mot donné. Calculé à 
	 */
	private static void printDotForOccurenciesList(List<Integer> listeOccurence){
		int n = listeOccurence.size();
		for (int i = 0; i < n; i++) {
			for (int j = i+1; j < n; j++) {
				System.out.println(listeOccurence.get(i)+"\t"+listeOccurence.get(j));
				System.out.println(listeOccurence.get(j)+"\t"+listeOccurence.get(i));
			}			
		}
	}
	
	private static void usage(){
		System.out.println("Usage : main.ReadAdnFasta <number> <filename>");
		System.out.println("\t<number> : integer which represent the wanted length of words");
		System.out.println("\t<filename> : name of a fasta file");
	}

	public static void main(String[] args) {
		try {
			int longueur = Integer.parseInt(args[0]);
			//int longueur = 4;
			//FastaReader fr = new FastaReader("ADNtest.fasta");
			FastaReader fr = new FastaReader(args[1]);
			ADN motif;			
			String sequenceStr = fr.readSequence();
			ADN sequence = new ADN(sequenceStr);
			int n = sequenceStr.length()-(longueur-1);
			List<Integer> occurences;
			boolean[] matched = new boolean[n]; //tableau qui permet de savoir si l'indice de la séquence considéré a déjà des équivalents
			for(int i=0;i<n;i++){
				if(!matched[i]){
					motif = new ADN(sequenceStr.substring(i, i+longueur));
					/*System.out.println("Séquence d'adn : " + new String(sequence.getBrin()));
					System.out.println("Motif : " + new String(motif.getBrin()));
					System.out.print("Indices des occurences : ");*/
					occurences = (motif.occurenceReverseComplementaire(sequence));
					for (int j : occurences) {
						matched[j] = true;
					}
					//System.out.println(occurences);
					printDotForOccurenciesList(occurences);					
				}
			}
		
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
