

@SuppressWarnings("unused")
public class Search {
	private Graph graph;
	private int s;

	// g is the graph and s is the source vertex
	public Search(Graph g, int s) {
		this.graph = g;
		this.s = s;
	}

	// is v connected to s
	public boolean marked(int v) {
		return true;
	}

	// get the number of vertices connected to s
	public int count() {
		return 0;
	}

}
