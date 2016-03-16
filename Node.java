import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by h3dg3wytch on 2/25/16.
 */
public class Node implements Comparable<Node>{

    private Integer idOfNode;
    private Edge edge;
    private List<Edge> edgeList;
    private Integer parent;
    private Integer key;



    public Node(){
        edgeList = new ArrayList<Edge>();
        edge = new Edge();
    }

    public Node(int num){
        this.idOfNode = num;
        edgeList = new ArrayList<Edge>();
        edge = new Edge();
    }




    @Override
    public boolean equals(Object object){
        //If this the object, then we return true
        if(this == object){
            return true;
        }
        //If we get
        if(object == null){
            return false;
        }
        if(object.getClass() != object.getClass()){
            return  false;
        }

        Node otherNode = (Node) object;
        if(idOfNode == null){
           if(otherNode.getIdOfNode() == null){
               return false;
           }
        } else if( !idOfNode.equals(otherNode.getIdOfNode()))
            return false;
        return true;
    }




    public void display(){
        //Print out the curent Node
        System.out.println("Node # " + idOfNode);
        //Print out the edges
        for(Edge e : edgeList){
            e.display();
        }
    }

    public List<Edge> getEdgeList() {
        return edgeList;
    }

    public Edge getEdge(){
        return this.edge;
    }

    public void update(){

        edgeList.add(this.edge);
        edge = new Edge();
    }

    public int minEdge(){

        int min = Integer.MAX_VALUE;
        int index = -1;
        for(int i = 0 ; i < edgeList.size(); i++){

            if(edgeList.get(i).getWeight() < min)
                min = edgeList.get(i).getWeight();
                index = i;
        }
        edgeList.remove(index);
        return min;

    }


    public void setIdOfNode(int idOfNode) {
        this.idOfNode = idOfNode;
    }

    public Integer getIdOfNode() {
        return idOfNode;
    }

    public Integer getParent() {
        return parent;
    }

    public void setParent(Integer parent) {
        this.parent = parent;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "ID: " + idOfNode + " Key: " + idOfNode + " Parent: " + parent;
    }

    @Override
    public int compareTo(Node o) {
        if(key < o.getKey())
            return -1;
        if(key > o.getKey())
            return 1;
        return 0;
    }
}
