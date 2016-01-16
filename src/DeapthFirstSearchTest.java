

import java.io.FileReader;
import java.util.Scanner;

class DFS{
	private boolean[] mark;
	private int[] toEdge;
	private int s;
	private Graph graph;
	
	public DFS(Graph g, int s){
		this.graph = g;
		this.s = s;
		mark = new boolean[g.getNumberOfVertices()];
		toEdge = new int[g.getNumberOfVertices()];
	}
	
	public void dfs(){
		dfs(s);
		System.out.println();
	}
	
	private void dfs(int s){
		mark[s] = true;
		System.out.print(s+" ");
		for(Integer i: graph.getNeighbours(s)){
			if (mark[i]==false){
				dfs(i);
				toEdge[i] = s;
			}
		}
	}
	
	// print the path from s to v
	public void printRoute(int v){
		System.out.print(v+" ");
		int track = toEdge[v];
		while(true){
			if (track == s){
				break;
			} else{
				System.out.print(track + " ");
				track = toEdge[track];
			}
		}
		System.out.println(s);
	}
}

public class DeapthFirstSearchTest {
	public static void main(String[] args) throws Exception {
		FileReader reader = new FileReader("graphData");
		@SuppressWarnings("resource")
		Scanner in = new Scanner(reader);
		
		int E, V;
		V = in.nextInt();
		E = in.nextInt();
		Graph graph = new Graph(V);
		for(int i=0; i<E; i++){
			int u, v;
			u = in.nextInt();
			v = in.nextInt();
			graph.addEdge(u, v);
		}
		
		graph.printTheGraph();
		
		DFS search = new DFS(graph, 0);  // s = 0
		search.dfs();
		search.printRoute(4);
	}
}
