/**
 * Problem ID    : 459                                 
 * Problem Name  : Graph Connectivity                   
 * Solved Using  : Disjoint Set
 * Author        : Muhammed Imran Hussain
 * E-mail        : imranweb7@gmail.com 
 * Website       : http://www.ihussain.info
*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

class Main{
    private static Map<Character, Character> parent = null;
    private static char MAX_NODE; // hold largest node value
    private static int total_subgraph; // hold total subgraph
    
    public static void main(String args[]){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        try{
            int test_case = Integer.parseInt(reader.readLine());

            if(test_case > 0){
                String line = reader.readLine();//blank line

                while(test_case-- > 0){
                    MAX_NODE = reader.readLine().charAt(0);
                    total_subgraph = (MAX_NODE - 'A') + 1;

                    parent = new HashMap<Character, Character>();

                    if(MAX_NODE >= 'A' && MAX_NODE <= 'Z'){  

                        // initializing set
                        // intially all nodes are different set
                        // where each node parent is itself
                        for(char l='A'; l<=MAX_NODE; l++){
                            parent.put(new Character(l), new Character(l));
                        }

                        //read connection
                        try{
                            while(!(line = reader.readLine()).isEmpty()){
                                if(line.charAt(0) <= MAX_NODE && line.charAt(1) <= MAX_NODE){
                                    union(line.charAt(0), line.charAt(1));
                                }
                            }
                        }catch(NullPointerException npex){
                            //pointer exception                                    
                        }finally{
                            System.out.println(total_subgraph);
                            if(test_case != 0){
                                System.out.print("\n");
                            }
                        }
                    }

                    //clear the map
                    parent.clear();
                }
            }
        }catch(NumberFormatException | IOException ex){
            //input or number format exception
        }
    }// end main
    
    /*
     * make union between two nodes
    */
    static void union(char node1, char node2){
        char parent_node1 = find(node1);
        char parent_node2 = find(node2);
        
        if(parent_node1 != parent_node2){
            parent.put(new Character(parent_node1), new Character(parent_node2));
            
            //decrement set count
            total_subgraph--;
        }
    }
    
    /*
     * Find representative of a node
    */
    static char find(char node1){
        if((char) parent.get(node1) == node1){
            return node1;
        }else{
            return find((char) parent.get(node1));
        }
    }
}