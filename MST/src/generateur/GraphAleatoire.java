package generateur;

import graph.Graph;
import graph.Node;

import java.util.Random;

public class GraphAleatoire {

	Random random;
	Graph graph;
	float probility;

	public GraphAleatoire(float probility) {
		random = new Random();
		this.probility = probility;
		graph = new Graph();
	}
	
	private void generate() {
		int numberOfNodes = random.nextInt(100);
		System.out.println(numberOfNodes);
		for (int i = 0; i < numberOfNodes; i++) {
			graph.createNode(i);
		}
		
		int numberOfArcs = random.nextInt((int)(probility*((numberOfNodes * (numberOfNodes -1))/2)));
		System.out.println(numberOfArcs);
		for (int i = 0; i < numberOfArcs; i++) {
			graph.createArc(random.nextInt(100), graph.getNodes().get(random.nextInt(numberOfNodes)), graph.getNodes().get(random.nextInt(numberOfNodes)));
		}
	}
	
	
	public static void main(String[] args) {
		GraphAleatoire aleatoire = new GraphAleatoire(0.5f);
		aleatoire.generate();
		
		for (int i = 0; i < aleatoire.graph.getNodes().size(); i++) {
			Node n = aleatoire.graph.getNodes().get(i);
			System.out.print( "Node N° " + n.getNumber() + ", Succ : ");
			
			for (int j = 0; j < n.getListSucc().size(); j++) {
				System.out.print(n.getListSucc().get(j).getNumber() + ", ");
			}
			System.out.println();
		}
	}

}
