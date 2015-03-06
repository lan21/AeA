package simulateur;

import java.util.LinkedList;
import java.util.Random;

/**
 * Cette classe est un simulateur de micro ARN. Il produit des séquences de
 * pré-microARN au hasard
 * 
 * @author Anis Tello
 * 
 */
public class SimulatorMicroARN {

	private String microARN;
	private LinkedList<Integer> gapIndex;
	private Random random = new Random();
	int middleGapIndex;
	String middleGap;
	private static int MIDDLEGAPLENGTH = 7;
	private static int MAXGAPLENGTH = 3;

	public SimulatorMicroARN(int halfLength) {
		try {
			microARN = generateSimpleMicroARN(halfLength);
			gapIndex = new LinkedList<Integer>();
			middleGapIndex = microARN.length() / 2;
			middleGap = middleGap();
			insertGap(middleGap, middleGapIndex);
			addGapIndex(middleGapIndex, middleGap);
			insertRandomGaps();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void insertRandomGaps() throws Exception {
		int numberOfGap = numberOfGap();
		int offsetIndex = 0;
		int gapNumber = 0;
		String gap;
		while ((gapNumber < numberOfGap) && (microARN.length() < 100)) {
			offsetIndex = random.nextInt(microARN.length());
			int gapLength;
			if ((gapLength = random.nextInt(MAXGAPLENGTH)) > 0) {
				gap = createGap(gapLength);
				if (checkGapIndexValide(offsetIndex)) {
					insertGap(gap, offsetIndex);
					addGapIndex(offsetIndex, gap);
				}
			}
		}
	}

	private void addGapIndex(int offsetIndex, String gap) {
		for (int i = 0; i < gapIndex.size(); i++) {
			if (offsetIndex < gapIndex.get(i))
				gapIndex.set(i, gapIndex.get(i) + gap.length());
		}
		gapIndex.add(offsetIndex);
	}

	private String middleGap() throws Exception {
		return createGap(MIDDLEGAPLENGTH);
	}

	private int numberOfGap() {
		return random.nextInt(100 - (microARN.length()));
	}

	private boolean checkGapIndexValide(int offsetIndex) {
		if (this.gapIndex.isEmpty())
			return true;
		if (this.gapIndex.contains(offsetIndex))
			return false;

		if (middleGapIndex < offsetIndex){
			if ((offsetIndex - middleGapIndex) < (MIDDLEGAPLENGTH + 3))
				return false;
		}
		for (Integer i : this.gapIndex) {
			if (Math.abs(this.gapIndex.indexOf(i) - offsetIndex) <= 3)
				return false;
		}
		return true;
	}

	public String generateFirstPartMicroARN(int length) throws Exception {
		String result = "";
		Random r = new Random();
		int n;
		for (int i = 0; i < length; i++) {
			n = r.nextInt(4);
			switch (n) {
			case 0:
				result += 'A';
				break;
			case 1:
				result += 'U';
				break;
			case 2:
				result += 'G';
				break;
			case 3:
				result += 'C';
				break;
			default:
				throw new Exception();
			}
		}

		return result;
	}

	public boolean checkAppariementAutorises(char f, char l) throws Exception {
		switch (f) {
		case 'A':
			return (l == 'U');
		case 'C':
			return (l == 'G');
		case 'G':
			return ((l == 'U') || (l == 'C'));
		case 'U':
			return ((l == 'A') || (l == 'G'));
		default:
			throw new Exception();
		}
	}

	public String generateSimpleMicroARN(int length) throws Exception {
		String s = generateFirstPartMicroARN(length);
		ARN adn = new ARN(s.toCharArray());
		String r = adn.reverse_complementaireString();
		return s.concat(r);
	}

	public boolean checkGap(char[] tab) throws Exception {
		int j = tab.length - 1;
		for (int i = 0; i < tab.length / 2; i++) {
			if (checkAppariementAutorises(tab[i], tab[j]))
				return false;
			j--;
		}
		return true;
	}

	public String createGap(int length) throws Exception {
		String gap = null;
		while (true) {
			gap = generateFirstPartMicroARN(length);
			if (checkGap(gap.toCharArray()))
				break;
		}
		return gap;
	}

	public void insertGap(String gap, int offset) {
		System.out.println("New gap was inserted:  " + gap);
		StringBuilder stringBuilder = new StringBuilder(this.microARN);
		stringBuilder.insert(offset, gap.toCharArray());
		this.microARN = stringBuilder.toString();
	}

	@Override
	public String toString() {
		return this.microARN;
	}

	public static void main(String[] args) throws Exception {
		SimulatorMicroARN MiARN = new SimulatorMicroARN(35);
		System.out.println(MiARN.toString());
		for (Integer integer : MiARN.gapIndex) {
			System.out.println(integer);
		}
	}
}
