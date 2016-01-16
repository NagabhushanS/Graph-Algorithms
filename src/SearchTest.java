

import java.io.FileReader;
import java.util.Scanner;

public class SearchTest {

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
		
		System.out.print("Enter the source vertex: ");
		int s = in.nextInt();
		Search search = new Search(graph, s);
		for(int i=0; i<V; i++){
			if (search.marked(i)){
				System.out.print(i+" ");
			}
		}
		System.out.println();
		
		if (search.count() == V){
			System.out.println("Connected");
		} else{
			System.out.println("Not connected");
		}

	}

}
