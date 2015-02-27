package lettresauteur.entity;

import java.util.List;

public class Mot {
	
	private String value;
	private List<Integer> successeurs;
	private int indice;
	
	public Mot(String string, List<Integer> list, int i) {
		this.value = string;
		this.successeurs = list;
		this.indice = i;
	}
	
	public int getIndice() {
		return indice;
	}
	
	public List<Integer> getSuccesseurs() {
		return successeurs;
	}
	
	public String getValue() {
		return value;
	}
	
	public void ajouterSuccesseur(int d){
		this.successeurs.add(d);
	}
	
	/**
	 * prédicat qui détermine si deux mots sont différentes d'une lettre
	 * @param a premier mot
	 * @param b deuxième mot
	 * @return vrai si diffère d'une lettre, faux sinon
	 */
	static boolean diffUneLettre(String a, String b) {
		// a et b supposees de meme longueur
		int i = 0;
		while (i < a.length() && a.charAt(i) == b.charAt(i))
			++i;
		if (i == a.length())
			return false;
		++i;
		while (i < a.length() && a.charAt(i) == b.charAt(i))
			++i;
		if (i == a.length())
			return true;
		return false;
	}
	
	public boolean diffUnMot(Mot a){
		return diffUneLettre(this.value,a.value);
	}
	
	public static boolean diffUnMot(Mot a,Mot b){
		return diffUneLettre(a.value,b.value);
	}
}
