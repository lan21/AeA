package algo;

import microARN.util.nucleotide.*;

public class AlignementGapAffine {
	int ouv; //pénalité d'ouverture de gap
	int ext; //pénalité d'extension de gap
	
	private static int N = -4;
	private static int G = 4;
	
	int[][] tabSubstitution = { {N,G,N,N},
								{G,N,G,N},
								{N,G,N,G},
								{N,N,G,N} }; //tableau de score de substition. Pour les indices : A=0;U=1;G=2;C=3
	
	/*int[][] tabSubstitution = { {2,-2,-2,-2},
								{-2,2,-2,-2},
								{-2,-2,2,-2},
								{-2,-2,-1,2} };*/
	
	int a[][];//tableau de score d'alignement utilisé pour les gap affines
	int b[][];
	int c[][];
	
	Provenance remonteA[][];//tableau utilisé pour la remonté pour a
	Provenance remonteB[][];//tableau utilisé pour la remonté pour a
	Provenance remonteC[][];//tableau utilisé pour la remonté pour a
	
	String s1;
	String s2;
	
	public AlignementGapAffine(String s1,String s2,int ouv,int ext) {
		this.s1 = s1;
		this.s2 = s2;
		
		a = new int[s1.length()+1][s2.length()+1];
		b = new int[s1.length()+1][s2.length()+1];
		c = new int[s1.length()+1][s2.length()+1];
		
		remonteA = new Provenance[s1.length()+1][s2.length()+1];
		remonteB = new Provenance[s1.length()+1][s2.length()+1];
		remonteC = new Provenance[s1.length()+1][s2.length()+1];
		
		this.ouv = ouv;
		this.ext = ext;
		
		
		this.initTableaux();
		
	}
	
	private void initTableaux(){
		remonteA[0][0] = null;
		remonteB[0][0] = null;
		remonteC[0][0] = null;
		for (int i = 1; i < a.length; i++) {
			a[i][0] = Integer.MIN_VALUE/2;
			b[i][0] = Integer.MIN_VALUE/2;
			c[i][0] = this.ouv+this.ext*i;
			remonteC[i][0] = new Provenance(i-1,0,this.remonteC);
		}
		for (int j = 1; j < a[0].length; j++) {
			a[0][j] = Integer.MIN_VALUE/2;
			b[0][j] = this.ouv+this.ext*j;
			remonteB[0][j] = new Provenance(0,j,this.remonteB);
			c[0][j] = Integer.MIN_VALUE/2;
		}
		a[0][0] = 0;
		b[0][0] = this.ouv;
		c[0][0] = this.ouv;

	}
	
	void executeAlgo(){
		for (int i = 1; i < a.length; i++) {
			for (int j = 1; j < a[0].length; j++) {
				a[i][j] = this.substitution(s1.charAt(i-1),s2.charAt(j-1))+this.max(a[i-1][j-1],b[i-1][j-1],c[i-1][j-1],i,j,i-1,j-1,remonteA);
				b[i][j] = this.max(ouv+ext+a[i][j-1],ext+b[i][j-1],ouv+ext+c[i][j-1],i,j,i,j-1,remonteB);
				c[i][j] = this.max(ouv+ext+a[i-1][j],ouv+ext+b[i-1][j],ext+c[i-1][j],i,j,i-1,j,remonteC);
			}
		}
	}

	private int substitution(char charAt, char charAt2) {
		return this.tabSubstitution[getIndice(charAt)][getIndice(charAt2)];
	}

	private int getIndice(char charAt) {
		switch (charAt) {
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
	
	/**
	 * retourne l'indice du maximum dans les tableaux de score d'alignement
	 * 
	 * @return un tableau qui contient deux éléments : le premier le i et le
	 *         deuxième le j
	 */
	public int[] getMaxTabValue() {
		int indice[] = { a.length, a[0].length };
		int max = a[indice[0]][indice[1]];
		for (int i = 0; i < a.length; i++) {
			if (a[i][a[0].length] >= max) {
				max = a[i][a[0].length];
				indice[0] = i;
				indice[1] = a[0].length;
			}
		}
		for (int i = 0; i < a.length; i++) {
			if (a[i][a[0].length] >= max) {
				max = a[i][a[0].length];
				indice[0] = i;
				indice[1] = a[0].length;
			}
		}
		return indice;
	}
	
	/**
	 * permet d'obtenir les alignements en remontant les tableaux de score d'alignement
	 */
	public void remonte(){
		String s1 = "";
		String link = "";
		String s2 = "";
		int i = a.length-1;
		int j = a[0].length-1;
		NucleotideBuilder nb = new NucleotideBuilder();
		Provenance provenance = remonteA[i][j];
		/*if(a[i][j]>b[i][j]){
			if(a[i][j]>c[i][j]){
				provenance = remonteA[i][j];
			}
			else {
				provenance = remonteC[i][j];
			}
		}
		else{
			if(b[i][j]>c[i][j]){
				provenance = remonteB[i][j];
			}
			else {
				provenance = remonteC[i][j];
			}
		}*/
		while(i+j>0){
		//for (int k = 0; k < 3; k++) {
			if((provenance.i == i-1)&&(provenance.j == j-1)){
				s1 = this.s1.charAt(i-1)+s1;				
				s2 = this.s2.charAt(j-1)+s2;
				Nucleotide n1 = nb.build(this.s1.charAt(i-1));
				Nucleotide n2 = nb.build(this.s2.charAt(j-1));
				if(n1.isComplement(n2))
					link = "|"+link;
				else
					link = " "+link;
			}
			if((provenance.i == i)&&(provenance.j == j-1)){
				s1 = "-"+s1;
				link = " "+link;
				s2 = this.s2.charAt(j-1)+s2;
			}
			if((provenance.i == i-1)&&(provenance.j == j)){
				s1 = this.s1.charAt(i-1)+s1;
				link = " "+link;
				s2 = "-"+s2;
			}
			i = provenance.i;
			j = provenance.j;
			provenance = provenance.provenancePrecedente;
		}
		System.out.println(s1);
		System.out.println(link);
		System.out.println(s2);
	}

	/**
	 * calcule le max entre les 3 valeurs données en paramètres
	 * @param valueA valeur provenant du tableau a
	 * @param valueB valeur provenant du tableau b
	 * @param valueC valeur provenant du tableau c
	 * @param i est l'indice du max calculé
	 * @param j est l'indice du max calculé
	 * @param provenancei 
	 * @param provenancej
	 * @param remonte est le tableau de remontée pour le max qu'on calcule
	 * @return
	 */
	private int max(int valueA, int valueB, int valueC, int i, int j, int provenancei, int provenancej,Provenance[][] remonte) {
		if(valueA>valueB){
			if(valueA>valueC){
				remonte[i][j] = new Provenance(provenancei, provenancej, this.remonteA);
				return valueA;
			}
			else {
				remonte[i][j] = new Provenance(provenancei, provenancej, this.remonteC);
				return valueC;
			}
		}
		else{
			if(valueB>valueC){
				remonte[i][j] = new Provenance(provenancei, provenancej, this.remonteB);
				return valueB;
			}
			else {
				remonte[i][j] = new Provenance(provenancei, provenancej, this.remonteC);
				return valueC;
			}
		}
	}
	
	private void imprimerTab(int[][] a){
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[0].length; j++) {
				System.out.printf("%4d",a[i][j]);
			}
			System.out.println();
		}
	}
	
	private void imprimerRemonte(Provenance[][] a){
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
		imprimerTab(a);
		
		System.out.println("Impression de remontee A");
		imprimerRemonte(remonteA);
		
		
		System.out.println("Impression du tableau B");
		imprimerTab(b);
		
		System.out.println("Impression de remontee B");
		imprimerRemonte(remonteB);
		
		System.out.println("Impression du tableau C");
		imprimerTab(c);
		
		System.out.println("Impression de remontee C");
		imprimerRemonte(remonteC);
	}
	
	private class Provenance{
		int i;
		int j;
		char tabProvenance;
		Provenance provenancePrecedente;
		
		public Provenance(int provenancei,int provenancej,Provenance[][] tabProvenance) {
			this.provenancePrecedente = tabProvenance[provenancei][provenancej];
			this.i = provenancei;
			this.j = provenancej;
			if(tabProvenance==remonteA)
				this.tabProvenance = 'a';
			else if(tabProvenance==remonteB)
				this.tabProvenance = 'b';
			else if(tabProvenance==remonteC)
				this.tabProvenance = 'c';
		}
		
		public String toString(){
			return this.tabProvenance+","+this.i+","+this.j;
		}
		
	}
	
	public static void main(String[] args) {
		String s1 = "UGCUUCCGGCCUGUUCCCUGAGACCUCAAGUGUGAGUGUACUAUUGAUGCUUCACACCUGGGCUCUCCGGGUACCAGGACGGUUUGAGCA";
		String s2 = new StringBuilder(s1).reverse().toString();
		AlignementGapAffine algo = new AlignementGapAffine(s1, s2, -4, -1);
		algo.executeAlgo();
		//algo.imprimerTableaux();
		algo.remonte();
	}
}
