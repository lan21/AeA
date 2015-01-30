package adn;

import java.util.List;
import java.util.Arrays;

public class ADN {
	private char[] brin;
	
	public ADN(int size) {
		brin = new char[size];
	}
	
	public ADN(char[] brin){
		this.brin = brin;
	}
	
	public ADN(String brin){
		this.brin = brin.toCharArray();
	}
	
	
	public char[] brin_reverse(){
		char[] reverse = new char[this.brin.length];
		int j = 0;
		for (int i = this.brin.length - 1; i>=0; i--) {
			reverse[j] = this.brin[i];
			j++;
		}
		return reverse;
	}
	
	
	public char[] complementaire() throws Exception{
		char[] comp = new char[this.brin.length];
		for (int i = 0; i < this.brin.length; i++) {
			switch(this.brin[i]){
				case 'A':
					comp[i] = 'T';
					break;
				case 'T':
					comp[i] = 'A';
					break;
				case 'G':
					comp[i] = 'C';
					break;
				case 'C':
					comp[i] = 'G';
					break;
				default:
					throw new Exception();
			}
		}
		return comp;
	}
	
	
	public char[] reverse_complementaire() throws Exception{
		char[] tmp = new char[this.brin.length];
		tmp = this.brin_reverse();
		ADN a = new ADN(tmp);
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
		ADN other = (ADN) obj;
		try {
			if ( (Arrays.equals(this.brin, other.brin)) || 
				 (Arrays.equals(this.complementaire(), other.brin)) ||
				 (Arrays.equals(this.brin_reverse(), other.brin))  ||
				 (Arrays.equals(this.reverse_complementaire(), other.brin))
			   )
				return true;
		} catch (Exception e) {
			return false;
		}
		
		return false;
	}
	

	public String reverse_complementaireString() throws Exception{
		return new String(this.reverse_complementaire());
	}
	

	public String complementaireString() throws Exception{
		return new String(this.complementaire());
	}
	
	public String brin_reverseString(){
		return new String(this.brin_reverse());
	}
	
	/**
	 * retourne la liste des indices d'occurence de cet adn dans la séquence d'adn
	 * @param sequence
	 * @return
	 */
	public List<Integer> occurence(ADN sequence){
		return null;
	}
}