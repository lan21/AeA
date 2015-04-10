package MSTTools;

import generator.RandomGraphGenerator;
import generator.RandomGraphGeneratorImpl;
import graph.Itf.Graph;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import Algorithm.Algo;
import Algorithm.Kruskal;
import Algorithm.Prim;

public class Starter implements MSTTools {

	Algo kruskal;
	Algo prim;

	@Override
	public void runPrim(Graph g) {
		prim = new Prim(g);
		prim.findMST();
	}

	@Override
	public void runKruskal(Graph g) {
		kruskal = new Kruskal(g);
		kruskal.findMST();
	}

	public static void main(String[] args) {
		if (args.length != 2) {
			System.out
					.println("Command: java -jar MST.jar [numberOfVertices] [probability] [Algo]");
			System.out.println("Algo: -p for PRIM\n      -k for KRUSKAL");
			return;
		}
		int numberOfVertices = Integer.parseInt(args[0]);
		float probability = Float.parseFloat(args[1]);
		// String type = args[2];
		Starter s = new Starter();
		RandomGraphGenerator random = new RandomGraphGeneratorImpl();
		Graph graph = random.generateErdosRenyiGraph(numberOfVertices,
				probability);
		long start = 0, elapsedTimeInNanoSec = 0;
		// if (type.equalsIgnoreCase("-p")) {
		start = System.nanoTime();
		s.runPrim(graph);
		PrintWriter writer;
		elapsedTimeInNanoSec = (System.nanoTime() - start);
		try {
			writer = new PrintWriter("ResultsPrim.txt", "UTF-8");
			writer.println("Graph {");
			writer.println(s.prim.ResultToString());
			writer.println("}");
			writer.close();
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		System.out.println("Algortim Prim took:" + elapsedTimeInNanoSec
				* 1.0e-9 + "Seconds");
		// }
		// else if (type.equalsIgnoreCase("-k")){
		start = System.nanoTime();
		s.runKruskal(graph);
		elapsedTimeInNanoSec = (System.nanoTime() - start);
		// PrintWriter writer;
		try {
			writer = new PrintWriter("ResultsKruskal.txt", "UTF-8");
			writer.println("Graph {");
			writer.println(s.kruskal.ResultToString());
			writer.println("}");
			writer.close();
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		// }
		// else
		// System.out.println("Algo can be [-k] for Kruksal or [-p] for Prim");
		System.out.println("Algortim Kruskal took:" + elapsedTimeInNanoSec
				* 1.0e-9 + "Seconds");
	}

}
