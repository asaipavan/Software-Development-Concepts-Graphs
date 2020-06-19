

import java.util.LinkedList; 
import java.util.TreeMap;

//creating a graph with adjacencyList
public class Graph { 
	static TreeMap<Integer,String> weight=new TreeMap<Integer,String>();
	int vertices;
	LinkedList<AdjacencyListNode>[] adjacencyList;
	LinkedList<AdjacencyListNode>[] newadjacencyList=null;
	@SuppressWarnings("unchecked")
	public Graph() { 
		this.vertices=40; 
		adjacencyList= new LinkedList[vertices]; 
		for(int i=0;i < vertices;i++) 
		{ 
			adjacencyList[i]=new LinkedList<>();
		}
	}

	
	// will addEdge into adjacencylist
	@SuppressWarnings("unchecked")
	public boolean addEdge(int source, int destination,int weight,int noofvertices)
	{
		//checks whether the adjacency list length is less or not or else will create list with new size
		if(adjacencyList.length<noofvertices)
		{
			
				newadjacencyList=new LinkedList[noofvertices];
				for (int i = 0; i < adjacencyList.length; i++) 
				{ 
			        newadjacencyList[i] = adjacencyList[i]; 
			    }
				adjacencyList=newadjacencyList;
				vertices=noofvertices;
				 for (int i =adjacencyList.length ;i <vertices ; i++) 
				 {
			            adjacencyList[i] = new LinkedList<>();
			      }
				newadjacencyList=null;
			}
		for(int i=0;i<adjacencyList.length;i++)
		{
			LinkedList<AdjacencyListNode> adjacencyList1=adjacencyList[i];
		
			for(int j=0;j<adjacencyList1.size();j++)
			{
				//checks whether the edge is already present or not and returns false if it's already present
				AdjacencyListNode presented=adjacencyList1.get(j);
				if(presented.source==source&&presented.destination==destination || presented.destination==source&&presented.source==destination)
				{
				return false;	
				}
			}
		}	
		//adding Edges into adjacency list with both source and destination as its undirected graph
		
		AdjacencyListNode node=new AdjacencyListNode(source,destination,weight);
		adjacencyList[source].addFirst(node);
		node=new AdjacencyListNode(destination,source,weight);
		adjacencyList[destination].addFirst(node);
		return true;

	}

	//returns the shortest path between source and destination
	public String getPath(Graph graph, int sourceIndex, int destinationIndex){
		boolean[] visited = new boolean[graph.vertices];
		visited[sourceIndex] = true;
		int nodeWeight=0;
		getShortestPath(graph, sourceIndex,destinationIndex, "", visited,nodeWeight);
		return weight.get(weight.firstKey());
	}


	public void getShortestPath(Graph graph, int sourceIndex, int destinationIndex, String path, boolean[] visited,int sourceIndexWeight){
		String newPath = path +  " " + sourceIndex;
		visited[sourceIndex] = true;
		LinkedList<AdjacencyListNode> list = graph.adjacencyList[sourceIndex];


		for (int i = 0; i <list.size() ; i++) {
			AdjacencyListNode node = list.get(i);
			int nodeWeight=sourceIndexWeight;
			if(node.destination!=destinationIndex && visited[node.destination]==false){
				nodeWeight=nodeWeight+node.weight;
				getShortestPath(graph,node.destination,destinationIndex,newPath,visited,nodeWeight);

			}else if(node.destination==destinationIndex){
				String newMapPath=newPath + " " + node.destination;
				weight.put(nodeWeight+node.weight, newMapPath);


			}
		}
		visited[sourceIndex] = false;
	}


}

