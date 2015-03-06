package algo;






/**
 * detecte si une séquence est un préMIARN
 * @author rakotoarivony
 *
 */
public class RecherchePreMiARN {
	private static final int NB_APPARIEMENT_MIN = 48;
	
	public String s1;
	public String s2;
	public String link;

	protected int[][] tabScore;
	
	private static int scoreGap = -2;
	
	private static final int WKO = -1;
	private static final int WOK = 2;

	private static final int TAILLE_MIN_PREMIARN = 70;
	
	public int nombreAlignement;
	
	public int gapMaxLength = 0;
	public int nbPrincipalGapFound = 0;
	public int currentGapSize = 0;
	public int indiceGapPrincipal;
	
	public int[][] tabSubstitution = {  {WKO,WOK,WKO,WKO},
										{WOK,WKO,WOK,WKO},
										{WKO,WOK,WKO,WOK},
										{WKO,WKO,WOK,WKO} }; //tableau de score de substition. Pour les indices : A=0;U=1;G=2;C=3
	
	
	/*public int[][] tabSubstitution = {  {WOK,WKO,WKO,WKO},
										{WKO,WOK,WKO,WKO},
										{WKO,WKO,WOK,WKO},
										{WKO,WKO,WKO,WOK} };*/
	protected Remontee[][] tabRemontee;
	
	/**
	 * contient le nombre d'alignement successif max entre tous les sous-mots de sequence1 et sequence2
	 */
	protected boolean[][] isAlignement;
	
	protected String sequence1;
	protected String sequence2;

	/**
	 * indice du début de prémiARN s'il y en a un
	 */
	private int indiceDebut;

	private int indiceFin;
	
	public RecherchePreMiARN(String seq1,String seq2) {
		this.sequence1 = seq1;
		this.sequence2 = seq2;
		
		tabScore = new int[sequence1.length()+1][sequence2.length()+1];
		isAlignement = new boolean[sequence1.length()+1][sequence2.length()+1];
		
		tabRemontee = new Remontee[sequence1.length()+1][sequence2.length()+1];
		
		this.nombreAlignement = 0;
		
		this.initTableaux();
		
	}
	
	private void initTableaux(){
		this.tabRemontee[0][0] = null;
		for (int i = 1; i < tabScore.length; i++) {
			tabScore[i][0] = 0;
			isAlignement[i][0] = false;
			tabRemontee[i][0] = new Remontee(i-1,0,tabRemontee);
		}
		for (int j = 1; j < tabScore[0].length; j++) {
			tabScore[0][j] = 0;
			isAlignement[0][j] = false;
			tabRemontee[0][j] = new Remontee(0,j-1,tabRemontee);
		}
		tabScore[0][0] = 0;
		isAlignement[0][0] = false;
	}

	void executeAlgo() {
		for (int i = 1; i < tabScore.length; i++) {
			for (int j = 1; j < tabScore[0].length; j++) {
				int diag = tabScore[i - 1][j - 1];
				int haut = tabScore[i - 1][j];
				int gauche = tabScore[i][j - 1];
				int scoreSub = this.w(sequence1.charAt(i - 1),sequence2.charAt(j - 1));
				// if (nbAlignementSuccessif[i-1][j-1]>+3)
				// si le i,j actuel est un alignemnt
				// si le i,j actuel vient de la diagonale, vérifier
				// si la diag est un alignement,
				// si non vérifier que les deux suivants sont des alignemnts. si
				// ce n'est pas le cas recalculer mais en supprimant le cas que
				// c'est un alignemnt
				// sinon, vérifier que les deux suivants sont des alignements
				
				tabScore[i][j] = max(diag + scoreSub, haut + scoreGap, gauche+ scoreGap, i, j);
				if (scoreSub == 2 && i >= 1 && j >= 1) { // si i et j alignement
					if ((tabRemontee[i][j].i == i-1 && tabRemontee[i][j].j == j-1 && isAlignement[i-1][j-1])){
						isAlignement[i][j] = true;
					}
					else if ((tabRemontee[i][j].i == i-1 && tabRemontee[i][j].j == j-1 && !isAlignement[i-1][j-1])
							// si vient de la diag et
							// le précédent n'est pas un alignement donc cet
							// alignemnt doit être suivi de deux alignements
							// successifs
							// s'il veut être pris comme alignemnt
							|| (tabRemontee[i][j].i != i - 1 || tabRemontee[i][j].j != j - 1)) { // ou ne vient pas de la diag
						try {
							int scoreSimilitudeSuivant = w(sequence1.charAt(i),sequence2.charAt(j));
							int scoreSimilitudeSuivant2 = w(sequence1.charAt(i + 1),sequence2.charAt(j + 1));
							if ((scoreSimilitudeSuivant != WOK)|| (scoreSimilitudeSuivant2 != WOK)) {
								tabScore[i][j] = max(Integer.MIN_VALUE / 2,haut + scoreGap, gauche + scoreGap, i,j);
								isAlignement[i][j] = false;
							}
							else{
								isAlignement[i][j] = true;
							}
						} catch (StringIndexOutOfBoundsException e) {
							tabScore[i][j] = max(Integer.MIN_VALUE / 2, haut+ scoreGap, gauche + scoreGap, i, j);
							isAlignement[i][j] = false;
						}
					}
				}
			}
		}

	}

	/**
	 * calcule le max entre diag, haut et gauche. Si ils sont tous négatifs, renvoie 0
	 * Selon le max calculé, le tableau de remonté est complété en prenant pour provenance 
	 * 		i-1,j-1 si diag est max
	 * 		i-1,j si haut est max
	 * 		i,j-1 si gauche est max
	 * @param diag score en haut à gauche dans tabScore + score de substitution 
	 * @param haut score en haut dans tabScore + score de gap
	 * @param gauche score à gauche dans tabScore + score de gap
	 * @param i indice i du score max calculé dans tabScore
	 * @param j indice j du score max calculé dans tabScore
	 * @return score max entre diag,haut et gauche ou 0 si tous négatifs
	 */
	private int max(int diag, int haut, int gauche,int i,int j){
		if(diag>haut){
			if(diag>gauche){
				tabRemontee[i][j] = new Remontee(i-1, j-1, tabRemontee);
				return Math.max(0,diag);
			}
			else {
				tabRemontee[i][j] = new Remontee(i, j-1, tabRemontee);
				return Math.max(0,gauche);
			}
		}
		else{
			if(haut>gauche){
				tabRemontee[i][j] = new Remontee(i-1, j, tabRemontee);
				return Math.max(0,haut);
			}
			else {
				tabRemontee[i][j] = new Remontee(i, j-1, tabRemontee);
				return Math.max(0,gauche);
			}
		}
	}
	
	/**
	 * permet de remonter le tableau de score et d'avoir les alignements des séquences
	 * @param b si true affiche les liaisons et le nombre d'appariement trouvé
	 */
	public void remonte(boolean b){
		s1 = "";
		link = "";
		s2 = "";
		int indiceMax[] = maxTabScore();
		int i = indiceMax[0];
		int j = indiceMax[1];
		this.indiceFin = i;
		this.indiceDebut = sequence1.length()-i;
		int nbAlignement = 0;
		Remontee remontee = tabRemontee[i][j];
		this.currentGapSize = 0;
		this.gapMaxLength = 0;
		this.indiceGapPrincipal = 0;
		this.nbPrincipalGapFound = 0;
		
		//System.out.println(remontee+"à l'indice "+i+","+j);
		/*s2 = s2+sequence2.substring(j);
		for (int k = j; k < sequence2.length(); k++) {
			s1 = s1 + "-";
			link = link + " ";
		}
		s1 = s1+sequence1.substring(i);
		for (int k = i; k < sequence1.length(); k++) {
			s2 = s2 + "-";
			link = link + " ";
		}*/
		while(i>0&&j>0){
			if((remontee.i == i-1)&&(remontee.j == j-1)){
				s1 = this.sequence1.charAt(i-1)+s1;				
				s2 = this.sequence2.charAt(j-1)+s2;
				if(this.isAlignement[i][j]){
					link = "|"+link;
					nbAlignement++;
					if(currentGapSize>3){
						nbPrincipalGapFound++;
						indiceGapPrincipal = i;
						if(currentGapSize>gapMaxLength){
							gapMaxLength = currentGapSize;
						}
					}
					currentGapSize = 0;
				}
				else{
					link = " "+link;
					currentGapSize++;
				}
			}
			if((remontee.i == i)&&(remontee.j == j-1)){
				s1 = "-"+s1;
				link = " "+link;
				s2 = this.sequence2.charAt(j-1)+s2;
				currentGapSize++;
			}
			if((remontee.i == i-1)&&(remontee.j == j)){
				s1 = this.sequence1.charAt(i-1)+s1;
				link = " "+link;
				s2 = "-"+s2;
				currentGapSize++;
			}
			i = remontee.i;
			j = remontee.j;
			remontee = remontee.remonteePrecedente;
		}
		this.nombreAlignement = nbAlignement;
		if(b){
			affichePreMiARN();
		}
	}

	private void affichePreMiARN(){
		System.out.println(s1);
		System.out.println(link);
		System.out.println(s2);
		System.out.println("Nombre d'appariement ="+this.nombreAlignement);
		System.out.println("Taille de gap max :"+gapMaxLength);
		System.out.println("Nbre de gap principal :"+nbPrincipalGapFound);
		System.out.println("Indice gap principal : "+indiceGapPrincipal);
		System.out.println("Début de prémiARN = "+this.indiceDebut);
	}
	/**
	 * donne l'indice du max dans tabScore après calcul d'alignement
	 * @return
	 */
	/*private int[] maxTabScore() {
		int i = tabScore.length-1;
		int j = tabScore[0].length-1;				
		int max = tabScore[i][j];
		for (int k = 0; k < tabScore.length; k++) {
			if (max < tabScore[k][tabScore[0].length-1]){
				max = tabScore[k][tabScore[0].length-1];
				i = k;
				j = tabScore[0].length-1;
			}
		}
		for (int l = 0; l < tabScore[0].length; l++) {
			if (max < tabScore[tabScore.length-1][l]){
				max = tabScore[tabScore.length-1][l];
				i = tabScore.length-1;
				j = l;
			}
		}
		//System.out.println("Max = "+max+" à l'indice :"+i+","+j);
		return new int[]{i,j};
	}*/
	
	private int[] maxTabScore() {
		int i = tabScore.length-1;
		int j = tabScore[0].length-1;				
		int max = tabScore[i][j];
		for (int k = 0; k < tabScore.length; k++) {
			for (int k2 = 0; k2 < tabScore[0].length; k2++) {
				if (max<tabScore[k][k2]){
					max = tabScore[k][k2];
					i = k;
					j = k2;
				}
			}
		}
		//System.out.println("Max = "+max+" à l'indice :"+i+","+j);
		return new int[]{i,j};
	}

	/**
	 * donne le score de la substitution de c1 par c2
	 * @param c1 un caractère genomique de l'ARN (A,U,G ou C)
	 * @param c2 un caractère genomique de l'ARN (A,U,G ou C)
	 * @exception ArrayIndexOutOfBoundsException si c1 ou c2 ne sont pas les caractères A,U G ou C
	 * 
	 */
	private int w(char c1, char c2) {
		return this.tabSubstitution[getIndice(c1)][getIndice(c2)];
	}
	
	/**
	 * Retourne l'indice de c dans le tableau de substiution
	 * @param c  un carctère génomique de l'ARN
	 * @return -1 si c n'est pas un caractère génomique de l'ARN
	 * 			0 si c = 'A'
	 * 			1 si c = 'U'
	 * 			2 si c = 'G'
	 * 			3 si c = 'C'
	 */
	private int getIndice(char c) {
		switch (c) {
		case 'A':
			return 0;
		case 'U':
			return 1;
		case 'G':
			return 2;
		case 'C':
			return 3;
		default:
			return -1;
		}
	}
	
	private void imprimerTab(int[][] a){
		System.out.printf("%8c",' ');
		for (int j = 0; j < sequence2.length(); j++) {
			System.out.printf("%4c",sequence2.charAt(j));
		}
		System.out.println();
		for (int i = 0; i < a.length; i++) {
			if (i==0)
				System.out.printf("%4c",' ');
			else
				System.out.printf("%4c",sequence1.charAt(i-1));
			for (int j = 0; j < a[0].length; j++) {
				System.out.printf("%4d",a[i][j]);
			}
			System.out.println();
		}
	}
	
	private void imprimerTab(boolean[][] a){
		System.out.printf("%12c",' ');
		for (int j = 0; j < sequence2.length(); j++) {
			System.out.printf("%6c",sequence2.charAt(j));
		}
		System.out.println();
		for (int i = 0; i < a.length; i++) {
			if (i==0)
				System.out.printf("%6c",' ');
			else
				System.out.printf("%6c",sequence1.charAt(i-1));
			for (int j = 0; j < a[0].length; j++) {
				System.out.printf("%6b",a[i][j]);
			}
			System.out.println();
		}
	}
	
	private void imprimerRemonte(Remontee[][] a){
		for (int i = 1; i < a.length; i++) {
			for (int j = 1; j < a[0].length; j++) {
				System.out.printf("%10s",a[i][j].toString());
			}
			System.out.println();
		}
	}
	
	/**
	 * imprime tous les tableaux de score d'alignement ainsi que les tableaux de remontée
	 */
	public void imprimerTableaux(){
		System.out.println("Impression du tableau A");
		imprimerTab(tabScore);
		
		/*System.out.println("Impression de remontee A");
		imprimerRemonte(tabRemontee);*/
	}
	
	/**
	 * retourne vrai si cette recherche a trouvé un prémiARN
	 * A utiliser après avoir executé et remonté l'algo
	 * @return
	 */
	public boolean aTrouvePreMiARNPotentiel(){
		return ((this.nombreAlignement>=NB_APPARIEMENT_MIN) && (this.gapMaxLength<=8) && (this.nbPrincipalGapFound<=1) && (this.sequence1.length()-this.indiceDebut>=TAILLE_MIN_PREMIARN));
	}
	
	public boolean aTrouvePreMiARN(){
		return (this.nombreAlignement>=NB_APPARIEMENT_MIN/2);
	}
	
	/**
	 * essaie de trouver un preMiARN en executant l'algo
	 * @param b si true affiche le priMeARN trouvé
	 * @return true si trouve un préMiARN
	 */
	public boolean trouvePreMiARN(boolean b){
		this.executeAlgo();
		this.remonte(false);
		if(this.aTrouvePreMiARNPotentiel()){
			String sprime1 = sequence1.substring(0, this.indiceGapPrincipal);
			String sprime2 = new StringBuilder(sequence1.substring(this.indiceGapPrincipal+this.gapMaxLength, sequence1.length())).reverse().toString();
			RecherchePreMiARN algoprime = new RecherchePreMiARN(sprime1, sprime2);
			algoprime.executeAlgo();
			algoprime.remonte(false);
			if(algoprime.aTrouvePreMiARN()&&b)
				this.affichePreMiARN();
			return algoprime.aTrouvePreMiARN();
		}
		return false;
	}
	
	public static void main(String[] args) {
		String s1 = "GUCAGAAUAAUGUCAAAGUGCUUACAGUGCAGGUAGUGAUAUGUGCAUCUACUGCAGUGAAGGCACUUGUAGCAUUAUGGUGAC";//"UGCUUCCGGCCUGUUCCCUGAGACCUCAAGUGUGAGUGUACUAUUGAUGCUUCACACCUGGGCUCUCCGGGUACCAGGACGGUUUGAGCAAUUC";//ACUAUUG
		//String s1 = "ACUAAUG";
		String s2 = new StringBuilder(s1).reverse().toString();
		//String s2 = "GAUCACUUCCAUGGCAGUA";	
		RecherchePreMiARN algo = new RecherchePreMiARN(s1, s2);
		algo.executeAlgo();
		//algo.imprimerTableaux();
		//algo.imprimerRemonte(algo.tabRemontee);
		//algo.imprimerTab(algo.isAlignement);
		algo.remonte(true);
		String sprime1 = s1.substring(0, algo.indiceGapPrincipal);
		String sprime2 = new StringBuilder(s1.substring(algo.indiceGapPrincipal+algo.gapMaxLength, s1.length())).reverse().toString();
		RecherchePreMiARN algoprime = new RecherchePreMiARN(sprime1, sprime2);
		algoprime.executeAlgo();
		algoprime.remonte(true);
		System.out.println("A trouvé un premiARN : "+ algo.aTrouvePreMiARN());
		System.out.println("Début de prémiARN = "+algo.indiceDebut);
		System.out.println("Fin de prémiARN = "+algo.indiceFin);
	}
}
