import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;


/**
 * Created by h3dg3wytch on 3/15/16.
 */
public class MSTPrim {

    private static PriorityQueue<Node> weights;
    private static List<Node> nodes;
    private static NodeComparator nodeComparable;
    private static List<Node> result;


    public MSTPrim(){

        nodeComparable = new NodeComparator();
        weights = new PriorityQueue<Node>(nodeComparable);
        nodes = new ArrayList<Node>();
        result =  new ArrayList<Node>();

    }

    public void execute( int graph[][] ) {
        //We make the heap
        TheHeap<Node> heap = new TheHeap<Node>();


        //Transfrom the matrix into the nodes
        for (int i = 0; i < graph.length; i++) {
            //New node at with id i
            Node n = new Node(i);
            //new Edge, that as long as it isn't zero, is will add that num to its weight, we also keep track of source and destination node
            for (int j = 0; j < graph[i].length; j++) {
                Edge edge = new Edge();
                if (graph[i][j] != 0) {
                    edge.setSourceNode(i);
                    edge.setDestinationNode(j);
                    edge.setWeight(graph[i][j]);
                    n.getEdgeList().add(edge);
                }
            }
            //add the nodes to the node list
            nodes.add(n);
        }

        //For each node, we set the key to infinity and it's parent to null
        for(Node n : nodes){
            n.setKey(Integer.MAX_VALUE);
            n.setParent(null);
        }
        //We set the root key equal to zero
        Node root = nodes.get(0);
        root.setKey(0);
        //Put all the nodes in the heap
        for(Node n : nodes){
            heap.insert(n);
        }

        //I was lazy and put this in a try catch block, if it ends that means we got to the end
        try{
            while(true){
                //Get node with the min key
                Node n = heap.deleteMin();
                //Print out what the heap looks like
                System.out.println(heap);
                //Go through each ede in the node
                for(Edge e : n.getEdgeList()){
                    //We get the temp node, we are able to do this due to the ids that we made
                    Node temp = nodes.get(e.getDestinationNode());
                    //If the heap contains the node, and the weight of the edge is less than that of the key
                    if(heap.contains(temp) && e.getWeight() < temp.getKey() ){
                        //Set the parent node to the current min key node
                        temp.setParent(n.getIdOfNode());
                        //Set the key to the weight of the edge
                        temp.setKey(e.getWeight());
                    }
                }
                //Ad d this min node to the results
                result.add(n);
            }

        }catch (Exception e){
            //We come here when the heap is empty

        }

        //Test case to make sure the heap is empty
        System.out.println(heap);
        //Print out the result node's key

        for(Node n : result){
            System.out.println(n.getKey());
        }






    }

    public class NodeComparator implements Comparator<Node>{
        @Override
        public int compare(Node o1, Node o2) {
            if(o1.getKey() < o2.getKey())
                return -1;
            if(o1.getKey() > o2.getKey())
                return 1;
            return 0;
        }


    }




    public static void main(String[] args){
        int graph[][] = new int[][] {{0, 2, 0, 6, 0},
                {2, 0, 3, 8, 5},
                {0, 3, 0, 0, 7},
                {6, 8, 0, 0, 9},
                {0, 5, 7, 9, 0},
        };

        MSTPrim t = new MSTPrim();
        t.execute(graph);




    }




}
