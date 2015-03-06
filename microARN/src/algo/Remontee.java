package algo;

/**
 * permet de remonter petit à petit le tableau de score d'alignement une fois qu'on a fini d'appliquer l'algo d'alignement sur deux chaines de caractères
 * Il y a un chainage de Remontée qui permet facilement d'effectuer cette remontée
 * @author rakotoarivony
 *
 */
public class Remontee {
	/**
	 * i et j sont les indices du prochain score à regarder dans le tableau de score lors de la remontée
	 */
	
	public int i;
	/**
	 * i et j sont les indices du prochain score à regarder dans le tableau de score lors de la remontée
	 */
	public int j;
	
	/**
	 * la remontée précédente est la remonté qui précède celle-ci. Elle vaut null si on est au début de la remontée
	 */
	public Remontee remonteePrecedente;
	
	/**
	 * construit une instance de Remontee
	 * @param i abcisse dans le tableau de score de l'endroit où il faut remonter
	 * @param j ordonnée dans le tableau de  score de l'endroit où il faut remonter
	 * @param tabRemontee le tableau de remontée qui correspond au tableau de score d'alignement.
	 * 			tabRemontee[i][j] correspondra à la remontée précedente de cette remontée
	 */
	public Remontee(int i,int j,Remontee[][]tabRemontee) {
		this.remonteePrecedente = tabRemontee[i][j];
		this.i = i;
		this.j = j;
	}
	
	public String toString(){
		return this.i+","+this.j;
	}
}
