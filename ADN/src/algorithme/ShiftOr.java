package algorithme;

import java.util.ArrayList;
import java.util.List;

import exception.*;
import adn.ADN;

/**
 * 
 * @author tello Class Shift_OR represent la algorithm shift or pour trouver un
 *         motif dans un brin d' ADN
 * 
 */
public class ShiftOr {
	/**
	 * Un instance d' ADN contient le brin
	 */
	private ADN adn;
	/**
	 * Le motif à chercher
	 */
	private char[] motif;
	/**
	 * La matrice principale
	 */
	public boolean[][] matrice;

	/**
	 * La vercteur U, prendre la valeur true où la lettre A apparaitre dans le
	 * motif
	 */
	public boolean[] U_A;

	/**
	 * La vercteur U, prendre la valeur true où la lettre T apparaitre dans le
	 * motif
	 */

	public boolean[] U_T;

	/**
	 * La vercteur U, prendre la valeur true où la lettre G apparaitre dans le
	 * motif
	 */
	public boolean[] U_G;

	/**
	 * La vercteur U, prendre la valeur true où la lettre C apparaitre dans le
	 * motif
	 * 
	 */
	public boolean[] U_C;

	
	public List<Integer> trouvee;
	/**
	 * Constructeur pour initialiser les parametre
	 * 
	 * @param adn
	 * @param motif
	 */
	public ShiftOr(char[] adn, char[] motif) {
		this.motif = motif;
		this.adn = new ADN(adn);
		this.U_A = new boolean[this.motif.length];
		this.U_T = new boolean[this.motif.length];
		this.U_G = new boolean[this.motif.length];
		this.U_C = new boolean[this.motif.length];
		this.matrice = new boolean[this.adn.getBrin().length][this.motif.length];
		this.trouvee = new ArrayList<Integer>();
	}

	/**
	 * Remplire la matrice principale de l'algorithm Shift or: Remplir la
	 * premier vecteur, puis pour chaque vectur i : (la decalage de la vecteur
	 * i-1) AND La Vecteur U de la lettre i du brin
	 * 
	 * @throws InvalidDNAException
	 * @throws ArrayLengthException
	 */
	public void remplireMatrice() throws InvalidDNAException, ArrayLengthException {

		for (int i = 0; i < this.motif.length; i++) {
			this.matrice[0][i] = false;
		}
		if (this.adn.getBrin()[0] == 'A' || this.adn.getBrin()[0] == 'G'
				|| this.adn.getBrin()[0] == 'T' || this.adn.getBrin()[0] == 'C') {
			if (this.adn.getBrin()[0] == this.motif[0]) {
				this.matrice[0][0] = true;
			}
		} else
			throw new InvalidDNAException();

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
				throw new InvalidDNAException();
			}
		}
	}

	/**
	 * 
	 * @param tab
	 * @return Decalage tous les element dans tab, premier indice prendre la
	 *         valeur true, la dernière valeur sort
	 */
	public boolean[] decalage(boolean[] tab) {
		boolean[] tmp = new boolean[tab.length];
		for (int i = tab.length - 1; i > 0; i--) {
			tmp[i] = tab[i - 1];
		}
		tmp[0] = true;
		return tmp;
	}

	/**
	 * 
	 * @param tab1
	 * @param tab2
	 * @return Le ET LOGIC entre le tab1 et le tab2
	 * @throws ArrayLengthException
	 */
	public boolean[] AND(boolean[] tab1, boolean[] tab2)
			throws ArrayLengthException {
		if (tab1.length != tab2.length)
			throw new ArrayLengthException();
		boolean tab[] = new boolean[tab1.length];
		for (int i = 0; i < tab1.length; i++) {
			tab[i] = (tab1[i] && tab2[i]);
		}
		return tab;
	}

	/**
	 * Remplir les 4 vercteur U
	 * 
	 * @throws InvalidDNAException
	 */
	public void genererLesVerctorsU() throws InvalidDNAException {
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
				throw new InvalidDNAException();

			}
		}
	}

	/**
	 * Chercher où un motif commence dans le brin par parcourir que la dérnière
	 * ligne de la matrice.
	 */
	public void trouveMotif() {

		for (int i = 0; i < this.adn.getBrin().length; i++) {
			if (this.matrice[i][this.motif.length - 1] == true) {
				this.trouvee.add(i - this.motif.length + 1);
			}
		}
	}
}