package microARN.util.nucleotide;

public class NucleotideA extends Nucleotide {
	
	public static Nucleotide instance = new NucleotideA();
		
	private NucleotideA(){
		super("A");
	}

	@Override
	public Nucleotide getCanonicalComplement() {
		return NucleotideU.instance;
	}

	@Override
	public Nucleotide getNonCanonicalComplement() {
		return null;
	}
	
	

}
