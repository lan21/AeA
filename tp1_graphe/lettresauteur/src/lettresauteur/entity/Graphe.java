package lettresauteur.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

//note pour moi-même : une classe Parcours aurait été plus intéressante et il contiendrait dejaVu et pere
public class Graphe {
	protected String[] mot;/*mots de ce graphe*/
	protected List<Integer> listeSucc[];/*liste des successeurs pour chaque sommet*/
	protected int nbSommets;
	protected Boolean dejaVu[];/*indique pour chaque sommet s'il a déjà été vu ou pas lors d'un parcours*/
	protected int pere[]; /*est utilisé pour mémoriser les indices de sommets lors de parcours de graphe*/

	@SuppressWarnings("unchecked")
	public Graphe(String[] lesMots) {
		this.nbSommets = lesMots.length;
		this.mot = new String[nbSommets];
		this.listeSucc = new List[nbSommets];
		this.dejaVu = new Boolean[nbSommets];
		this.pere = new int[nbSommets];
		for (int i = 0; i < nbSommets; i++) {
			this.mot[i] = lesMots[i];
			this.listeSucc[i] = new ArrayList<Integer>();
			this.dejaVu[i] = false; // tous les sommets ne sont pas encore vu
			this.pere[i] = -1;
		}
	}

	/**
	 * ajoute une arete entre les sommets s et d dans le graphe g
	 * @param g
	 * @param s
	 * @param d
	 */
	static void ajouterArete(Graphe g, int s, int d) {
		g.listeSucc[s].add(d);
		g.listeSucc[d].add(s);
	}
	
	/**
	 * ajoute un arc qui part de s verd d dans le graphe g
	 * @param g le graphe
	 * @param s sommet de départ
	 * @param d sommet d'arrivé
	 */
	static void ajouterArc(Graphe g, int s, int d) {
		g.listeSucc[s].add(d);
	}

	/**
	 * vérifie si deux chaînes a et b sont différents de exactement une lettre
	 * si les deux chaînes sont équivalentes, renvoie faux
	 * @param a première chaîne à comparer
	 * @param b deuxième chaîne à comparer
	 * @return true si a et b sont différents d'une lettre
	 * 		   false sinon
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

	/**
	 * Initialise les arêtes de g.
	 * Il existe une arête entre deux sommets du graphe g si ils sont exactement différents d'une lettre
	 * @param g le graphe dont les arêtes sont à initialiser. Les sommets de g sont déjà initialisé
	 * 			
	 */
	static void lettreQuiSaute(Graphe g) {
		for (int i = 0; i < g.nbSommets; i++) {
			for (int j = i + 1; j < g.nbSommets; j++) {
				if (Graphe.diffUneLettre(g.mot[i], g.mot[j]))
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
	public static void DFS(Graphe g, int x) {
		g.dejaVu[x] = true;
		System.out.print(g.mot[x] + " ");
		// pour chaque succeseur
		for (Integer j : g.listeSucc[x]) {
			if (!g.dejaVu[j]) {
				Graphe.DFS(g, j);
			}
		}
	}

	/**
	 * parcours en profondeur du graphe g à partir du sommet numéro x et marquage du père
	 * 
	 * @param g
	 *            graphe
	 * @param x
	 *            numéro de sommet de départ
	 * @param p
	 *            numéro du père
	 */
	public static void DFS(Graphe g, int x, int p) {
		g.dejaVu[x] = true;
		g.pere[x] = p;
		//System.out.print(g.mot[x] + ",indice = "+p);
		// pour chaque succeseur
		for (Integer j : g.listeSucc[x]) {
			if (!g.dejaVu[j]) {
				Graphe.DFS(g, j, x);
			}
		}
	}
	
	/**
	 * parcours en largeur du graphe à partir du sommet x
	 * @param g graphe
	 * @param x le sommet de départ
	 */
	public static void BFSIteratif(Graphe g, int x) {
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
		for (int i = 0; i < nbSommets; i++) {
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
	 * affiche le graphe g en indiquant pour chaque mot ses successeurs
	 * 
	 * @param g
	 */
	public static void affiche(Graphe g) {
		System.out.println("Affichage du graphe");
		for (int i = 0; i < g.nbSommets; i++) {
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
	public static void chemin(Graphe g, String from, String to) {
		int indiceFrom = Graphe.indice(from, g.mot);
		int indiceTo = Graphe.indice(to, g.mot);
		Graphe.DFS(g, indiceFrom, -1);
		System.out.println("Chemin de "+from+" à "+to);
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
	 * recherche et affiche un chemin de la chaine from à la chaine to dans le graphe g en utilisant un parcours en largeur.
	 * 
	 * @param g
	 *            le graphe
	 * @param from
	 *            la chaine de départ
	 * @param to
	 *            la chaine d'arrivée
	 */
	public static void cheminBFS(Graphe g, String from, String to) {
		int indiceFrom = Graphe.indice(from, g.mot);
		int indiceTo = Graphe.indice(to, g.mot);
		Graphe.BFSIteratif(g, indiceFrom);
		System.out.println("Chemin le plus court de "+from+" à "+to);
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
	 * affiche les composantes connexes du graphe g grâce à un parcours en profondeur
	 * @param g le graphe
	 */
	public static void afficheComposanteConnexeDFS(Graphe g){
		int j = 1;
		// if(!g.dejaVu[i] && (g.mot[i]=="lion" || g.mot[i]=="peur")) pour avoir
		// les composantes de loin et peur
		System.out.println("Composantes connexes du graphe par un parcours en profondeur\n");
		for (int i = 0; i < g.nbSommets; i++) { // donne toute les composantes connexes
			if (!g.dejaVu[i]) {
				System.out.print(j + ":\t");
				DFS(g, i);
				j++;
				System.out.println();
			}
		}
		g.resetParcours();
	}
	
	/**
	 * affiche les composantes connexes du graphe g grâce à un parcours en largeur
	 * @param g le graphe
	 */
	public static void afficheComposanteConnexeBFS(Graphe g){
		int j = 1;
		// if(!g.dejaVu[i] && (g.mot[i]=="lion" || g.mot[i]=="peur")) pour avoir
		// les composantes de loin et peur
		System.out.println("Composantes connexes du graphe par un parcours en largeur\n");
		for (int i = 0; i < g.nbSommets; i++) { // donne toute les composantes connexes
			if (!g.dejaVu[i] ) {
				System.out.print(j + ":\t");
				BFSIteratif(g, i);
				j++;
				System.out.println();
			}
		}
		g.resetParcours();
	}
	
	/**
	 * Initialise un graphe avec un dictionnaire des mots de 4 lettres 
	 * Affiche le graphe
	 * Affiche toutes les composantes connexes du graphe grace à un parcours en profondeur
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Graphe g = new Graphe(Dicos.dico4);
		Graphe.lettreQuiSaute(g);
		
		Graphe.afficheComposanteConnexeDFS(g);		
				
		//Graphe.cheminBFS(g, "lion", "peur");
		
	}

}
