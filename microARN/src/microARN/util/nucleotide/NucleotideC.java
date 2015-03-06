package microARN.util.nucleotide;

public class NucleotideC extends Nucleotide {

	public static Nucleotide instance = new NucleotideC();
	
	private NucleotideC(){
		super("C");
	}

	@Override
	public Nucleotide getCanonicalComplement() {
		return NucleotideG.instance;
	}

	@Override
	public Nucleotide getNonCanonicalComplement() {
		return null;
	}

}
