/**
 * Created on November 18,2017
 * @author akrish12
 *
 */


public class Dijkstra {
	
	/**
	 * Main Function to call Dijkstra algorithm
	 * @param args
	 */

	public static void main(String[] args) {
		Dijkstra dij = new Dijkstra();
		int[][] Mat = {  {1, 2, 5}, {2, 3, 7} };
		int N = 3;
		int E = 2;
		int S = 3;
		dij.Dijkstra_alg(N,E,Mat,S);
	}

	/**
	 * Function that calculates the distance and usp
	 * @param n
	 * @param e
	 * @param mat
	 * @param s
	 * @return
	 */
	public static int[][] Dijkstra_alg(int n, int e, int[][] mat, int s) {
		int [][] conngraph = new int[n][n];
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				conngraph[i][j]=0;
			}
		}
		for(int i=0; i<e; i++){
			conngraph[mat[i][0]-1][mat[i][1]-1] = mat[i][2];
			conngraph[mat[i][1]-1][mat[i][0]-1] = mat[i][2];
		}

		int weight[][]= new int[n][2];
		Boolean visited_node[]=new Boolean[n];

		for(int i=0;i<n;i++) {
			weight[i][0] = Integer.MAX_VALUE;
			weight[i][1] = 1;
			visited_node[i]=false;
		}

		weight[s-1][0]=0;

		for(int i=0;i<n-1;i++) {
			int u =min_distance(weight, visited_node, n);
			visited_node[u]=true;

			for(int v=0;v<n;v++){
				if(!visited_node[v] && conngraph[u][v]!=0 && weight[u][0] != Integer.MAX_VALUE && weight[u][0]+conngraph[u][v] < weight[v][0]) {
					weight[v][0] = weight[u][0] + conngraph[u][v];
					weight[v][1] = weight[u][1];
				}
				else if(!visited_node[v] && conngraph[u][v]!=0 && weight[u][0]+conngraph[u][v] == weight[v][0]){     //For unique Shortest Path
					weight[v][1]=0;
				}
			}
		}

		for(int i = 0; i<n; i++) {
			System.out.println(weight[i][0] + " " + weight[i][1]);
		}
		return weight;
	}
	
	/**
	 * Function to calculate minimum distance 
	 * @param weight
	 * @param visited_node
	 * @param N
	 * @return
	 */
	public static final int min_distance(int[][] weight, Boolean visited_node[], int N) {
		int index = -1;
		int	min = Integer.MAX_VALUE;

		for(int i=0;i<N;i++){
			if(visited_node[i] == false && weight[i][0] < min) {
				min = weight[i][0];
				index = i;
			}
		}
		System.out.println("find min" +index);
		return index;
	}
}
