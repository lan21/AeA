package microARN.util.nucleotide;

/**
 * This class represent a nucleotide
 * @author rakotoarivony
 *
 */
public interface Nucleotide {
	/**
	 * return the canonical complement of this nucleotide
	 * @return the canonical complement of this nucleotide
	 */
	public Nucleotide getCanonicalComplement();
	
	/**
	 * return the non canonical complement of this nucleotide if it has one.
	 * If it doesn't have one, it return null
	 * @return
	 */
	public Nucleotide getNonCanonicalComplement();
	
	/**
	 * return the representation of this nucleotide in String
	 * @return the representation of this nucleotide in String
	 */
	public String toString();

}
