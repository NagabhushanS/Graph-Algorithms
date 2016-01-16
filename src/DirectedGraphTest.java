

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

class DirectedGraph{
	private Map<Integer, ArrayList<Integer>> adjLists;
	private int numberOfVertices;
	private int numberOfEdges;

	public DirectedGraph(int V) {
		adjLists = new HashMap<>(V);
		for (int i = 0; i < V; i++) {
			adjLists.put(i, new ArrayList<Integer>());
		}
		this.numberOfVertices = V;
		this.numberOfEdges = 0;
	}

	public int getNumberOfEdges() {
		return this.numberOfEdges;
	}

	public int getNumberOfVertices() {
		return this.numberOfVertices;
	}

	public void addVertex() {
		adjLists.put(getNumberOfVertices(), new ArrayList<Integer>());
		this.numberOfVertices++;
	}

	public void addEdge(int u, int v) { // add the edges u --> v
		adjLists.get(u).add(v);
		this.numberOfEdges++;
	}

	public ArrayList<Integer> getNeighbours(int u) {
		return new ArrayList<Integer>(adjLists.get(u));
	}

	public void printTheGraph() {
		for (Entry<Integer, ArrayList<Integer>> list : adjLists.entrySet()) {
			System.out.print(list.getKey() + ": ");
			for (Integer i : list.getValue()) {
				System.out.print(i + " ");
			}
			System.out.println();
		}

	}
	
	public DirectedGraph reverse(){
		DirectedGraph reverseGraph = new DirectedGraph(getNumberOfVertices());
		int V = getNumberOfVertices();
		for(int i=0; i<V; i++){
			for(Integer j: this.getNeighbours(i)){
				reverseGraph.addEdge(j, i);
			}
		}
		return reverseGraph;
	}
}

public class DirectedGraphTest {

	public static void main(String[] args) throws IOException {
		@SuppressWarnings("resource")
		Scanner in = new Scanner(new BufferedReader(new FileReader("graphData")));

		int E, V;
		V = in.nextInt();
		E = in.nextInt();
		DirectedGraph graph = new DirectedGraph(V);
		for (int i = 0; i < E; i++) {
			int u, v;
			u = in.nextInt();
			v = in.nextInt();
			graph.addEdge(u, v);
		}
		
		graph.printTheGraph();
		
		DFSDirected dfs = new DFSDirected(graph, 0);
		dfs.dfs();
		
	}

}
