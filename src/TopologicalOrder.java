

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;

class TO{
	private boolean[] mark;
	private Stack<Integer> orderStack;
	private DirectedGraph graph;
	
	public TO(DirectedGraph graph){
		this.graph = graph;
		int V = graph.getNumberOfVertices();
		mark = new boolean[V];
		orderStack = new Stack<>();
		for(int i=0; i<V; i++){
			if (!mark[i]){
				dfs(i);
			}
		}
		System.out.println();
	}

	private void dfs(int s) {
		mark[s] = true;
		//System.out.print(s+" ");
		for(Integer i: graph.getNeighbours(s)){
			if (!mark[i]){
				dfs(i);
			}
		}
		// the earliest in the order and hence the last in the stack
		orderStack.push(s);
		
	}
	
	public void printTopologicalOrder(){
		while(!orderStack.isEmpty()){
			System.out.print(orderStack.pop()+" ");
		}
		System.out.println();
	}
}
public class TopologicalOrder {
	public static void main(String[] args) throws IOException {
		@SuppressWarnings("resource")
		Scanner in = new Scanner(new BufferedReader(new FileReader("graphData")));
		int E, V;
		V = in.nextInt();
		E = in.nextInt();
		DirectedGraph graph = new DirectedGraph(V);
		for(int i=0; i<E; i++){
			int u, v;
			u = in.nextInt();
			v = in.nextInt();
			graph.addEdge(u, v);
		}
		
		graph.printTheGraph();
		
		TO to = new TO(graph);
		to.printTopologicalOrder();
		
	}
}
