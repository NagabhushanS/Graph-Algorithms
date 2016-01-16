

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

class CC{
	private boolean[] mark;
	private int[] id;
	private int counter;
	private Graph graph;
	
	public CC(Graph graph){
		this.graph = graph;
		counter=0;
		mark = new boolean[graph.getNumberOfVertices()];
		id = new int[graph.getNumberOfVertices()];
		int V = graph.getNumberOfVertices();
		for(int i=0; i<V; i++){
			if (!mark[i]){
				dfs(i);
				counter++;
			}
		}
		
	}
	
	private void dfs(int s) {
		mark[s] = true;
		id[s] = counter;
		for(Integer i: graph.getNeighbours(s)){
			if (!mark[i]){
				dfs(i);
			}
		}
		
	}

	public boolean isConnected(int u, int v){
		return id[u]==id[v];
	}
}

public class ConnectedComponentsTest {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("graphData"));
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
		
		CC cc = new CC(graph);
		System.out.println(cc.isConnected(0, 1));
	}

}
