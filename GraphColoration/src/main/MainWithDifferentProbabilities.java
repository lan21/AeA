package main;

import generator.RandomGraphGenerator;
import generator.RandomGraphGeneratorImpl;
import graph.Itf.Graph;
import Algorithm.DSATUR;
import Algorithm.Naive;
import Algorithm.WelshPowell;

public class MainWithDifferentProbabilities {
	public static void main(String[] args) {
			RandomGraphGenerator random = new RandomGraphGeneratorImpl();
			int nbOfVertices ;
			try{
				nbOfVertices = Integer.parseInt(args[0]);				
			}
			catch(Exception e){
				nbOfVertices = 100;
			}
			System.out.println("Comparison of algorithms for different probability");
			System.out.println();
			for (float i = 0.1f; i < 1f; i = i+0.2f) {
				System.out.println("p = "+i);
				System.out.println("\t\tNumber of colors\tExecution time (in ms)");
				
				Graph graph = random.generateErdosRenyiGraph(nbOfVertices, i);
				long start = System.nanoTime();
				WelshPowell w = new WelshPowell(graph);
				System.out.println("Welsh-Powell\t"+w.getNumberOfUsedColors()+"\t\t\t"+(System.nanoTime()-start)/1000000.);
				
				graph.resetColor();
				start = System.nanoTime();
				DSATUR d = new DSATUR(graph);
				System.out.println("DSATUR\t\t"+d.getNumberOfUsedColors()+"\t\t\t"+(System.nanoTime()-start)/1000000.);
				
				graph.resetColor();
				start = System.nanoTime();
				Naive n = new Naive(graph);
				System.out.println("Naive\t\t"+n.getNumberOfUsedColors()+"\t\t\t"+(System.nanoTime()-start)/1000000.);
				
				System.out.println();
			}
	}
	
	private static void usage(){
		System.out.println("usage : MainWithDifferentProbabilities <numberOfVertices>");
		System.out.println("numberOfVertices : number of vertices on the generated graph. It will be 100 bu default");
	}
	
}
