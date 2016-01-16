

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;

class DAGSP {
	private double[] distTo;
	private int[] edgeTo;
	private boolean[] mark;
	private Stack<Integer> toStack;
	private DWG graph;
	private int s;

	public DAGSP(DWG g, int s) {
		graph = g;
		this.s = s;
		mark = new boolean[g.V];
		toStack = new Stack<>();
		distTo = new double[g.V];
		edgeTo = new int[g.V];
		for (int i = 0; i < g.V; i++) {
			if (!mark[i]) {
				dfs(i);
			}
		}
		for (int i = 0; i < g.V; i++) {
			distTo[i] = Double.POSITIVE_INFINITY;
		}
		distTo[s] = 0.0;
		edgeTo[s] = -1;
		while (!toStack.isEmpty()) {
			for (Edge e : graph.getNeighbours(toStack.pop())) {
				relaxEdge(e);
			}
		}
		System.out.println("The shortest paths are: ");
		for (int i = 0; i < g.V; i++) {
			System.out.println(s + " --> " + i + ": " + distTo[i]);
		}
		System.out.println();
	}

	private void dfs(int s) {
		mark[s] = true;
		for (Edge e : graph.getNeighbours(s)) {
			if (!mark[e.getTo()]) {
				dfs(e.getTo());
			}
		}
		toStack.push(s);

	}

	private void relaxEdge(Edge e) {
		int u = e.getFrom();
		int v = e.getTo();
		if (distTo[v] > distTo[u] + e.getW()) {
			distTo[v] = distTo[u] + e.getW();
			edgeTo[v] = u;
		}
	}

}

public class DAGShortestPath {

	public static void main(String[] args) throws IOException {
		@SuppressWarnings("resource")
		Scanner in = new Scanner(new BufferedReader(new FileReader("graphData")));
		int E, V;
		V = in.nextInt();
		E = in.nextInt();
		DWG graph = new DWG(V);
		for (int i = 0; i < E; i++) {
			graph.addEdge(new Edge(in.nextInt(), in.nextInt(), in.nextDouble()));
		}

		System.out.println(graph);
		
		@SuppressWarnings("unused")
		DAGSP ds = new DAGSP(graph, 5);
	}

}
/*
 * 5 --> 0: 0.73
5 --> 1: 0.32
5 --> 2: 0.6200000000000001
5 --> 3: 0.61
5 --> 4: 0.35
5 --> 5: 0.0
5 --> 6: 1.13
5 --> 7: 0.28*/
