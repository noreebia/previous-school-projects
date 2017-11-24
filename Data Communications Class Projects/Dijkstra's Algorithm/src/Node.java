import java.util.ArrayList;

public class Node {

	public String name;
	public boolean isVisited;
	public int distance;
	public int[] neighborWeights;
	public ArrayList<Neighbor> neighbors;
	public int xCoordinate;
	public int yCoordinate;
	
	Node(String name, int x, int y){
		this.name=name;
		isVisited=false;
		distance=100;
		neighbors=new ArrayList<>();
		this.xCoordinate = x;
		this.yCoordinate= y;
	}
	
	public int getX(){
		return this.xCoordinate;
	}
	
	public int getY(){
		return this.yCoordinate;
	}
	
	public String getName(){
		return this.name;
	}

	public void setVisitedState(Boolean bool){
		this.isVisited=bool;
	}
	
	public void addNeighbors(Node neighborNode,int weight){
		neighbors.add(new Neighbor(neighborNode,weight));
	}
	
	public ArrayList<Neighbor> getNeighbors(){
		return this.neighbors;
	}
	
	
	public void setDistance(int distance){
		this.distance=distance;
	}
	
	public boolean getVisitedState(){
		return this.isVisited;
	}
	
	public int getDistance(){
		return this.distance;
	}
}
