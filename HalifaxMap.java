
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import java.util.Map.Entry;


/**
 * @author SaiPavan_Akuralapu
 *
 */
public class HalifaxMap {
	static int noofvertices=0;
	static Graph graph=new Graph();
	
	//creating Hashmap to store co-ordinates and key to co-ordinate
	
	Map<Integer,List<Integer>> vertices= new HashMap<Integer, List<Integer>>();

// records a new intersection for the city
	Boolean newIntersection(int x,int y)
	{
		
		
		if(noofvertices>0)
		{
			
			for(Entry<Integer, List<Integer>> entry: vertices.entrySet())
			{
			 //will check if the co-ordinate values are already presented in the hashmap	
				
				if(entry.getValue().get(0)==x && entry.getValue().get(1)==y)
				{
					return false;
				}
			}
			
			
			List<Integer> coordinate=new ArrayList<Integer>();

			//adding coordinates to the list of an hashmap
			
			coordinate.add(x);
			coordinate.add(y);
		//adding vertices into hashmap
			vertices.put(noofvertices,coordinate);
			//incrementing noofvertices to count totalnoofvertices
			noofvertices++;
			return true;
		}
		else
		{
			List<Integer> coordinate=new ArrayList<Integer>();

			coordinate.add(x);
			coordinate.add(y);
			vertices.put(noofvertices,coordinate);
			noofvertices++;
			
			return true;
		}


	}
	//distance will calculate the weight between vertices
	static int distance(int x1,int y1,int x2,int y2)
	{
		int x=x1-x2;
		int y=y1-y2;
		double sqrt=(Math.pow(x, 2)+Math.pow(y, 2));
		return (int)Math.sqrt(sqrt);
	}
	
	//defineRoad will create the edge between the vertices and weight 
	Boolean defineRoad(int x1,int y1,int x2,int y2)
	{
//Initializing destination and source to -1 
		int destination = -1,source = -1,weight=0;
		//checks whether the vertices are present or not
		for(Entry<Integer, List<Integer>> entry: vertices.entrySet())
		{
			if(entry.getValue().get(0)==x1 && entry.getValue().get(1)==y1)
			{
				source=entry.getKey();
			}
			if(entry.getValue().get(0)==x2 && entry.getValue().get(1)==y2)
			{
				destination=entry.getKey();
			}
		}
		//if source and destination vertices are not present returns false 
		if(source==-1 || destination == -1 || source==destination)
		{
			return false;
		}
		
		
		weight=distance(x1, y1, x2, y2);

		//calls addEdge to add into edges into graph
		boolean flag=graph.addEdge(source, destination, weight,noofvertices);



		return flag;
	}
//navigate will print the sequence of coordinates with shortest path
	void navigate(int x1,int y1,int x2,int y2)
	{ 
		//will find the vertex from hashmap
		int sourcevertex=find(x1,y1);
		int destvertex=find(x2,y2);
		//checks whether the vertices are already present or not
		if(sourcevertex==-1||destvertex==-1 || sourcevertex==destvertex)
		{
			System.out.println("no path");
			
		}
		
		else
		{
		try
		{
			//calls the getPath to get shortest path from graph
		String path= graph.getPath(graph, sourcevertex, destvertex);
		
		StringTokenizer st=new StringTokenizer(path);
		while(st.hasMoreTokens())
		{
			
			int i=Integer.parseInt(st.nextToken());
			for(Entry<Integer, List<Integer>> entry: vertices.entrySet())
			{
				if(entry.getKey()==i)
				{
					System.out.println(entry.getValue().get(0)+"	"+entry.getValue().get(1));
				}
			}
		}
		}
		catch(Exception e)
		{
			System.out.println("no path");
		}
		}
		
		
	}
	
	//find method will check the coordinates from the hashmap 
	int find(int xcoordinate,int ycordinate)
	{
		int value=-1;
		for(Entry<Integer, List<Integer>> entry: vertices.entrySet())
		{
			if(entry.getValue().get(0)==xcoordinate && entry.getValue().get(1)==ycordinate)
			{
				value= entry.getKey();
			}
		}
		return value;
	}


}
