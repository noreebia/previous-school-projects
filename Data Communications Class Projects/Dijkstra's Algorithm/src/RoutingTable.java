
public class RoutingTable {

	int[][] data;

	public RoutingTable(int nodeNumber){
		data=new int[5][2];
		setTable(nodeNumber);
	}
	public void setTable(int nodeNumber){
		switch(nodeNumber){
		case 1:
			data[0][0]=2;
			data[0][1]=2;
			data[1][0]=3;
			data[1][1]=4;
			data[2][0]=4;
			data[2][1]=4;
			data[3][0]=5;
			data[3][1]=4;
			data[4][0]=6;
			data[4][1]=4;
			break;
		case 2:
			data[0][0]=1;
			data[0][1]=1;
			data[1][0]=3;
			data[1][1]=3;
			data[2][0]=4;
			data[2][1]=4;
			data[3][0]=5;
			data[3][1]=4;
			data[4][0]=6;
			data[4][1]=4;
			break;
		case 3:
			data[0][0]=1;
			data[0][1]=5;
			data[1][0]=2;
			data[1][1]=5;
			data[2][0]=4;
			data[2][1]=5;
			data[3][0]=5;
			data[3][1]=5;
			data[4][0]=6;
			data[4][1]=5;
			break;
		case 4:
			data[0][0]=1;
			data[0][1]=2;
			data[1][0]=2;
			data[1][1]=2;
			data[2][0]=3;
			data[2][1]=5;
			data[3][0]=5;
			data[3][1]=5;
			data[4][0]=6;
			data[4][1]=5;
			break;
		case 5:
			data[0][0]=1;
			data[0][1]=4;
			data[1][0]=2;
			data[1][1]=4;
			data[2][0]=3;
			data[2][1]=3;
			data[3][0]=4;
			data[3][1]=4;
			data[4][0]=6;
			data[4][1]=6;
			break;
		case 6:
			data[0][0]=1;
			data[0][1]=5;
			data[1][0]=2;
			data[1][1]=5;
			data[2][0]=3;
			data[2][1]=5;
			data[3][0]=4;
			data[3][1]=5;
			data[4][0]=5;
			data[4][1]=5;
			break;
		}
	}
}
