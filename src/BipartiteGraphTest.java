

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

class CheckBipartite {
	private boolean[] mark;
	private boolean[] color;
	private Graph graph;
	private boolean isBipartite;

	public CheckBipartite(Graph graph) {
		this.graph = graph;
		mark = new boolean[graph.getNumberOfVertices()];
		color = new boolean[graph.getNumberOfVertices()];
		isBipartite = checkBipartite(0, false);
	}

	private boolean checkBipartite(int s, boolean flag) {
		mark[s] = true;
		color[s] = flag;
		for (Integer i : graph.getNeighbours(s)) {
			if (!mark[i]) {
				checkBipartite(i, !flag);
			} else {
				if (color[i] == color[s]) {
					return false;
				}
			}
		}
		return true;
	}

	public boolean isBipartite() {
		return isBipartite;
	}

}

public class BipartiteGraphTest {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("graphData"));
		@SuppressWarnings("resource")
		Scanner in = new Scanner(reader);
		int E, V;
		V = in.nextInt();
		E = in.nextInt();
		Graph graph = new Graph(V);
		for (int i = 0; i < E; i++) {
			int u, v;
			u = in.nextInt();
			v = in.nextInt();
			graph.addEdge(u, v);
		}
		graph.printTheGraph();

		CheckBipartite cb = new CheckBipartite(graph);
		System.out.println(cb.isBipartite() ? "Bipartite" : "Not bipartite");
	}

}
