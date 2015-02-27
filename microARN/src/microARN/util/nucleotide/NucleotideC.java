package microARN.util.nucleotide;

public class NucleotideC implements Nucleotide {

	public static Nucleotide instance = new NucleotideC();
	
	private NucleotideC(){}

	@Override
	public Nucleotide getCanonicalComplement() {
		return NucleotideG.instance;
	}

	@Override
	public Nucleotide getNonCanonicalComplement() {
		return null;
	}
	
	@Override
	public String toString() {
		return "C";
	}

}
