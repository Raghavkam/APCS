// name:J6-16-23      date:05-12-23
import java.util.*;
import java.io.*;
public class Huffman
{
   public static Scanner keyboard = new Scanner(System.in);
   public static void main(String[] args) throws IOException
   {
      //Prompt for two strings 
      System.out.print("Encoding using Huffman codes");
      System.out.print("\nWhat message? ");
      String message = keyboard.nextLine();
   
      System.out.print("\nEnter middle part of filename:  ");
      String middlePart = keyboard.next();
   
      huffmanize( message, middlePart );
   }
   public static void huffmanize(String message, String middlePart) throws IOException
   {
         //Make a frequency table of the letters
      	//Put each letter-frequency pair into a HuffmanTreeNode. Put each 
   		//        node into a priority queue (or a min-heap).     
      	//Use the priority queue of nodes to build the Huffman tree
      	//Process the string letter-by-letter and search the tree for the 
   		//       letter. It's recursive. As you recur, build the path through the tree, 
   		//       where going left is 0 and going right is 1.
         //System.out.println the binary message 
      	//Write the binary message to the hard drive using the file name ("message." + middlePart + ".txt")
         //System.out.println the scheme from the tree--needs a recursive helper method
      	//Write the scheme to the hard drive using the file name ("scheme." + middlePart + ".txt")
     Map<String, Integer> frq = new HashMap<String, Integer>();
     String k = "";    
     for(int i = 0; i < message.length(); i++){ 
         k = message.substring(i,i+1);
         if(frq.containsKey(k)){
            frq.put(k,frq.get(k)+1);
         }
         else{
            frq.put(k, 1);
         }
     
     }
      
     Queue<HuffmanTreeNode> q = new PriorityQueue<HuffmanTreeNode>();//put into priority queue 
     for(String i : frq.keySet()){
        q.add(new HuffmanTreeNode(i, frq.get(i)));
     }
      
     while(q.size() > 1){ //sort into tree 
        HuffmanTreeNode fir = q.remove();
        HuffmanTreeNode sec = q.remove();
        q.add(new HuffmanTreeNode("*", fir, sec, fir.getFreq() + sec.getFreq()));
     
     }
     
     Map<String, String> scheme = new HashMap<String, String>();
     maprecur(q.peek(), "", scheme);
     
     PrintWriter mess = (new PrintWriter("message." + middlePart+ ".txt"));
     PrintWriter key = (new PrintWriter("scheme." + middlePart+ ".txt"));
     for(String i : scheme.keySet()){
         key.println(i + scheme.get(i));
     }
     
     
     for(int i = 0; i < message.length(); i++){ 
         k = message.substring(i,i+1); 
         mess.print(scheme.get(k));
     }
         
     mess.close();
     key.close();
           
   }
   
   public static void maprecur(HuffmanTreeNode tree, String path, Map<String, String> map){
      if(tree == null){
         return;
      }
      if(tree.getLeft() == null && tree.getRight() == null){
           map.put(tree.getLetter(), path);
           path = "";
      }
      String path2 = path;
      maprecur(tree.getLeft(), path+= "0", map);
      maprecur(tree.getRight(), path2 += "1", map);
      
   }
}
	/*
	  * This tree node stores two values.  Look at TreeNode's API for some help.
	  * The compareTo method must ensure that the lowest frequency has the highest priority.
	  */
class HuffmanTreeNode implements Comparable<HuffmanTreeNode>
{
   private String letter; 
   private Integer freq;
   private HuffmanTreeNode left, right;
   
   public HuffmanTreeNode(String initValue, Integer initFreq)
   { 
      letter = initValue;
      freq = initFreq; 
      left = null; 
      right = null; 
   }
   
   public HuffmanTreeNode(String initValue, HuffmanTreeNode initLeft, HuffmanTreeNode initRight, Integer initFreq)
   { 
      letter = initValue; 
      freq = initFreq; 
      left = initLeft; 
      right = initRight; 
   }
   
   public String getLetter()
   { 
      return letter; 
   }
   
   public Integer getFreq()
   { 
      return freq; 
   }
   
   public HuffmanTreeNode getLeft() 
   { 
      return left; 
   }
   
   public HuffmanTreeNode getRight() 
   { 
      return right; 
   }
   
   public void setLetter(String theNewValue) 
   { 
      letter = theNewValue; 
   }
   
    public void setFreq(Integer theNewValue) 
   { 
      freq = theNewValue; 
   }

   
   public void setLeft(HuffmanTreeNode theNewLeft) 
   { 
      left = theNewLeft;
   }
   
   public void setRight(HuffmanTreeNode theNewRight)
   { 
      right = theNewRight;
   } 
   
   public int compareTo(HuffmanTreeNode other){
      if(other.getFreq().compareTo(freq) > 0 ){
         return -1;
      }
      else if(other.getFreq().compareTo(freq) < 0 ){
         return 1;
      }
      return 0;
   }
}
