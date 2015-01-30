package kmp;

import adn.ADN;

public class Shift_OR {
	private ADN adn;
	private char[] motif;
	private boolean[][] matrice;
	private boolean[] U_A;
	private boolean[] U_T;
	private boolean[] U_G;
	private boolean[] U_C;

	public Shift_OR(char[] adn, char[] motif) {
		this.motif = motif;
		this.adn = new ADN(adn);
		this.U_A = new boolean[this.motif.length];
		this.U_T = new boolean[this.motif.length];
		this.U_G = new boolean[this.motif.length];
		this.U_C = new boolean[this.motif.length];
		this.matrice = new boolean[this.adn.getBrin().length][this.motif.length];
	}

	public void trouve_pour_le_motif_exact() {
		try {
			this.genererVerctorsU();
			this.remplireMatrice();
			this.trouveMotif();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void trouve_pour_tous_les_version_du_motif() {

	}

	public void remplireMatrice() throws Exception {

		for (int i = 0; i < this.motif.length; i++) {
			this.matrice[0][i] = false;
		}

		if (this.adn.getBrin()[0] == this.motif[0]) {
			this.matrice[0][0] = true;
		}

		for (int i = 1; i < this.adn.getBrin().length; i++) {
			switch (adn.getBrin()[i]) {
			case 'A':
				matrice[i] = AND(this.U_A, decalage(matrice[i - 1]));
				break;
			case 'G':
				matrice[i] = AND(this.U_G, decalage(matrice[i - 1]));
				break;
			case 'C':
				matrice[i] = AND(this.U_C, decalage(matrice[i - 1]));
				break;
			case 'T':
				matrice[i] = AND(this.U_T, decalage(matrice[i - 1]));
				break;
			default:
				throw new Exception();
			}
		}
	}

	public boolean[] decalage(boolean[] tab) {
		boolean[] tmp = new boolean[tab.length];
		for (int i = tab.length - 1; i > 0; i--) {
			tmp[i] = tab[i - 1];
		}
		tmp[0] = true;
		return tmp;
	}

	private boolean[] AND(boolean[] tab1, boolean[] tab2) throws Exception {
		if (tab1.length != tab2.length)
			throw new Exception();
		boolean tab[] = new boolean[tab1.length];
		for (int i = 0; i < tab1.length; i++) {
			tab[i] = (tab1[i] && tab2[i]);
		}
		return tab;
	}

	public void genererVerctorsU() throws Exception {
		for (int i = 0; i < this.motif.length; i++) {
			switch (motif[i]) {
			case 'A':
				U_A[i] = true;
				U_C[i] = false;
				U_G[i] = false;
				U_T[i] = false;
				break;
			case 'G':
				U_A[i] = false;
				U_C[i] = false;
				U_G[i] = true;
				U_T[i] = false;
				break;
			case 'C':
				U_A[i] = false;
				U_C[i] = true;
				U_G[i] = false;
				U_T[i] = false;
				break;
			case 'T':
				U_A[i] = false;
				U_C[i] = false;
				U_G[i] = false;
				U_T[i] = true;
				break;
			default:
				throw new Exception();

			}
		}
	}

	public void trouveMotif() {
		boolean flag = false;
		for (int i = 0; i < this.adn.getBrin().length; i++) {
			if (this.matrice[i][this.motif.length - 1] == true) {
				flag = true;
				if ((i - this.motif.length + 2) == 0){
					System.out
							.println("Un motif commence au 1er caractère du brin");
				}
				System.out
						.println("Un motif commence au "
								+ (i - this.motif.length + 2)
								+ "éme caractère du brin");
			}
		}
		if(!flag){
			System.out.println("Aucun motif n'a été trouvé dans le brin!");
		}
	}

//	public static void main(String[] args) throws Exception {
//		Shift_OR s = new Shift_OR("GATACACAT".toCharArray(),
//				"GAT".toCharArray());
//		try {
//			s.genererVerctorsU();
//			s.remplireMatrice();
//			s.trouveMotif();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
}
