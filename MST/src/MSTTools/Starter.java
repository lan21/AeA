package MSTTools;

import generator.RandomGraphGenerator;
import generator.RandomGraphGeneratorImpl;
import graph.Itf.Graph;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import Algorithm.Algo;
import Algorithm.Kruskal;

public class Starter implements MSTTools {

	Algo kruskal;

	@Override
	public void runPrim(Graph g) {

	}

	@Override
	public void runKruskal(Graph g) {
		kruskal = new Kruskal(g);
		kruskal.findMST();
	}

	public static void main(String[] args) {
		if (args.length != 3) {
			System.out
					.println("Command: java -jar MST.jar [numberOfVertices] [probability] [Algo]");
			System.out.println("Algo: -p for PRIM\n      -k for KRUSKAL");
			return;
		}
		int numberOfVertices = Integer.parseInt(args[0]);
		float probability = Float.parseFloat(args[1]);
		String type = args[2];
		Starter s = new Starter();
		RandomGraphGenerator random = new RandomGraphGeneratorImpl();
		Graph graph = random.generateErdosRenyiGraph(numberOfVertices,
				probability);
		long start = 0, elapsedTimeInNanoSec= 0;
		if (type.equalsIgnoreCase("-p")) {
			start = System.nanoTime();
			s.runPrim(graph);
			elapsedTimeInNanoSec = (System.nanoTime() - start);
		}
		else if (type.equalsIgnoreCase("-k")){
			start = System.nanoTime();
			s.runKruskal(graph);
			elapsedTimeInNanoSec = (System.nanoTime() - start);
			PrintWriter writer;
			try {
				writer = new PrintWriter("Results.txt", "UTF-8");
				writer.println("Graph {");
				writer.println(s.kruskal.ResultToString());
				writer.println("}");
				writer.close();
			} catch (FileNotFoundException | UnsupportedEncodingException e) {
				e.printStackTrace();
			}		
		}
		else
			System.out.println("Algo can be [-k] for Kruksal or [-p] for Prim");
		System.out.println("Algortim took:"
				+ elapsedTimeInNanoSec * 1.0e-9 + "Seconds");
		
	}

}
