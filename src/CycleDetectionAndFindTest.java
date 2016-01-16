

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;

class CycleDetect {
	private boolean hasCycle;
	private boolean mark[];
	private boolean[] onStack;
	private Stack<Integer> cycleStack;
	private int[] toEdge;
	private DirectedGraph graph;

	public CycleDetect(DirectedGraph graph) {
		this.graph = graph;
		hasCycle = false;
		mark = new boolean[graph.getNumberOfVertices()];
		toEdge = new int[graph.getNumberOfVertices()];
		onStack = new boolean[graph.getNumberOfVertices()];
		cycleStack = null;
		int V = graph.getNumberOfVertices();
		System.out.println("The cycles are: ");
		for (int i = 0; i < V; i++) {
			if (!mark[i]) {
				specialDFS(i);
			}
		}
	}

	public boolean hasCycle() {
		return hasCycle;
	}

	// detects all the possible cycles in the graph
	private void specialDFS(int s) {
		mark[s] = true;
		onStack[s] = true;
		for (Integer i : graph.getNeighbours(s)) {
			if (!mark[i]) {
				toEdge[i] = s;
				specialDFS(i);
			} else if (onStack[i]) {
				hasCycle = true;
				cycleStack = new Stack<Integer>();
				for (int x = s; x != i; x = toEdge[x])
					cycleStack.push(x);
				cycleStack.push(i);
				cycleStack.push(s);
				while (!cycleStack.isEmpty()) {
					System.out.print(cycleStack.pop() + " ");
				}
				System.out.println();
				cycleStack = null;
			}
		}
		onStack[s] = false;
	}
}

@SuppressWarnings("unused")
public class CycleDetectionAndFindTest {
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

		CycleDetect cd = new CycleDetect(graph);

	}

}

/*
 * 3 2 3 
3 5 4 2 3 
2 0 5 4 2 
12 9 10 12 
8 7 8 */
