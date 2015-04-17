package main;

import generator.RandomGraphGenerator;
import generator.RandomGraphGeneratorImpl;
import graph.Itf.Graph;
import Algorithm.DSATUR;
import Algorithm.Naive;
import Algorithm.WelshPowell;

public class Main {
	public static void main(String[] args) {
		try{
			RandomGraphGenerator random = new RandomGraphGeneratorImpl();
			int nbOfVertices = Integer.parseInt(args[0]);
			float probability = Float.parseFloat(args[1]);
			if(probability<0.0f)
				probability = 0.0f;
			if(probability>1.0f)
				probability = 1.0f;			
			System.out.println("Execution of algo with n = "+nbOfVertices+" and p = "+probability);
			System.out.println();
			long timeWelshPowell = 0;
			long timeDsatur = 0;
			long timeNaive = 0;
			for (int i=0;i<50;i++) {
				
				Graph graph = random.generateErdosRenyiGraph(nbOfVertices, i);
				long start = System.nanoTime();
				WelshPowell w = new WelshPowell(graph);
				timeWelshPowell += (System.nanoTime()-start);
				
				graph.resetColor();
				start = System.nanoTime();
				DSATUR d = new DSATUR(graph);
				timeDsatur += (System.nanoTime()-start);
				
				graph.resetColor();
				start = System.nanoTime();
				Naive n = new Naive(graph);
				timeNaive += (System.nanoTime()-start);
				
			}
			System.out.println("\t\tAverage execution time ");
			System.out.println("Welsh-Powell \t: "+(timeWelshPowell/1000000.)/50.+"ms");
			System.out.println("Dsatur \t\t: "+(timeDsatur/1000000.)/50.+"ms");
			System.out.println("Naive \t\t: "+(timeNaive/1000000.)/50.+"ms");
			System.out.println();
		} catch(Exception e){
			usage();
		}
	}
	
	private static void usage(){
		System.out.println("usage : MainWithDifferentProbabilities <numberOfVertices> <probability>");
		System.out.println("<probability> : a float between 0 and 1");
	}
	
}
