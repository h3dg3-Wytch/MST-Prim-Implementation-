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

        BinaryHeap<Node> heap = new BinaryHeap<Node>();


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
            nodes.add(n);
        }

        for(Node n : nodes){
            n.setKey(Integer.MAX_VALUE);
            n.setParent(null);
        }
        Node root = nodes.get(0);
        root.setKey(0);
        for(Node n : nodes){
            heap.insert(n);
        }


        try{
            while(true){
                Node n = heap.deleteMin();
                System.out.println(heap);
                for(Edge e : n.getEdgeList()){
                    Node temp = nodes.get(e.getDestinationNode());
                    if(heap.contains(temp) && e.getWeight() < temp.getKey() ){
                        temp.setParent(n.getIdOfNode());
                        temp.setKey(e.getWeight());
                    }
                }
                result.add(n);
            }

        }catch (Exception e){

        }

        System.out.println(heap);

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
