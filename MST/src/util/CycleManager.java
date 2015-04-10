package util;

import graph.Itf.Edge;
import graph.Itf.Graph;
import graph.impl.VertexImpl;

/**
 * To detected if there is a cycle in a graph while applying Kruskal Algorithm
 * @author anis
 *
 */
public class CycleManager {

	private static int TAG = 1;
	private Graph graph;

	public CycleManager(graph.Itf.Graph graph2) {
		this.graph = graph2;
	}

	/**
	 * the main method to verify if adding an edge to Kruskal MST won't create a cycle
	 * @param edge
	 * @return
	 */
	public boolean isCyclic(Edge edge) {
		VertexImpl start = (VertexImpl) this.graph.getVertex(edge.getStart());
		VertexImpl end = (VertexImpl) this.graph.getVertex(edge.getEnd());

		if ((start.getTag() == 0) && (end.getTag() == 0)) {
			setTag(edge);
			return true;
		}

		else if ((start.getTag() != 0) && (end.getTag() == 0)) {
			setTag(edge, start);
			return true;
		}

		else if ((start.getTag() == 0) && (end.getTag() != 0)) {
			setTag(edge, end);
			return true;
		}

		else {
			if (start.getTag() != end.getTag()) {
				fixTag(start.getTag(), end.getTag());
				return true;
			}
		}

		return false;

	}

	/**
	 * To update the tags in a subgraph
	 * @param oldTag
	 * @param newTag
	 */
	private void fixTag(int oldTag, int newTag) {
		for (int i = 0; i < this.graph.getVertices().size(); i++) {
			VertexImpl v = (VertexImpl) this.graph.getVertex(i);
			if (v.getTag() == oldTag)
				v.setTag(newTag);
		}
	}

	/**
	 * To put a tag on an un-tagged vertex of an edge
	 * @param edge
	 * @param vertex
	 */
	private void setTag(Edge edge, VertexImpl vertex) {
		int tag = this.graph.getVertex(edge.getStart()).getTag();
		if (tag == 0) {
			this.graph.getVertex(edge.getStart()).setTag(vertex.getTag());
		} else
			this.graph.getVertex(edge.getEnd()).setTag(vertex.getTag());

	}

	/**
	 * To put a tag on both vertex of an un-tagged edge.
	 * @param edge
	 */
	private void setTag(Edge edge) {
		this.graph.getVertex(edge.getStart()).setTag(TAG);
		this.graph.getVertex(edge.getEnd()).setTag(TAG);
		TAG++;
	}

}
