//Name:J6-16-23
//Date:05-19-23

/* Resource classes and interfaces
 * for use with Graph0 AdjMat_0_Driver,
 *              Graph1 WarshallDriver,
 *          and Graph2 FloydDriver
 */

import java.util.*;
import java.io.*;

interface AdjacencyMatrix
{
   public int[][] getGrid();
   public int[][] readGrid(String fileName);
   public boolean isNeighbor(int from, int to);
   public int countEdges();
   public List<Integer> getNeighbors(int source);
   public String showAllNeighbors();
   public String toString();  //returns the grid as a String
}

interface WithNames
{
   public void readNames(String fileName);
   public Map<String, Integer> getNamesAndNumbers();
   public String toStringNamesAndNumbers();  // each line contains number-name, ex: 0-Pendleton
   public boolean isNeighbor(String from, String to);
}
  
interface Warshall
{    
   public int countReachables();
   public boolean isReachable(String from, String to);  
   public List<String> getReachables(String from);
   public String toStringReachability(); //displays the reachability matrix with 2 spaces in front of each value
   public void allPairsReachability();   // Warshall's Algorithm. fills the reachability matrix                                  
}

interface Floyd
{
   public int getCost(int from, int to);
   public int getCost(String from, String to);
   public void allPairsWeighted();  //Floyd's Algorithm
}

/***********************  the graph  ******************/
public class AdjMat implements AdjacencyMatrix//, WithNames//, Warshall//, Floyd
{
   private int[][] grid = null;   //adjacency matrix representation
   private Map<String, Integer> namesAndNumbers = null;    // maps name to number
   private  ArrayList<String> nameList = null;  //reverses the map, index-->name
   private int[][] reachability = null; //reachability matrix for Warshall, cost matrix for Floyd
 
 /*  write constructors, accessor methods, and instance methods   */  
   public AdjMat(String filename){  //1 arg constructor 
      grid = readGrid(filename);
      namesAndNumbers = new TreeMap<String, Integer>();
      nameList = new ArrayList<String>();
   }
   
   public AdjMat(String  filename, String names){ //2 arg constructor
      grid = readGrid(filename);
      reachability = readGrid(filename);
      namesAndNumbers = new TreeMap<String, Integer>();
      nameList = new ArrayList<String>();
      readNames(names);

   }

   public int[][] getGrid() //accsessors
   {
      return grid;
   
   }
   
   
   public Map<String, Integer> getNamesAndNumbers()
   {
      return namesAndNumbers;
   
   }

   
   public int[][] readGrid(String name) //read methods
   {
      Scanner in = null;
      int[][] newg = null;
      try{
         in = new Scanner(new File(name));
         int len = Integer.parseInt(in.next());
         newg = new int[len][len];
         for(int i = 0; i < len; i++){
            for(int k = 0; k < len; k++){
                 newg[i][k] = Integer.parseInt(in.next());
            }
         } 
      }
      catch(FileNotFoundException e){
         e.printStackTrace(System.out);         
      }      
      return newg;
   }
   
   
   public void readNames(String fileName) {
      Scanner in = null;
      String ln = "";
     
      int n = 0; 
      try{
         in = new Scanner(new File(fileName));
         in.nextLine();
         while(in.hasNextLine()){
            ln = in.nextLine(); 
            if(!ln.equals("")){
               namesAndNumbers.put(ln, n);
               n++;
               nameList.add(ln);
            }
         }       
      }
      catch(FileNotFoundException e){
         e.printStackTrace(System.out);   
         
      }      
   }
   
   public int countEdges()
   {
      int n = 0;
      for(int i =  0; i < grid.length; i++){
         for(int k =0; k < grid[0].length; k++){
            if(grid[i][k] != 0){
               n++;
            }
         }
      }
      return n;
   
   }

   public boolean isNeighbor(int from, int to) // neighbor methods
   {
      if(grid[from][to] != 0){
         return true;
      }
      return false;
   }
   
   public boolean isNeighbor(String from, String to) 
   {
      if(grid[namesAndNumbers.get(from)][namesAndNumbers.get(to)] ==1)
      {
         return true; 
      }
      return false;
   }
 
     
   public List<Integer> getNeighbors(int source)
   {
      List<Integer> neigh = new ArrayList<Integer>();
      for(int i = 0; i < grid.length; i++){
         if(grid[source][i] != 0){
            neigh.add(i);
         }
      }
      return neigh;
   }
   
   public String showAllNeighbors()
   {
      String out = "";
      for(int i = 0; i < grid.length;i++){
         out += i + ": " + getNeighbors(i);
         out += "\n";
      } 
      return out; 
   
   }
   
   
   public String toString() //toStrings
   {
      String out = "";
      for(int i = 0; i < grid.length;i++){
         out += "  ";
         for(int k = 0; k < grid[0].length; k++){
            out += grid[i][k]+ " ";
         }
         out+= "\n";
      } 
      return out; 
   }
   
   public String toStringNamesAndNumbers()// each line contains number-name, ex: 0-Pendleton
   { 
      String out = "";
      for(String i : namesAndNumbers.keySet())
      {  
         out += namesAndNumbers.get(i) +"-" + i + "\n";
      }
      return out;
      
   }    
      
      
   /************  implement the Warshall interface ************/
   public int countReachables()
   {
      int n = 0;
      for(int i =  0; i < reachability.length; i++){
         for(int k =0; k < reachability[0].length; k++){
            if(reachability[i][k] != 0){
               n++;
            }
         }
      }
      return n;
   }
   public boolean isReachable(String from, String to)
   {
      if(reachability[namesAndNumbers.get(from)][namesAndNumbers.get(to)] ==1)
      {
         return true; 
      }
      return false;
   }
   public List<String> getReachables(String from){
      int source = namesAndNumbers.get(from);
      List<String> neigh = new ArrayList<String>();
      for(int i = 0; i < reachability.length; i++){
         if(reachability[source][i] != 0){
            neigh.add(nameList.get(i));
         }
      }
      return neigh;

   }
   public String toStringReachability() //displays the reachability matrix with 2 spaces in front of each value
   {   
      String out = "";
      for(int i = 0; i < reachability.length;i++){
         out += "  ";
         for(int k = 0; k < reachability[0].length; k++){
            out += reachability[i][k]+ " ";
         }
         out+= "\n";
      } 
      return out; 
   }
   
   
   public void allPairsReachability()   // Warshall's Algorithm. fills the reachability matrix      
   {
      for(int v = 0; v < reachability.length; v++){
         for(int r = 0; r < reachability.length; r++){
            for(int c = 0; c < reachability.length; c++){
               //if(reachability[r][c] = 0   ){
                  if((reachability[r][v] == 1) && ((reachability[v][c] == 1))){
                     reachability[r][c] = 1;
                  }
              // }
            }
         }
      }
   } 
  
        
   /*************  implement the Floyd interface  *********/
   public int getCost(int from, int to)
   {
      return reachability[from][to];
   
   }
   public int getCost(String from, String to)
   {
      return reachability[namesAndNumbers.get(from)][namesAndNumbers.get(to)];
   }
   
   public void allPairsWeighted(){
      for(int v = 0; v < reachability.length; v++){
         for(int r = 0; r < reachability.length; r++){
            for(int c = 0; c < reachability.length; c++){
               if( r == c ){
                  reachability[r][c] = 0;
               }
               if((reachability[r][v] < 9999) && (reachability[v][c] < 9999) &&
                     (reachability[r][c] > reachability[r][v]+reachability[v][c])) {
                  reachability[r][c] = reachability[r][v]+reachability[v][c];
               }
              
            }
         }
      }
   }
  
  
}