package reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import exception.InvalidFastaFileException;

/**
 * lecteur de fichier Fasta.
 * @author rakotoarivony
 *
 */
public class FastaReader {
	private String filename;
	private BufferedReader br;
	
	public FastaReader(String filename) throws IOException, InvalidFastaFileException {
		this.filename = filename;		
		this.br = new BufferedReader(new FileReader(filename));		
		if(!this.br.readLine().startsWith(">")){
			throw new InvalidFastaFileException();
		}
	}
	
	public String getFilename() {
		return filename;
	}
	

	/**
	 * return a String representing the chromosome sequence for this fasta reader
	 * @return
	 * @throws IOException 
	 */
	public String readSequence() throws IOException{
		//TODO ajouter les exceptions si fichier non conforme (si non A,T,G,C)
		String brin = "";
		String read = "";		
		while(((read = br.readLine())!=null)&&(!read.startsWith(">"))){
			brin+=read;
		}
		
		return brin;
	}
	
	public void close() throws IOException{
		this.br.close();
	}
}
