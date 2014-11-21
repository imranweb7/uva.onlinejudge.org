/**
 * Problem ID    : 10653                                 
 * Problem Name  : Bombs! NO they are Mines!!                      
 * Solved Using  : BFS
 * Author        : Muhammed Imran Hussain
 * E-mail        : imranweb7@gmail.com 
 * Website       : http://www.ihussain.info
*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class TwoDimensionalGrid{
    private int Grid[][];
            
    public TwoDimensionalGrid(int row, int col){
        Grid = new int[row][col];
        
        for(int i=0; i<row; i++){
            for(int j=0; j<col; j++){
                Grid[i][j] = 0;
            }
        }
    }
    
    void setCellBlock(int row, int col){
        Grid[row][col] = -1;
    }
    
    void setCellValue(int row, int col, int val){
        Grid[row][col] = val;
    }
    
    int getCellValue(int row, int col){
        return Grid[row][col];
    }
}

class Pair<F, L>{
    private final F first;
    private final L last;
            
    public Pair(F f, L l){
        first = f;
        last = l;
    }
    
    F getFirst(){
        return first;
    }
    
    L getLast(){
        return last;
    }
}

class Main{
    BufferedReader reader;
    StringTokenizer line_token;
    
    int row, col, total_rows_contain_bomb;
    int row_no_contain_bomb, total_bomb_in_row, col_no_contain_bomb;  
    int start_row, start_col;
    int destination_row, destination_col;
    TwoDimensionalGrid grid;
    
    int fx[] = {1, -1, 0, 0};
    int fy[] = {0, 0, 1, -1};
    
    public static void main(String args[]){
        Main obj = new Main();
        obj.beginProcess();
    }
    
    void beginProcess(){
        try {
            reader = new BufferedReader(new InputStreamReader(System.in));
            
            do{
                //reading test case
                line_token = new StringTokenizer(reader.readLine());            
                row = Integer.parseInt(line_token.nextToken());
                col = Integer.parseInt(line_token.nextToken());
                
                if((row>0 && row<=1000) && (col>0 && col<=1000)){                    
                    grid = new TwoDimensionalGrid(row, col);
                    
                    //reading number of rows that contain bomb
                    total_rows_contain_bomb = Integer.parseInt(reader.readLine());
                    
                    if(total_rows_contain_bomb >= 0 && total_rows_contain_bomb <= row){
                        for(int i=0; i<total_rows_contain_bomb; i++){
                            //reading row no and bomb cell no
                            line_token = new StringTokenizer(reader.readLine());                              
                            row_no_contain_bomb = Integer.parseInt(line_token.nextToken());
                            total_bomb_in_row = Integer.parseInt(line_token.nextToken());
                            
                            for(int j=0; j<total_bomb_in_row; j++){
                                col_no_contain_bomb = Integer.parseInt(line_token.nextToken());                                
                                grid.setCellBlock(row_no_contain_bomb, col_no_contain_bomb);
                            }
                        }
                        
                        //reading start position
                        line_token = new StringTokenizer(reader.readLine()); 
                        start_row = Integer.parseInt(line_token.nextToken());
                        start_col = Integer.parseInt(line_token.nextToken());
                        
                        //reading destination position
                        line_token = new StringTokenizer(reader.readLine());    
                        destination_row = Integer.parseInt(line_token.nextToken());
                        destination_col = Integer.parseInt(line_token.nextToken());
                        
                        bfs();
                    }
                }
            
            }while(row > 0 && col > 0);
            
        } catch (IOException ex) {
            //
        }
    }
    
    void bfs(){
        int distance[][] = new int[row][col];       
        distance[start_row][start_col] = 0;
        grid.setCellValue(start_row, start_col, 1);
        
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair<Integer, Integer>(start_row, start_col));
        
        while(!queue.isEmpty()){
            Pair top = queue.poll();
            
            for(int i=0; i<4; i++){
                int tx = (int) top.getFirst() + fx[i];
                int ty = (int) top.getLast() + fy[i];
                
                if(tx>=0 && tx<row && ty>=0 && ty<col && grid.getCellValue(tx, ty)!=-1 && grid.getCellValue(tx, ty)==0){
                    distance[tx][ty] = distance[(int) top.getFirst()][(int) top.getLast()] + 1;
                    grid.setCellValue(tx, ty, 1);
                    queue.add(new Pair<Integer, Integer>(tx, ty));
                }else{
                    if(tx == destination_row && ty == destination_col){
                        break;
                    }
                }
            }    
        }
        
        System.out.println(distance[destination_row][destination_col]);
    }
}
