import java.awt.Graphics;
import java.util.ArrayList;


public class Dijkstra {
	ArrayList<Node> unVisitedNodes=new ArrayList<>();
	ArrayList<Node> visitedNodes=new ArrayList<>();
	Node[] totalNodes;
	Node source;
	Node node1;
	Node node2;
	Node node3;
	Node node4;
	Node node5;
	Node node6;
	String path;

	Dijkstra(){
		path="";
		node1=new Node("v1", 50, 100);
		node2=new Node("v2", 100, 50);
		node3=new Node("v3", 150,50);
		node4=new Node("v4", 115, 125);
		node5=new Node("v5", 165, 125);
		node6=new Node("v6", 200, 70);

		node1.addNeighbors(node2, 2);
		node1.addNeighbors(node3, 5);
		node1.addNeighbors(node4, 1);

		node2.addNeighbors(node1, 3);
		node2.addNeighbors(node3, 3);
		node2.addNeighbors(node4, 2);

		node3.addNeighbors(node1, 8);
		node3.addNeighbors(node2, 6);
		node3.addNeighbors(node4, 3);
		node3.addNeighbors(node5, 1);
		node3.addNeighbors(node6, 5);

		node4.addNeighbors(node1, 7);
		node4.addNeighbors(node2, 2);
		node4.addNeighbors(node3, 3);
		node4.addNeighbors(node5, 1);

		node5.addNeighbors(node3, 1);
		node5.addNeighbors(node4, 1);
		node5.addNeighbors(node6, 2);

		node6.addNeighbors(node3, 8);
		node6.addNeighbors(node5, 4);

		unVisitedNodes.add(node1);
		unVisitedNodes.add(node2);
		unVisitedNodes.add(node3);
		unVisitedNodes.add(node4);
		unVisitedNodes.add(node5);
		unVisitedNodes.add(node6);

		/*
		System.out.println(unVisitedNodes.get(0).getNeighbors().get(0).getNode().getName());
		System.out.println(unVisitedNodes.get(0).getNeighbors().get(0).getNode().getDistance());
		unVisitedNodes.get(0).getNeighbors().get(0).getNode().setDistance(10);
		System.out.println(node2.getDistance());
		*/
		//performDijkstra(node1);
	}
	
	public void setSource(Node source){
		this.source=source;
	}

	public void performDijkstra(){
		node1.setDistance(100);
		node2.setDistance(100);
		node3.setDistance(100);
		node4.setDistance(100);
		node5.setDistance(100);
		node6.setDistance(100);
		source.setDistance(0);
		Node currentNode=source;
		Node tempNode=null;
		int tempDistance;
		int i,k;
		int count=0;
		while(count < 6){
			tempDistance=100;
			currentNode.setVisitedState(true);

			if(visitedNodes.add(currentNode)){
				System.out.println("visited "+currentNode.getName());
			}

			if(unVisitedNodes.remove(currentNode)){
				System.out.println("Removed "+currentNode.getName());
			}
			
			for(i=0; i<currentNode.getNeighbors().size();i++){
				if(currentNode.getNeighbors().get(i).getNode().isVisited == false){
					int alt=currentNode.getDistance()+ currentNode.getNeighbors().get(i).getWeight();
					if(alt < currentNode.getNeighbors().get(i).getNode().getDistance()){
						currentNode.getNeighbors().get(i).getNode().setDistance(alt);
					}
				}
				else{
					continue;
				}
			}	

			for(k=0;k<unVisitedNodes.size();k++){
				if( unVisitedNodes.get(k).isVisited == true){
					continue;
				}
				else{
					if(tempDistance > unVisitedNodes.get(k).getDistance()){
						tempDistance = unVisitedNodes.get(k).getDistance();
						tempNode= unVisitedNodes.get(k);
					}
				}
			}
			
			currentNode=tempNode;
			System.out.println("Current node is "+currentNode.getName());
			count++;
			showUnvisited();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		int p=0;
		for(p=0;p<visitedNodes.size();p++){
			System.out.print(visitedNodes.get(p).getName()+" ");
			
			path=path + visitedNodes.get(p).getName();
			if(p < visitedNodes.size()-1){
				path=path+" → ";	
			}
		}
		flushArrayLists();
	}
	
	public void flushArrayLists(){
		
		node1.setVisitedState(false);
		node2.setVisitedState(false);
		node3.setVisitedState(false);
		node4.setVisitedState(false);
		node5.setVisitedState(false);
		node6.setVisitedState(false);
		
		unVisitedNodes.add(node1);
		unVisitedNodes.add(node2);
		unVisitedNodes.add(node3);
		unVisitedNodes.add(node4);
		unVisitedNodes.add(node5);
		unVisitedNodes.add(node6);
		visitedNodes.clear();
	}

	public void showUnvisited(){
		int i;
		if(unVisitedNodes.size() > 0){
			for(i=0;i< unVisitedNodes.size();i++){
				System.out.print(unVisitedNodes.get(i).getName()+" ");
				System.out.print(unVisitedNodes.get(i).getDistance()+" ");
			}
			System.out.println("");
		}
	}

}
