

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class Edge {
	private int u, v;
	private double w;

	public Edge() {
		this(0, 0, 0.0);
	}

	public Edge(int u, int v, double w) {
		this.u = u;
		this.v = v;
		this.w = w;
	}

	public int getFrom() {
		return u;
	}

	public int getTo() {
		return v;
	}

	public double getW() {
		return w;
	}
}

class DWG {
	private HashMap<Integer, ArrayList<Edge>> adjLists;
	public int E, V;

	public DWG(int V) {
		this.E = 0;
		this.V = V;
		adjLists = new HashMap<>();
		for (int i = 0; i < V; i++) {
			adjLists.put(i, new ArrayList<Edge>());
		}
	}

	public void addEdge(Edge e) {
		adjLists.get(e.getFrom()).add(e);
		E++;
	}

	public ArrayList<Edge> getNeighbours(int u) {
		return new ArrayList<>(adjLists.get(u));
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < V; i++) {
			for (Edge e : this.getNeighbours(i)) {
				builder.append(String.format("%d -> %d : %.2f\n", i, e.getTo(), e.getW()));
			}
		}
		return builder.toString();
	}

}

public class DirectedWeightedGraph {

	public static void main(String[] args) throws IOException {
		@SuppressWarnings("resource")
		Scanner in = new Scanner(new BufferedReader(new FileReader("graphData")));
		int E, V;
		V = in.nextInt();
		E = in.nextInt();
		DWG graph = new DWG(V);
		for(int i=0; i<E; i++){
			graph.addEdge(new Edge(in.nextInt(), in.nextInt(), in.nextDouble()));
		}
		
		System.out.println(graph);
	}

}
