package microARN.util.nucleotide;

public class NucleotideA implements Nucleotide {
	
	public static NucleotideA instance = new NucleotideA();
	
	private NucleotideA(){}

	@Override
	public Nucleotide getCanonicalComplement() {
		return NucleotideU.instance;
	}

	@Override
	public Nucleotide getNonCanonicalComplement() {
		return null;
	}
	
	@Override
	public String toString(){
		return "A";
	}

}
