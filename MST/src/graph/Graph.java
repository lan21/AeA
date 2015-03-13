package graph;

import java.util.ArrayList;
import java.util.List;

public class Graph {

	private List<Node> nodes;
	private List<Arc> arcs;

	public Graph() {
		this.nodes = new ArrayList<Node>();
		this.arcs = new ArrayList<Arc>();
	}

	public Graph(List<Node> nodes, List<Arc> arcs) {
		this.nodes = nodes;
		this.arcs = arcs;
	}

	private void addNode(Node node) {
		if (!this.nodes.contains(node))
			this.nodes.add(node);
	}

	private void addArc(Arc arc) {
		if (this.arcs.contains(arc))
			this.arcs.add(arc);
	}

	public void createNode(int number) {
		Node node = new Node(number);
		addNode(node);
	}

	public void createArc(int weight, Node begin, Node end) {
		Arc arc = new Arc(weight, begin, end);
		addArc(arc);
		begin.addSucc(end);
		end.addSucc(begin);
	}

	public List<Node> getNodes() {
		return nodes;
	}

	public List<Arc> getArcs() {
		return arcs;
	}

}
