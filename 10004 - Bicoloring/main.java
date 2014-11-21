/**
 * Problem ID    : 10004                                 
 * Problem Name  : Bicoloring                      
 * Solved Using  : BFS
 * Author        : Muhammed Imran Hussain
 * E-mail        : imranweb7@gmail.com 
 * Website       : http://www.ihussain.info
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class GraphAdjacencyList{
    Map<Integer, List<Integer>> Adjacency_list;
    int[] Node_Color;
    
    //initialize graph adjacency list
    GraphAdjacencyList(int Number_of_Nodes){
        Adjacency_list = new HashMap<>();
        Node_Color = new int[Number_of_Nodes];
        
        for(int i=0; i< Number_of_Nodes; i++){
            Adjacency_list.put(new Integer(i), new LinkedList<Integer>());
            Node_Color[i] = -1;
        }
    }
    
    //set edges
    void setEdge(int Source, int Destination){
        if(Source > Adjacency_list.size() || Destination > Adjacency_list.size()){
            // nodes not found
        }else{
            List<Integer> Source_List = Adjacency_list.get(Source);
            Source_List.add(Destination);
            
            List<Integer> Destination_List = Adjacency_list.get(Destination);
            Destination_List.add(Source);
        }
    }
    
    //get edges
    List<Integer> getEdges(int Source){
        if(Source > Adjacency_list.size()){
            // nodes not found
            return null;
        }else{
            return Adjacency_list.get(Source);
        }
    }
    
    int[] getNodeColors(){
        return Node_Color;
    }
}

class Main{
    public static void main(String args[]){
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            
            int Number_of_Nodes, Number_of_Edges, Source, Destination;
            StringTokenizer connection;
            
            Number_of_Nodes = Integer.parseInt(reader.readLine());
            
            int count = 0;
            int item;
                        
            while(Number_of_Nodes > 0){
                if(Number_of_Nodes > 1 && Number_of_Nodes < 200){
                    Number_of_Edges = Integer.parseInt(reader.readLine());              
                
                    GraphAdjacencyList graph = new GraphAdjacencyList(Number_of_Nodes);
                    int[] color = graph.getNodeColors();

                    for(int i=0; i<Number_of_Edges; i++){                        
                        connection = new StringTokenizer (reader.readLine());
                        
                        Source = Integer.parseInt(connection.nextToken());
                        Destination = Integer.parseInt(connection.nextToken());

                        if(Source >= 0 && Source < Number_of_Nodes && Destination >= 0 && Destination < Number_of_Nodes){
                            graph.setEdge(Source, Destination);
                            count++;
                        }
                    }
                    
                    if(count > 0){                    
                        boolean isBiColored = true;
                        
                        color[0] = 0;
                        
                        Queue<Integer> queue = new LinkedList<Integer>();   
                        queue.add(0);

                        while(!queue.isEmpty() && isBiColored){                   
                            item = queue.poll();
                            
                            int size = graph.getEdges(item).size();
                            
                            for(int i=0; i<size && isBiColored; i++){
                                if(color[graph.getEdges(item).get(i)] == -1){
                                    if(color[item] == 0){
                                        color[graph.getEdges(item).get(i)] = 1;
                                    }else{
                                        color[graph.getEdges(item).get(i)] = 0;
                                    }
                                    
                                    queue.add(graph.getEdges(item).get(i));
                                }else{
                                    if(color[graph.getEdges(item).get(i)] == color[item]){
                                        isBiColored = false;
                                    }
                                }
                            }
                        }

                        if(isBiColored){
                            System.out.println("BICOLORABLE.");
                        }else{
                            System.out.println("NOT BICOLORABLE.");
                        }
                    }
                }
                
                Number_of_Nodes = Integer.parseInt(reader.readLine());
            }
        } catch (IOException ex) {
            //
        }        
    }
}
