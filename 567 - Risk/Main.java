/**
 * Problem ID    : 567                                 
 * Problem Name  : Risk                      
 * Solved Using  : BFS
 * Author        : Muhammed Imran Hussain
 * E-mail        : imranweb7@gmail.com 
 * Website       : http://www.ihussain.info
*/
import java.io.*;
import java.util.*;

class Graph{
    private Map<Integer, List<Integer>> Adjacency_List;
    
    public Graph(){
        Adjacency_List = new HashMap<>();
    }
    
    void setEdge(int source, int destination){
        if(!Adjacency_List.containsKey(source)){
            Adjacency_List.put(new Integer(source), new LinkedList<Integer>());
        }
        
        if(!Adjacency_List.containsKey(destination)){
            Adjacency_List.put(new Integer(destination), new LinkedList<Integer>());
        }
        
        List<Integer> head = Adjacency_List.get(source);
        head.add(destination);
        
        List<Integer> tail = Adjacency_List.get(destination);
        tail.add(source);        
    }
    
    List<Integer> getEdges(int source){
        return Adjacency_List.get(source);
    }
}

class Main{
    private final int MAX_NODE = 20;    
    BufferedReader reader;
    Graph graph;
    
    public static void main(String args[]){
        Main obj = new Main();
        obj.beginProcess();
    }
    
    void parseLineIntoEdge(int line_no, String line){
        String[] token = line.split("\\s");  
        
        if(token.length > 0){
            int shared_borders_no = Integer.parseInt(token[0]);
            
            if(token.length != shared_borders_no){
                for(int i=1; i<token.length; i++){
                    //X distinct integers J greater than 
                    //I and not exceeding 20                    
                    int country = Integer.parseInt(token[i]);
                    
                    if(country > line_no && country <= MAX_NODE){
                        graph.setEdge(i, country);
                    }
                }
            }
        }
    }
    
    void processShortestPathResult(int T){
        try {
            //Line 20 of the test set
            //contains a single integer (1 <= N <= 100)
            int N = Integer.parseInt(reader.readLine());
            
            if(N >= 1 && N <= 100){
                System.out.println("Test Set #"+T);
                
                //The next N lines each contain
                //exactly two integers (1<= A, B <= 20; A != B)
                while(N > 0){
                    try {
                        String[] line_token = (reader.readLine()).split("\\s");
                        
                        if(line_token.length == 2){
                            int A = Integer.parseInt(line_token[0]);
                            int B = Integer.parseInt(line_token[1]);
                            
                            if(A >= 1 && B <= 20 && A != B){
                                int shortest_path_count = calculateShortestPath(graph, A, B);
                                System.out.printf("%2d to %2d: %d%n", A, B, shortest_path_count);
                            }
                        }
                        
                        N--;
                    } catch (IOException ex) {
                       //
                    }
                }
            }
        } catch (NumberFormatException | IOException ex) {
            //
        }
    }
    
    void beginProcess(){
        try {
            reader = new BufferedReader(new InputStreamReader(System.in));
            
            int test_case = 1;
            
            while(true){
                    graph = new Graph();
                    
                    String line = reader.readLine();                    
                    if(line.isEmpty() || line == null){
                        break;
                    }
                    
                     parseLineIntoEdge(1, line);

                    //the Ith line, where I is less than 20
                    for(int i=2; i<=19; i++){
                        line = reader.readLine();
                        parseLineIntoEdge(i, line);
                    }// end lines
                    
                    processShortestPathResult(test_case);

                    System.out.print("\n");
                    
                    test_case++;
            }
        
        } catch (NumberFormatException | IOException ex) {
            
        }
    }
    
    int calculateShortestPath(Graph g, int source, int destination){
        Map<Integer, Integer> visited = new HashMap<>();
        //set source node visited
        visited.put(new Integer(source), new Integer(1));
        
        Map<Integer, Integer> level = new HashMap<>();
        //set source node level 0
        level.put(new Integer(source), new Integer(0));
        
        Queue<Integer> queue = new LinkedList<Integer>();
        //push source node to queue
        queue.add(source);
        
        while(!queue.isEmpty()){
            int top = queue.poll();
            int size = g.getEdges(top).size();
            
            for(int i=0; i<size; i++){
                if(!visited.containsKey(g.getEdges(top).get(i))){
                    visited.put(new Integer(g.getEdges(top).get(i)), new Integer(1));
                    level.put(new Integer(g.getEdges(top).get(i)), new Integer(level.get(top) + 1));
                    queue.add(g.getEdges(top).get(i));
                }
            }            
        }
        
        if(level.containsKey(destination)){
            return level.get(destination);
        }
        
        return 0;
    }
}