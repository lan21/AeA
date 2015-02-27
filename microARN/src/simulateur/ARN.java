package simulateur;

import java.util.Arrays;
import java.util.Random;

public class ARN {
	private char[] brin;

	public ARN(int size) {
		brin = new char[size];
	}

	public ARN(char[] brin) {
		this.brin = brin;
	}

	public ARN(String brin) {
		this.brin = brin.toCharArray();
	}

	public char[] brin_reverse() {
		char[] reverse = new char[this.brin.length];
		int j = 0;
		for (int i = this.brin.length - 1; i >= 0; i--) {
			reverse[j] = this.brin[i];
			j++;
		}
		return reverse;
	}

	public char[] complementaire() throws Exception {
		char[] comp = new char[this.brin.length];
		Random r = new Random();
		for (int i = 0; i < this.brin.length; i++) {
			switch (this.brin[i]) {
			case 'A':
				comp[i] = 'U';
				break;
			case 'U':
				comp[i] = (r.nextInt()%2 == 0)? 'A' : 'G';
				break;
			case 'G':
				comp[i] = (r.nextInt()%2 == 0)? 'U' : 'C';
				break;
			case 'C':
				comp[i] = 'G';
				break;
			default:
				throw new Exception("ADN non conforme");
			}
		}
		return comp;
	}

	public char[] reverse_complementaire() throws Exception {
		char[] tmp = new char[this.brin.length];
		tmp = this.brin_reverse();
		ARN a = new ARN(tmp);
		return a.complementaire();
	}

	public char[] getBrin() {
		return brin;
	}

	public void setBrin(char[] brin) {
		this.brin = brin;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ARN other = (ARN) obj;
		try {
			if ((Arrays.equals(this.brin, other.brin))
					|| (Arrays.equals(this.complementaire(), other.brin))
					|| (Arrays.equals(this.brin_reverse(), other.brin))
					|| (Arrays
							.equals(this.reverse_complementaire(), other.brin)))
				return true;
		} catch (Exception e) {
			return false;
		}

		return false;
	}

	public String reverse_complementaireString() throws Exception {
		return new String(this.reverse_complementaire());
	}

	public String complementaireString() throws Exception {
		return new String(this.complementaire());
	}

	public String brin_reverseString() {
		return new String(this.brin_reverse());
	}

//	/**
//	 * retourne la liste des indices d'occurence de cet adn dans la séquence
//	 * d'adn
//	 * 
//	 * @param sequence
//	 * @return
//	 * @throws Exception
//	 */
//	public List<Integer> occurence(ADN sequence) throws Exception {
//		List<Integer> occurence = new LinkedList<Integer>();
//		String brin = new String(this.brin);
//		String reverse = new String(this.brin_reverse());
//		String complementaire = new String(this.complementaire());
//		String reverseComplementaire = new String(this.reverse_complementaire());
//
//		String laSequence = new String(sequence.brin);
//
//		occurence.addAll(KMP.listeIndiceOccurence(brin, laSequence));
//		if (!reverse.equals(brin))
//			occurence.addAll(KMP.listeIndiceOccurence(reverse, laSequence));
//		if ((!complementaire.equals(brin)) && (!complementaire.equals(reverse)))
//			occurence.addAll(KMP.listeIndiceOccurence(complementaire,
//					laSequence));
//		if ((!reverseComplementaire.equals(brin))
//				&& (!reverseComplementaire.equals(reverse))
//				&& (!reverseComplementaire.equals(complementaire)))
//			occurence.addAll(KMP.listeIndiceOccurence(reverseComplementaire,
//					laSequence));
//		return occurence;
//	}
//
//	public List<Integer> occurenceReverseComplementaire(ADN sequence)
//			throws Exception {
//		List<Integer> occurence = new LinkedList<Integer>();
//		String reverseComplementaire = new String(this.reverse_complementaire());
//
//		String laSequence = new String(sequence.brin);
//		occurence.addAll(KMP.listeIndiceOccurence(reverseComplementaire,
//				laSequence));
//		return occurence;
//	}
}