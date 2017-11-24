
public class Neighbor {
	
	public Node node;
	public int weight;
	
	Neighbor(Node node, int weight){
		this.node=node;
		this.weight=weight;
	}
	
	public int getWeight(){
		return this.weight;
	}
	
	public Node getNode(){
		return this.node;
	}
	
}
