package microARN.util.nucleotide;

public class NucleotideG extends Nucleotide {

	public static Nucleotide instance = new NucleotideG();
	
	private NucleotideG(){
		super("G");
	}

	@Override
	public Nucleotide getCanonicalComplement() {
		return NucleotideC.instance;
	}

	@Override
	public Nucleotide getNonCanonicalComplement() {
		return NucleotideU.instance;
	}


}
