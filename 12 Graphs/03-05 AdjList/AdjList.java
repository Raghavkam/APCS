// Name:J6-16-23  
// Date:05-31-23
 
import java.util.*;
import java.io.*;

/* Resource classes and interfaces
 * for use with Graphs3: EdgeList,
 *              Graphs4: DFS-BFS
 *          and Graphs5: EdgeListCities
 */

/**************** Graphs 3: EdgeList *****/
interface VertexInterface
{
   public String getName();
   public HashSet<Vertex> getAdjacencies();
   
   /*
     postcondition: if the set already contains a vertex with the same name, the vertex v is not added
                    because adjacencies is a HashSet, this method should operate in O(1)
   */
   public void addAdjacent(Vertex v);
   /*
     postcondition:  returns as a string one vertex with its adjacencies, without commas.
                     for example, D [C A]
     */
   public String toString(); 
 
} 
 
/*************************************************************/
class Vertex implements VertexInterface, Comparable<Vertex> //2 vertexes are equal if and only if they have the same name
{
   private final String name;
   private HashSet<Vertex> adjacencies;
   
   public Vertex(String nname){
      name = nname;
      adjacencies = new HashSet<Vertex>();
   }
   public String getName(){
      return name;
   }
   public HashSet<Vertex> getAdjacencies(){
      return adjacencies;
   }
   /*
     postcondition: if the set already contains a vertex with the same name, the vertex v is not added
                    because adjacencies is a HashSet, this method should operate in O(1)
   */
   public void addAdjacent(Vertex v){
      adjacencies.add(v);
   }
   /*
     postcondition:  returns as a string one vertex with its adjacencies, without commas.
                     for example, D [C A]
     */
   public String toString(){
      String out = name + " [";
      for(Vertex i: adjacencies){
         out += i.getName() + " ";
      }
      out = out.trim();
      out += "]";
      return out;
   }
   
   public int compareTo(Vertex v){
      if(name.compareTo(v.getName()) > 0){
         return -1;
      }
      else if(name.compareTo(v.getName()) < 0){
         return 1;
      }
      return 0;
   }
   
   public boolean equals(Vertex v){
      if(name.equals(v.getName())){
         return true; 
      }
      return false;
   }
   
   public int hashCode(){
      return name.hashCode();
   
   }
}   

/*************************************************************/
interface AdjListInterface 
{
   public Set<Vertex> getVertices();
   public Vertex getVertex(String vName);
   public Map<String, Vertex> getVertexMap();  //this is just for codepost testing
   
   /*      
      postcondition: if a Vertex with the name v exists, then the map is unchanged.
                     addVertex should work in O(log n)
   */
   public void addVertex(String vName);
   
   /*
      precondition:  both Vertexes, source and target, are already stored in the graph.
      postcondition:  addEdge should work in O(log n)
   */
   public void addEdge(String source, String target); 
   
   /*
       returns the whole graph as one string, e.g.:
       A [C]
       B [A]
       C [C D]
       D [C A]
     */
   public String toString(); 

}

  
/********************** Graphs 4: DFS and BFS *****/
interface DFS_BFS
{
   public String depthFirstSearch(String name);
   public String breadthFirstSearch(String name);
   /*   extra credit  */
   public String depthFirstRecur(String name);
   public List<Vertex> depthFirstRecurHelper(Vertex v, List<Vertex> reachable);
}

/****************** Graphs 5: Edgelist with Cities *****/
interface EdgeListWithCities
{
   public void readData(String cities, String edges) throws FileNotFoundException;
   public int edgeCount();
   public int vertexCount();
   public boolean isReachable(String source, String target);
   public boolean isStronglyConnected(); //return true if every vertex is reachable from every 
                                          //other vertex, otherwise false 
}


/*************  start the Adjacency-List graph  *********/
public class AdjList implements AdjListInterface, DFS_BFS, EdgeListWithCities
{
   //we want our map to be ordered alphabetically by vertex name
   private Map<String, Vertex> vertexMap = new TreeMap<String, Vertex>();
      
   /* constructor is not needed because of the instantiation above */
   public Set<Vertex> getVertices(){
      Set<Vertex> verts = new HashSet<Vertex>();
      for(String i : vertexMap.keySet()){
         verts.add(vertexMap.get(i));
      }
      return verts;
   
   }
   public Vertex getVertex(String vName){
      return vertexMap.get(vName);
   
   }
   public Map<String, Vertex> getVertexMap(){//this is just for codepost testing
      return vertexMap;
   }  
   
   /*      
      postcondition: if a Vertex with the name v exists, then the map is unchanged.
                     addVertex should work in O(log n)
   */
   public void addVertex(String vName){
      if(!vertexMap.containsKey(vName)){
        vertexMap.put(vName, new Vertex(vName));
      }
   }
   
   /*
      precondition:  both Vertexes, source and target, are already stored in the graph.
      postcondition:  addEdge should work in O(log n)
   */
   public void addEdge(String source, String target){
    //if(vertexMap.containsKey(source) && vertexMap.containsKey(target)){
      vertexMap.get(source).addAdjacent( vertexMap.get(target));        
    //}

   }
   
   /*
       returns the whole graph as one string, e.g.:
       A [C]
       B [A]
       C [C D]
       D [C A]
     */
   public String toString(){
      String out="";
      for(String i : vertexMap.keySet()){
         out += vertexMap.get(i) + "\n"; 
      } 
      return out;
   } 
   
   public String depthFirstSearch(String name){
      HashSet<Vertex> verts= (vertexMap.get(name)).getAdjacencies();
      List<Vertex> list = new ArrayList<Vertex>();
      Stack<Vertex> stack = new Stack<Vertex>();
      int n = 0;
      
      stack.push(vertexMap.get(name));

      while(!stack.isEmpty()){
         Vertex t = stack.pop();
         HashSet<Vertex> edges = t.getAdjacencies();
         if(contains(list, t) == true){
            list.add(t);
            n++;
            for(Vertex i : edges){
               stack.push(i);
            
            }
         }
      
      }
      String out =""; 
      for(Vertex i : list){
         out += i.getName() + " ";
      
      }
      return out;
   
   }
   
   
   public boolean contains(List<Vertex> list, Vertex t){
      for(Vertex i: list){
         if(i.equals(t)){
            return false;
         }
      }
      return true;
   }
   
   public String breadthFirstSearch(String name){
      HashSet<Vertex> verts= (vertexMap.get(name)).getAdjacencies();
      List<Vertex> list = new ArrayList<Vertex>();
      Queue<Vertex> queue = new LinkedList<Vertex>();
      int n = 0;
      
      queue.add(vertexMap.get(name));

      while(!queue.isEmpty()){
         Vertex t = queue.remove();
         HashSet<Vertex> edges = t.getAdjacencies();
         if(contains(list, t) == true){
            list.add(t);
            n++;
            for(Vertex i : edges){
               queue.add(i);
            }
         }
      }
      String out =""; 
      for(Vertex i : list){
         out += i.getName() + " ";
      
      }
      return out;
   

   }
   
   
   public String depthFirstRecur(String name){
      HashSet<Vertex> verts= (vertexMap.get(name)).getAdjacencies();
      List<Vertex> list = new ArrayList<Vertex>();
      Stack<Vertex> stack = new Stack<Vertex>();
      int n = 0;
      
      list.add(vertexMap.get(name));
      list = depthFirstRecurHelper(vertexMap.get(name), list);
      
      String out =""; 
      for(Vertex i : list){
         out += i.getName() + " ";
      }
      return out;
   }
   
   
   public List<Vertex> depthFirstRecurHelper(Vertex v, List<Vertex> reachable){
      HashSet<Vertex> verts = v.getAdjacencies();
      ArrayList<Vertex> temp = new ArrayList<Vertex>();
      for(Vertex i: v.getAdjacencies()){
         temp.add(i);
      }
      for(int i = verts.size()-1; i >= 0; i--){
         Vertex t = temp.get(i);
         if(contains(reachable, t)){
            reachable.add(t);
            depthFirstRecurHelper(t, reachable);
     
         }
      }
      return reachable;
   }

   public void readData(String cities, String edges) throws FileNotFoundException{
       Scanner in = new Scanner(new File(cities));
       while(in.hasNextLine()){
         String name = in.nextLine();
         vertexMap.put(name, new Vertex(name));
       }
       Scanner in2 = new Scanner(new File(edges));
       while(in2.hasNextLine()){
         String edge = in2.nextLine();
         String[] line = edge.split(" ");
         addEdge(line[0], line[1]);
       
       }

   
   }
   public int edgeCount(){
      int count = 0; 
      for(String i: vertexMap.keySet()){
         count += vertexMap.get(i).getAdjacencies().size();
      
      }
      return count;
   }
   public int vertexCount(){
      return vertexMap.size();
   }
   public boolean isReachable(String source, String target){
      if(depthFirstSearch(source).indexOf(target) != -1){
         return true;
      }
      return false;
   }
   public boolean isStronglyConnected(){
      for(String i : vertexMap.keySet()){
         String[] edges = depthFirstSearch(i).split(" ");
         if(edges.length != vertexMap.size()){
            return false;
         }
      
      }
      return true;
   
   }
 
}