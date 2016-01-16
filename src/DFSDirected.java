

public class DFSDirected{
	private boolean[] mark;
	private int[] toEdge;
	private int s;
	private DirectedGraph graph;
	
	public DFSDirected(DirectedGraph g, int s){
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