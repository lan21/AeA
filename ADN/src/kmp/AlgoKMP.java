package kmp;

public class AlgoKMP {
	private String texte;
	private String motif;
	
	/**
	 * initialise un algo KMP avec les données entrés
	 * @param texte
	 * @param motif
	 */
	public AlgoKMP(String texte,String motif){
		this.texte = texte;
		this.motif = motif;
	}
	
	/**
	 * retourne un tableau de next pour le motif m
	 * @return
	 */
	public static int[] next(String motif){
		return null;
	}
}
