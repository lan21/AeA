package microARN.util.nucleotide;
/** 
 * builds nucleotide corresponding to the letter we give it
 * @author rakotoarivony
 *
 */
public class NucleotideBuilder {
	public Nucleotide build(char c){
		switch (c) {
		case 'A':
			return NucleotideA.instance;
		case 'C':
			return NucleotideC.instance;
		case 'G':
			return NucleotideG.instance;
		case 'U':
			return NucleotideU.instance;
		default:
			return null;
		}
	}
}
