package generator;

import graph.Itf.Graph;

import java.util.Random;

public class RandomGraphGeneratorImpl implements RandomGraphGenerator {
	Random random;
	
	public RandomGraphGeneratorImpl() {
		random = new Random();
	}

	@Override
	public Graph generateErdosRenyiGraph(int numberOfVertices, float probability)
			throws IllegalArgumentException {
		Graph graph = new graph.impl.Graph();
		int distance;
		for (int i = 0; i < numberOfVertices; i++) {
			graph.addVertex();
		}
		for (int i = 0; i < numberOfVertices - 1; i++) {
			for (int j = i + 1; j < numberOfVertices; j++) {
				if (random.nextFloat() < probability) {
					distance = random.nextInt((int) Math.pow(numberOfVertices, 4));
					graph.addEdge(i, j, distance);
				}
			}
		}
		return graph;
	}
}
