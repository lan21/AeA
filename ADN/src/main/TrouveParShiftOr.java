package main;

import adn.ADN;
import kmp.*;

public class TrouveParShiftOr {

	public static void trouve_pour_le_motif_exact(char[] brin, char[] motif) {
		System.out.print("Le motif exact: ");
		System.out.println(motif);
		Shift_OR shift = new Shift_OR(brin, motif);
		try {
			shift.genererVerctorsU();
			shift.remplireMatrice();
			shift.trouveMotif();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void trouve_pour_le_brin_reverse(char[] brin, char[] motif)
			throws Exception {
		ADN a = new ADN(motif);
		System.out.print("Le reverse: ");
		System.out.println(a.brin_reverse());
		Shift_OR shift = new Shift_OR(brin, a.brin_reverse());
		try {
			shift.genererVerctorsU();
			shift.remplireMatrice();
			shift.trouveMotif();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void trouve_pour_le_brin_complementaire(char[] brin,
			char[] motif) throws Exception {
		ADN a = new ADN(motif);
		System.out.print("Le brin complementaire: ");
		System.out.println(a.complementaire());
		Shift_OR shift = new Shift_OR(brin, a.complementaire());
		try {
			shift.genererVerctorsU();
			shift.remplireMatrice();
			shift.trouveMotif();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void trouve_pour_le_brin_reverse_complementaire(char[] brin,
			char[] motif) throws Exception {
		ADN a = new ADN(motif);
		System.out.print("Le brin reverse complementaire: ");
		System.out.println(a.reverse_complementaire());
		Shift_OR shift = new Shift_OR(brin, a.reverse_complementaire());
		try {
			shift.genererVerctorsU();
			shift.remplireMatrice();
			shift.trouveMotif();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void trouve_pour_tous_les_version_du_motif(char[] brin,
			char[] motif) throws Exception {
		TrouveParShiftOr.trouve_pour_le_motif_exact(brin, motif);
		TrouveParShiftOr.trouve_pour_le_brin_reverse(brin, motif);
		TrouveParShiftOr.trouve_pour_le_brin_complementaire(brin, motif);
		TrouveParShiftOr
				.trouve_pour_le_brin_reverse_complementaire(brin, motif);
	}

	public static void main(String[] args) throws Exception {
		TrouveParShiftOr.trouve_pour_le_motif_exact("GATCATCACT".toCharArray(),
				"TCA".toCharArray());
		TrouveParShiftOr.trouve_pour_le_brin_reverse(
				"GATCATCACT".toCharArray(), "TCA".toCharArray());
		TrouveParShiftOr.trouve_pour_le_brin_complementaire(
				"GAGTCATCACT".toCharArray(), "TCA".toCharArray());
		TrouveParShiftOr.trouve_pour_le_brin_reverse_complementaire(
				"GTGACATCACT".toCharArray(), "TCA".toCharArray());
		TrouveParShiftOr.trouve_pour_le_motif_exact("GGGGGG".toCharArray(),
				"TCA".toCharArray());

	}
}
