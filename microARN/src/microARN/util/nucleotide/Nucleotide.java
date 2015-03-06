package microARN.util.nucleotide;

/**
 * This class represent a nucleotide
 * @author rakotoarivony
 *
 */
public abstract class Nucleotide {
	
	protected String representation;
	
	protected Nucleotide(String representation){
		this.representation = representation;
	}
	/**
	 * return the canonical complement of this nucleotide
	 * @return the canonical complement of this nucleotide
	 */
	public abstract Nucleotide getCanonicalComplement();
	
	/**
	 * return the non canonical complement of this nucleotide if it has one.
	 * If it doesn't have one, it return null
	 * @return
	 */
	public abstract Nucleotide getNonCanonicalComplement();
	
	/**
	 * return true if n is the canonical complement of this nucleotide
	 * @param n
	 * @return
	 */
	public boolean isCanonicalComplement(Nucleotide n) {
		if (n== null)
			return false;
		return n.equals(this.getCanonicalComplement());
	}
	
	/**
	 * return true if n is the canonical complement or the non canonical of this nucleotide
	 * @param n
	 * @return
	 */
	public boolean isComplement(Nucleotide n) {
		if (n== null)
			return false;
		return this.isCanonicalComplement(n)||n.equals(this.getNonCanonicalComplement());
	}
	
	public boolean equals(Nucleotide n){
		if (n==null)
			return false;
		if(n instanceof Nucleotide){
			Nucleotide nucl = (Nucleotide) n;
			return this.representation.equals(nucl.representation);
		}
		else return false;
	}

	@Override
	public String toString(){
		return this.representation;
	}	

}
