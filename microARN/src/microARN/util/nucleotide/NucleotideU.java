package microARN.util.nucleotide;

public class NucleotideU extends Nucleotide {

	public static Nucleotide instance = new NucleotideU();
	
	private NucleotideU(){
		super("U");
	}

	@Override
	public Nucleotide getCanonicalComplement() {
		return NucleotideA.instance;
	}

	@Override
	public Nucleotide getNonCanonicalComplement() {
		return NucleotideG.instance;
	}

}
