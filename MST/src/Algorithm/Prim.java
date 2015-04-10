package Algorithm;

import java.util.Map;

import graph.Itf.Edge;
import graph.Itf.Graph;
import graph.Itf.Vertex;
import graph.impl.GraphImpl;

public class Prim implements Algo{
	Graph graph;
	Graph treeResult;
	
	public Prim(GraphImpl g) {
		this.graph = g;
		this.treeResult = new GraphImpl();
	}
	
	public Graph execute(){
		return graph;
	}

	@Override
	public Graph getGraph() {
		return graph;
	}

	@Override
	public void setGraph(Graph graph) {
		this.graph = graph;		
	}

	@Override
	public void findMST() {
		int nbVertices = graph.getVertices().size();
		MinHeap<VertexLink> vertexHeap = new MinHeap<VertexLink>(nbVertices);
		vertexHeap.ajouter(new VertexLink(0,0,0));
		while(true){
			int vertexMinNumber = vertexHeap.retirerRacine().getVertexNumber();
			Vertex v = graph.getVertex(vertexMinNumber);
			//pour chaque sommet adjacent, si la distance est inférieure à la distance dans la heap, mettre à jour la heap
			for(Map.Entry<Integer, Integer> adjacency : v.getAdjacency().entrySet()){
				int vertexNumber = adjacency.getKey();
				int distance = adjacency.getValue();
				
			}
		}
		
		
	}

	@Override
	public String ResultToString() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Représente un sommet et sa valeur d'adjacence avec un autre sommet qui est le sommet choisi dans l'algo de prim
	 * Par exemple, au tout début de l'algo, on choisit un sommet s au hasard.
	 * Les adjacences seront mis à jour par rapport à ce sommet.
	 * 
	 * @author rakotoarivony
	 *
	 */
	private class VertexLink implements Comparable<VertexLink>{ 
		private int vertexNumber;
		private int vertexLinkedNumber;
		private int distance;
		
		public int getVertexNumber() {
			return vertexNumber;
		}
		
		public int getVertexLinkedNumber() {
			return vertexLinkedNumber;
		}
		
		/**
		 * @param vertex :  number of this vertex
		 * @param vertexLinked : number of the vertex linked to this vertex
		 */
		public VertexLink(int vertex) {
			this.vertexNumber = vertex;
			this.vertexLinkedNumber = -1;
			this.distance = Integer.MAX_VALUE;
		}
		
		/**
		 * @param vertex :  number of this vertex
		 * @param vertexLinked : number of the vertex linked to this vertex
		 */
		public VertexLink(int vertex,int vertexLinked) {
			this.vertexNumber = vertex;
			this.vertexLinkedNumber = vertexLinked;
			this.distance = Integer.MAX_VALUE;
		}
		
		/**
		 * @param vertex :  number of this vertex
		 * @param vertexLinked : number of the vertex linked to this vertex
		 * @param distance : distance with the vertex linked
		 */
		public VertexLink(int vertex,int vertexLinked,int distance) {
			this.vertexNumber = vertex;
			this.vertexLinkedNumber = vertexLinked;
			this.distance = distance;
		}
		
		@Override
		public int compareTo(VertexLink o) {
			return ((Integer)this.distance).compareTo(o.distance);
		}
		
		@Override
		public boolean equals(Object o){
			if(o == null) return false;
			if(o instanceof VertexLink){
				VertexLink other = (VertexLink) o;
				return (this.vertexNumber == other.vertexNumber);
			}
			return false;
		}
	}
}
