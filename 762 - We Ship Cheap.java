/**
 * Problem ID    : 762                                 
 * Problem Name  : We Ship Cheap                    
 * Solved Using  : BFS
 * Author        : Muhammed Imran Hussain
 * E-mail        : imranweb7@gmail.com 
 * Website       : http://www.ihussain.info
*/

import java.util.*;

class Graph{
    private Map<Integer, List<Integer>> Adjacecny_List;
    
    public Graph(int Number_of_Nodes){
        Adjacecny_List = new HashMap<>();
        
        for(int i=0; i<Number_of_Nodes; i++){
            Adjacecny_List.put(new Integer(i), new LinkedList<Integer>());
        }
    }
    
    void setEdge(int node1, int node2){
        List<Integer> source = Adjacecny_List.get(node1);
        source.add(new Integer(node2));
        
        List<Integer> destination = Adjacecny_List.get(node2);
        destination.add(new Integer(node1));
    }
    
    List<Integer> getEdges(int source){
        return Adjacecny_List.get(source);
    }
}

class Main{
    public static void main(String args[]){
        Main obj = new Main();
        obj.beginProcess();
    }
    
    void beginProcess(){
        Scanner in = new Scanner(System.in);
        
        int count=0;
        while(in.hasNextInt()){
            int links = in.nextInt();
            
            //assuming double nodes present in the graph
            int total_nodes = links*2;
            
            //Strings nodes index
            List<String> node_index = new LinkedList<String>();
            
            //inititalizing graph
            Graph graph = new Graph(total_nodes);
            
            for(int i=0; i<links; i++){
                String node1 = in.next();
                String node2 = in.next();
                
                if(!node_index.contains(node1)){
                    node_index.add(node1);
                }
                
                 if(!node_index.contains(node2)){
                    node_index.add(node2);
                }
                 
                graph.setEdge(node_index.indexOf(node1), node_index.indexOf(node2));
            }
            
            int source = node_index.indexOf(in.next());
            int destination = node_index.indexOf(in.next());
            
            boolean hasRoute = false;
            
            if(count > 0){
                System.out.println("");
            }
            
            if(source != -1 && destination != -1){
                Map<Integer, Integer> parents = bfs(graph, source, destination);
                
                if(parents.size() > 1){
                    hasRoute = true;
                    
                    //to store path from destination to source
                    List path = new ArrayList();
                    
                    int parent = destination;
                    while(source != parent){
                        path.add(node_index.get(parents.get(parent))+" "+node_index.get(parent));
                        parent = parents.get(parent);
                    }
                    
                    //list path from source to destination
                    for(int i=path.size()-1; i>=0; i--){
                        System.out.println(path.get(i)); 
                    }
                }
            }
            
            if(!hasRoute){
                System.out.println("No route");
            }
            
            String blank_line = in.nextLine();
            
            count++;
        }        
    }
    
    /*
     * Return parents map with node-parent-node
    */
    Map<Integer, Integer> bfs(Graph g, int s, int d){
        Map<Integer, Integer> visited = new HashMap<>();
        Map<Integer, Integer> parent = new HashMap<>();
        
        visited.put(new Integer(s), new Integer(1));
        
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(s);
        
        while(!queue.isEmpty()){
            int top = queue.poll();            
            int size = g.getEdges(top).size();
            
            if(size > 0){
                for(int i=0; i<size; i++){
                    int edge = g.getEdges(top).get(i);
                    
                    if(!visited.containsKey(new Integer(edge))){
                        visited.put(new Integer(edge), new Integer(1));
                        queue.add(edge);
                        parent.put(new Integer(edge), new Integer(top));
                    }else{
                        if(edge == d){
                            break;
                        }
                    }
                }
            }
        }
        
        return parent;
    }
}