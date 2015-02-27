package lettresauteur.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Graphe2 {
	protected String[] mot;
	protected List<Integer> listeSucc[];
	protected int nb;
	protected Boolean dejaVu[];
	protected int pere[];

	@SuppressWarnings("unchecked")
	public Graphe2(String[] lesMots) {
		this.nb = lesMots.length;
		this.mot = new String[nb];
		this.listeSucc = new List[nb];
		this.dejaVu = new Boolean[nb];
		this.pere = new int[nb];
		for (int i = 0; i < nb; i++) {
			this.mot[i] = lesMots[i];
			this.listeSucc[i] = new ArrayList<Integer>();
			this.dejaVu[i] = false; // tous les sommets ne sont pas encore vu
			this.pere[i] = -1;
		}
	}

	static void ajouterArete(Graphe2 g, int s, int d) {
		g.listeSucc[s].add(d);
		g.listeSucc[d].add(s);
	}
	
	static void ajouterArc(Graphe2 g, int s, int d) {
		g.listeSucc[s].add(d);
	}

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

	static void lettreQuiSaute(Graphe2 g) {
		for (int i = 0; i < g.nb; i++) {
			for (int j = i + 1; j < g.nb; j++) {
				if (Graphe2.diffUneLettre(g.mot[i], g.mot[j]))
					ajouterArete(g, i, j);
			}
		}
	}

	/**
	 * parcours en profondeur du graphe g à partir du sommet numéro x
	 * 
	 * @param g
	 *            graphe
	 * @param x
	 *            numéro de sommet
	 */
	public static void DFS(Graphe2 g, int x) {
		g.dejaVu[x] = true;
		System.out.print(g.mot[x] + " ");
		// pour chaque succeseur
		for (Integer j : g.listeSucc[x]) {
			if (!g.dejaVu[j]) {
				Graphe2.DFS(g, j);
			}
		}
	}

	/**
	 * parcours en profondeur du graphe g à partir du sommet numéro x et marquage du père
	 * 
	 * @param g
	 *            graphe
	 * @param x
	 *            numéro de sommet
	 * @param p
	 *            numéro du père
	 */
	public static void DFS(Graphe2 g, int x, int p) {
		g.dejaVu[x] = true;
		g.pere[x] = p;
		//System.out.print(g.mot[x] + ",indice = "+p);
		// pour chaque succeseur
		for (Integer j : g.listeSucc[x]) {
			if (!g.dejaVu[j]) {
				Graphe2.DFS(g, j, x);
			}
		}
	}
	
	/**
	 * parcours en largeur du graphe à partir du sommet x
	 * @param g graphe
	 * @param x le sommet de départ
	 */
	public static void BFSIteratif(Graphe2 g, int x) {
		Queue<Integer> file = new ConcurrentLinkedQueue<Integer>();
		g.dejaVu[x] = true;
		g.pere[x] = -1;
		file.add(x);
		while (!file.isEmpty()) {
			int tete = file.poll();
			//	System.out.print(g.mot[tete] + " ");
			// ajouter tous les successeurs de x dans la file
			for (Integer j : g.listeSucc[tete]) {
				if (!g.dejaVu[j]) {
					g.dejaVu[j] = true;
					g.pere[j] = tete;
					file.add(j);
				}
			}
		}
	}
	

	/**
	 * réinitialise le parcours de ce graphe. Tous les sommet redeviennent
	 * non-vu
	 */
	public void resetParcours() {
		for (int i = 0; i < nb; i++) {
			this.dejaVu[i] = false; // tous les sommets ne sont pas encore vu
			this.pere[i] = -1;
		}
	}

	/**
	 * retourne les successeurs de chaque sommets de mots
	 * 
	 * @return
	 */
	public List<Integer>[] getListeSucc() {
		return listeSucc;
	}

	/**
	 * retourne les sommets de mot de ce graphe
	 * 
	 * @return
	 */
	public String[] getMot() {
		return mot;
	}

	/**
	 * affiche le graphe g
	 * 
	 * @param g
	 */
	public static void affiche(Graphe2 g) {
		for (int i = 0; i < g.nb; i++) {
			System.out.print(g.mot[i] + "->");
			for (Integer j : g.listeSucc[i]) {
				System.out.print(g.mot[j] + ",");
			}
			System.out.println();
		}
	}

	/**
	 * recherche un chemin de la chaine from à la chaine to dans le graphe g en utilisant un parcours en profondeur
	 * 
	 * @param g
	 *            le graphe
	 * @param from
	 *            la chaine de départ
	 * @param to
	 *            la chaine d'arrivée
	 */
	public static void chemin(Graphe2 g, String from, String to) {
		int indiceFrom = Graphe2.indice(from, g.mot);
		int indiceTo = Graphe2.indice(to, g.mot);
		Graphe2.DFS(g, indiceFrom, -1);
		if(g.pere[indiceTo]==-1){
			System.out.println("Il n'y a pas de chemin de "+from+" à "+to);
		}
		else{
			int indice = indiceTo;
			while(indice != indiceFrom){
				System.out.print(g.mot[indice]+"<-");
				indice = g.pere[indice];
			}
			System.out.println(g.mot[indice]);
		}
		g.resetParcours();
	}
	
	/**
	 * recherche un chemin de la chaine from à la chaine to dans le graphe g en utilisant un parcours en largeur
	 * 
	 * @param g
	 *            le graphe
	 * @param from
	 *            la chaine de départ
	 * @param to
	 *            la chaine d'arrivée
	 */
	public static void cheminBFS(Graphe2 g, String from, String to) {
		int indiceFrom = Graphe2.indice(from, g.mot);
		int indiceTo = Graphe2.indice(to, g.mot);
		Graphe2.BFSIteratif(g, indiceFrom);
		if(g.pere[indiceTo]==-1){
			System.out.println("Il n'y a pas de chemin de "+from+" à "+to);
		}
		else{
			int indice = indiceTo;
			while(indice != indiceFrom){
				System.out.print(g.mot[indice]+"<-");
				indice = g.pere[indice];
			}
			System.out.println(g.mot[indice]);
		}
		g.resetParcours();
	}

	/**
	 * recherche l'indice de la chaine m dans le tableau de mot tabMots
	 * 
	 * @param m
	 * @param tabMots
	 * @return
	 */
	public static int indice(String m, String[] tabMots) {
		for (int i = 0; i < tabMots.length; ++i)
			if (m.equals(tabMots[i]))
				return i;
		throw new Error(m + " n'est pas dans le tableau.");
	}

	/**
	 * Methode principale Initialise un graphe avec un dictionnaire des mots de 3/4 lettres 
	 * Affiche le graphe 
	 * Affiche toutes les composantes connexes du graphe grace à un parcours en profondeur
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Graphe2 g = new Graphe2(Dicos.dico4);
		Graphe2.lettreQuiSaute(g);
		affiche(g);
		
		Graphe2.afficheComposanteConnexeDFS(g);		
		
		//Graphe2.afficheComposanteConnexeBFS(g);		
		
		Graphe2.cheminBFS(g, "lion", "peur");
		
	}
	
	public static void afficheComposanteConnexeDFS(Graphe2 g){
		int j = 1;
		// if(!g.dejaVu[i] && (g.mot[i]=="lion" || g.mot[i]=="peur")) pour avoir
		// les composantes de loin et peur
		for (int i = 0; i < g.nb; i++) { // donne toute les composantes connexes
			if (!g.dejaVu[i]) {
				System.out.print(j + ":\t");
				DFS(g, i);
				j++;
				System.out.println();
			}
		}
		g.resetParcours();
	}
	
	public static void afficheComposanteConnexeBFS(Graphe2 g){
		int j = 1;
		// if(!g.dejaVu[i] && (g.mot[i]=="lion" || g.mot[i]=="peur")) pour avoir
		// les composantes de loin et peur
		for (int i = 0; i < g.nb; i++) { // donne toute les composantes connexes
			if (!g.dejaVu[i] ) {
				System.out.print(j + ":\t");
				BFSIteratif(g, i);
				j++;
				System.out.println();
			}
		}
		g.resetParcours();
	}

}
