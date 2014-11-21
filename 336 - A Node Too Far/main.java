/**
 * Problem ID    : 336                                 
 * Problem Name  : A Node Too Far                
 * Solved Using  : BFS
 * Author        : Muhammed Imran Hussain
 * E-mail        : imranweb7@gmail.com 
 * Website       : http://www.ihussain.info
*/
import java.util.*;

class Graph{
    private Map<Integer, List<Integer>> adjacent_list;
    
    public Graph(){
        adjacent_list = new HashMap<>();
    }
    
    public void setEdge(int node1, int node2){
        if(!adjacent_list.containsKey(node1)){
            adjacent_list.put(new Integer(node1), new LinkedList<Integer>());
        }
        
        if(!adjacent_list.containsKey(node2)){
            adjacent_list.put(new Integer(node2), new LinkedList<Integer>());
        }
        
        List<Integer> source = adjacent_list.get(node1);
        source.add(node2);
        
        List<Integer> destination = adjacent_list.get(node2);
        destination.add(node1);
    }
    
    List<Integer> getEdges(int node){
        return adjacent_list.get(node);
    }
    
    boolean isNodeExist(int node){
        return adjacent_list.containsKey(node);
    }
    
    int countTotalNode(){
        return adjacent_list.size();
    }
}

class Main{
    public static void main(String args[]){
        Main obj = new Main();
        obj.beginProcess();
    }
    
    void beginProcess(){
        Scanner in = new Scanner(System.in);
       
        int NC;
        int case_no = 1;

        while(in.hasNextLine()){
            NC = in.nextInt();

            if(NC == 0){
                break;
            }

            Graph graph = new Graph();

            for(int i=0; i<NC; i++){
                int node1 = in.nextInt();
                int node2 = in.nextInt();

                graph.setEdge(node1, node2);
            }

            while(in.hasNextInt()){                
                int query = in.nextInt();

                if(in.hasNextInt()){
                    int ttl = in.nextInt();

                    if(query == 0 && ttl == 0){
                        break;
                    }else{
                        int no_of_not_reachable_node = 0;

                        if(graph.isNodeExist(query)){
                            no_of_not_reachable_node = calculateNotReachableNodes(graph, query, ttl);  
                        }

                        System.out.println("Case "+case_no+": "+no_of_not_reachable_node+" nodes not reachable from node "+query+" with TTL = "+ttl+".");
                        case_no++;
                    }
                }                
            }
        }
    }
    
    int calculateNotReachableNodes(Graph g, int source, int ttl){
        int total_node = g.countTotalNode();
        int reachable = 1;
        
        Map<Integer, Integer> visited = new HashMap<>();
        visited.put(new Integer(source), new Integer(1));
        
        Map<Integer, Integer> level = new HashMap<>();
        level.put(new Integer(source), new Integer(ttl));
        
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(source);
        
        while(!queue.isEmpty()){
            int top = queue.poll();
            int size = g.getEdges(top).size();
                    
            for(int i=0; i<size; i++){
                int item = g.getEdges(top).get(i);
                
                int new_level = level.get(top) - 1;
                
                if(!visited.containsKey(new Integer(item))){
                    visited.put(new Integer(item), new Integer(1));
                    queue.add(item);
                    level.put(new Integer(item), new Integer(new_level));
                    
                    if(new_level >= 0){                       
                        reachable++;
                    }
                }
            }
        }
        
        return (total_node - reachable); 
    }
}