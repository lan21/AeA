package generator;

import graph.Itf.Graph;

public interface RandomGraphGenerator {
    Graph generateErdosRenyiGraph(int numberOfVertices, float probability)
                      throws IllegalArgumentException;
}
