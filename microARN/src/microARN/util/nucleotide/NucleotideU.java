package microARN.util.nucleotide;

public class NucleotideU implements Nucleotide {

	public static Nucleotide instance = new NucleotideU();
	
	private NucleotideU(){}

	@Override
	public Nucleotide getCanonicalComplement() {
		return NucleotideA.instance;
	}

	@Override
	public Nucleotide getNonCanonicalComplement() {
		return NucleotideG.instance;
	}
	
	@Override
	public String toString() {
		return "U";
	}

}
