package microARN.util.nucleotide;

public class NucleotideG implements Nucleotide {

	public static Nucleotide instance = new NucleotideG();
	
	private NucleotideG(){}

	@Override
	public Nucleotide getCanonicalComplement() {
		return NucleotideC.instance;
	}

	@Override
	public Nucleotide getNonCanonicalComplement() {
		return NucleotideU.instance;
	}
	
	@Override
	public String toString() {
		return "G";
	}

}
