

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;

class SCC {
	private DirectedGraph graph;
	private DirectedGraph rev;
	private int[] id;
	private boolean[] mark;
	private Stack<Integer> stack;
	private int count;

	public SCC(DirectedGraph graph) {
		this.graph = graph;
		stack = new Stack<>();
		id = new int[graph.getNumberOfVertices()];
		mark = new boolean[graph.getNumberOfVertices()];
		rev = graph.reverse();
		for (int i = 0; i < graph.getNumberOfVertices(); i++) {
			if (!mark[i]) {
				dfsrev(i);
			}

		}
		count = 0;
		mark = new boolean[graph.getNumberOfVertices()];
		while (!stack.isEmpty()) {
			int x = stack.pop();
			if (!mark[x]) {
				dfs(x);
				count++;
			}
		}
		for (int i = 0; i < graph.getNumberOfVertices(); i++) {
			System.out.print(id[i] + " ");
		}
		System.out.println();
	}

	private void dfs(int s) {
		mark[s] = true;
		id[s] = count;
		for (Integer i : graph.getNeighbours(s)) {
			if (!mark[i]) {
				dfs(i);
			}
		}
	}

	private void dfsrev(int s) {
		mark[s] = true;
		for (Integer i : rev.getNeighbours(s)) {
			if (!mark[i]) {
				dfsrev(i);
			}
		}
		stack.push(s);
	}

	public boolean isConnected(int u, int v) {
		return id[u] == id[v];
	}
}

public class KosarajuSharirAlgorithm {

	@SuppressWarnings("unused")
	public static void main(String[] args) throws IOException {
		@SuppressWarnings("resource")
		Scanner in = new Scanner(new BufferedReader(new FileReader("graphData")));
		int E, V;
		V = in.nextInt();
		E = in.nextInt();
		DirectedGraph graph = new DirectedGraph(V);
		for (int i = 0; i < E; i++) {
			graph.addEdge(in.nextInt(), in.nextInt());
		}
		graph.printTheGraph();

		SCC scc = new SCC(graph);

	}

}
