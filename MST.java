import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.PriorityQueue;

/**
 * Created by h3dg3wytch on 3/15/16.
 */
public class MST {

    //Number of vertices in the graph
    private static final int V = 5;


    //A utility function to find the vertex with the minimum key
    //value, from the set of vertices not yet included in MST
    public int minKey(int key[], Boolean mstSet[]) {
        //initialize min value
        int min = Integer.MAX_VALUE;
        //initialize the index
        int min_index = -1;
        //loop through all the vertices
        for (int v = 0; v < V; v++) {
            //if the index in the set is false, and the key is less then min
            //set the min equal to the key, and set the index equal to
            //v index
            if (mstSet[v] == false && key[v] < min) {
                min = key[v];
                min_index = v;
            }
        }
        return min_index;
    }

    //Pretty print for the constructor MST stored in parent[]
    public void printMST(int parent[], int n, int graph[][]) {
        System.out.println("Edge   Weight");
        for (int i = 1; i < V; i++) {
            System.out.println(parent[i] + " - " + i + "  " + graph[i][parent[i]]);
        }
    }

    public void primMST(int graph[][]) {
        // Array to store constructed MST
        int parent[] = new int[V];
        //Key values to pick minimum weight edge in cut
        int key[] = new int[V];


        //to represent set of vertices not yet included in MST
        Boolean mstSet[] = new Boolean[V];

        //Initialuze all keys as INFINITE
        for (int i = 0; i < V; i++) {
            key[i] = Integer.MAX_VALUE;
            mstSet[i] = false;
        }
        //Always include first 1st vertex in MST.
        //Make key 0 so that this vertex is picked as first vertex
        key[0] = 0;
        parent[0] = -1;    //first node is always root in MST

        //The MST will have V vertices
        for (int count = 0; count < V - 1; count++) {

            //pick the minimum key vertex from the set of vetices
            //not yet included in MST
            int u = minKey(key, mstSet);

            //add the picked vertex to the mst set
            mstSet[u] = true;

            //from the first vertex to the last
            for (int v = 0; v < V; v++) {
                //if the graph doesn't equal 0 and the vertex hasn't been vvisited
                // and the key is is then the key currently there
                if (graph[u][v] != 0 && mstSet[v] == false && graph[u][v] < key[v]) {
                    //Add this to the parent string
                    parent[v] = u;
                    //Make u's key, the new key to compare
                    key[v] = graph[u][v];
                }
            }

        }

        printMST(parent, V, graph);
    }



    public static void main (String[] args)
    {
        /* Let us create the following graph
           2    3
        (0)--(1)--(2)
        |    / \   |
        6| 8/   \5 |7
        | /      \ |
        (3)-------(4)
             9          */
        MST t = new MST();
        int graph[][] = new int[][] {{0, 2, 0, 6, 0},
                {2, 0, 3, 8, 5},
                {0, 3, 0, 0, 7},
                {6, 8, 0, 0, 9},
                {0, 5, 7, 9, 0},
        };

        // Print the solution
        t.primMST(graph);
    }
}


