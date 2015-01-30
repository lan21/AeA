package algorithme;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


public class KMP {
	/**
	 * donne la liste des indices de l'occurence de motif dans texte
	 * @param motif
	 * @param texte
	 * @return
	 */
	public static List<Integer> listeIndiceOccurence(String motif,String texte){
		int i = 0,j = 0;
		int m = motif.length();
		int n = texte.length();
		int next[] = KMP.next(motif);
		List<Integer> listeOccurence = new LinkedList<Integer>();
		if (m==0){
			return listeOccurence;
		}
		while(i+j<n){
			if(texte.charAt(i+j)==motif.charAt(j))
				j++;
			else{
				if(next[j]==-1){
					i = i+j+1;
					j = 0;
				}
				else{
					i = i + j - next[j];
					j = next[j];
				}
			}
			if(j == m){
				listeOccurence.add(i);
				i = i + j - next[j];
				j = next[j];
			}
		}
		return listeOccurence;
	}
	
	/**
	 * retourne un tableau de next du motif
	 * @param motif
	 * @return
	 */
	public static int[] next(String motif){
		int m = motif.length();
		int[] pref = new int[m+1];/*on utilise un tableau de taille m+1*/		
		pref[0] = -1;/*le premier next sera toujours -1*/
		if (m == 0){
			return pref;
		}
		else{			
			int i = 0,j = 1;
			while(j<m){
				if(motif.charAt(i)==(motif.charAt(j))){
					pref[j] = pref[i];
					j++;
					i++;
				}
				else{
					pref[j] = i; /*on reviendra à la dernière position lue qui est i si dans la recherche d'occurence on echoue à cet indice*/
					j++;
					i = 0;
				}
			}
			pref[j] = i;
			return pref;
		}
	}
	
	/**
	 * retourne le nombre d'occurences de motif dans texte
	 * @param motif
	 * @param texte
	 * @return
	 */
	public static int nombreOccurence(String motif,String texte){
		return KMP.listeIndiceOccurence(motif, texte).size();
	}
	
	public static void main(String[] args) {
		System.out.println(Arrays.toString(KMP.next("tatabatataba")));
		System.out.println(Arrays.toString(KMP.next("tttattt")));
		String motif1 = "tactaga";
		String texte1 = "tactagatactaga";
		
		System.out.println(KMP.listeIndiceOccurence(motif1, texte1));
	}
}
