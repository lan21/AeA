package simulateur;

import java.util.Random;

/**
 * Cette classe est un simulateur de micro ARN. Il produit des séquences de
 * pré-microARN au hasard
 * 
 * @author rakotoarivony
 * 
 */
public class SimulateurMicroARN {
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
	
	public String generateMicroARN() throws Exception{
		String s =generateFirstPartMicroARN(50);
		ARN adn = new ARN(s.toCharArray());
		String r = adn.reverse_complementaireString();
		return s.concat(r);
	}
	
	public String createGap(int n) throws Exception{
		String s;
		while(!check(s=generateFirstPartMicroARN(n))){}
		return s;
	}
	
	private boolean check(String string) throws Exception {
		char tab[] = string.toCharArray();
		int i = 0;
		int j = tab.length;
		while(i<j){
			if(checkAppariementAutorises(tab[i], tab[j]))
				return false;
		}
		return true;
	}

	public String reverseString(String string){
		String reverse = new StringBuffer(string).reverse().toString();
		return reverse;
	}
	public static void main(String[] args) throws Exception {
		SimulateurMicroARN s = new SimulateurMicroARN();
		System.out.println(s.generateMicroARN());
	}
}
