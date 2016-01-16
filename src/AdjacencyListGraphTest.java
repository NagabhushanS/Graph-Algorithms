

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

class Graph {
	private Map<Integer, ArrayList<Integer>> adjLists;
	private int numberOfVertices;
	private int numberOfEdges;
	
	public Graph(int V){
		adjLists = new HashMap<>(V);
		for(int i=0; i<V; i++){
			adjLists.put(i, new ArrayList<Integer>());
		}
		this.numberOfVertices = V;
		this.numberOfEdges = 0;
	}
	
	public int getNumberOfEdges(){
		return this.numberOfEdges;
	}
	public int getNumberOfVertices(){
		return this.numberOfVertices;
	}
	
	public void addVertex(){
		adjLists.put(getNumberOfVertices(), new ArrayList<Integer>());
		this.numberOfVertices++;
	}
	
	public void addEdge(int u, int v){
		adjLists.get(u).add(v);
		adjLists.get(v).add(u);
		this.numberOfEdges++;
	}
	
	public ArrayList<Integer> getNeighbours(int u){
		return new ArrayList<Integer>(adjLists.get(u));
	}

	public void printTheGraph() {
		for(Entry<Integer, ArrayList<Integer>> list: adjLists.entrySet()){
			System.out.print(list.getKey()+": ");
			for(Integer i: list.getValue()){
				System.out.print(i+" ");
			}
			System.out.println();
		}
		
	}
}

@SuppressWarnings("resource")
public class AdjacencyListGraphTest {

	public static void main(String[] args) throws Exception {
		FileReader reader = new FileReader("graphData");
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
		

	}
}
