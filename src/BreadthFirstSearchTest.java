

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

class BFS {
	private Graph graph;
	private int s;
	private boolean[] mark;
	private int[] toEdge;
	private int[] dist;

	public BFS(Graph graph, int s) {
		mark = new boolean[graph.getNumberOfVertices()];
		toEdge = new int[graph.getNumberOfVertices()];
		dist = new int[graph.getNumberOfVertices()];
		this.graph = graph;
		this.s = s;
	}

	// T(n) = O(|E| + |V|)
	public void bfs() {
		System.out.println("The BFS traversal is: ");
		Queue<Integer> queue = new LinkedList<>();
		System.out.print(s+" ");
		queue.add(s);
		mark[s] = true;
		dist[s]=0;
		while (!queue.isEmpty()) {
			Integer u = queue.poll();
			for (Integer v : graph.getNeighbours(u)) {
				if (!mark[v]) {
					System.out.print(v+" ");
					queue.add(v);
					mark[v] = true;
					toEdge[v] = u;
					dist[v] = dist[u]+1;
				}
			}
		}
		System.out.println();
	}

	public void shortestPath(int v) {
		bfs();
		Stack<Integer> stack = new Stack<>();
		stack.push(v);
		int track = v;
		while (true) {
			if (track == s) {
				break;
			}
			track = toEdge[track];
			stack.push(track);
		}
		System.out.println("The shortest path from " + s + " to " + v +" is: ");
		while(!stack.isEmpty()){
			System.out.print(stack.pop()+" ");
		}
		System.out.println();
	}
	
	public void shortestDistance(int v){
		System.out.println("Shortest distance: "+dist[v]);
	}
}

public class BreadthFirstSearchTest {

	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("graphData"));
		int V, E;
		V = Integer.parseInt(in.readLine().trim());
		E = Integer.parseInt(in.readLine().trim());
		Graph graph = new Graph(V);
		for (int i = 0; i < E; i++) {
			int u, v;
			String s = in.readLine().trim();
			int x = s.indexOf(' ');
			u = Integer.parseInt(s.substring(0, x));
			v = Integer.parseInt(s.substring(x + 1));
			graph.addEdge(u, v);
		}

		graph.printTheGraph();

		Integer s = sc.nextInt();
		Integer i = sc.nextInt();
		BFS search = new BFS(graph, s);
		search.shortestPath(i);
		search.shortestDistance(i);
		in.close();
	}

}
