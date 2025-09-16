// Name:J6-16-23              Date:05-11-23
import java.util.*;
import java.io.*;
public class deHuffman
{
   public static void main(String[] args) throws IOException
   {
      Scanner keyboard = new Scanner(System.in);
      System.out.print("\nWhat binary message (middle part)? ");
      String middlePart = keyboard.next();
      Scanner sc = new Scanner(new File("message."+middlePart+".txt")); 
      String binaryCode = sc.next();
      Scanner huffLines = new Scanner(new File("scheme."+middlePart+".txt")); 
      	
      TreeNode root = huffmanTree(huffLines);
      String message = dehuff(binaryCode, root);
      System.out.println(message);
      	
      sc.close();
      huffLines.close();
   }
   
   /*  
    To be consistent with the test in CodePost, 
    all the non-leaf nodes must store empty strings ("") 
    and each leaf node must store the letter as a String ("M").
    
    Do not store them as NULL and char!
    */  
   public static TreeNode huffmanTree(Scanner huffLines)
   {
    TreeNode tree = new TreeNode(null); 
    String letter = "";
    String nums = "";
    int curr = 0;
    TreeNode trav = tree;
      
    while(huffLines.hasNextLine()){
       trav.setValue(letter);
       trav = tree;
       letter = huffLines.nextLine();
       nums = letter.substring(1);
       letter = letter.substring(0,1);
         
       for(int i = 0; i < nums.length(); i++){
          curr = Integer.parseInt(nums.substring(i,i+1));
          if(curr == 1){
              if(trav.getRight() == null){
                 trav.setRight(new TreeNode("")); 
              } 
              trav = trav.getRight();
          }
          else if(curr == 0){
              if(trav.getLeft() == null){
                 trav.setLeft(new TreeNode("")); 
              } 
              trav = trav.getLeft();
          }
         
        }
         
     }
     trav.setValue(letter);
     trav = tree;
     
     return tree;
   }
   
   public static String dehuff(String text, TreeNode root)
   {
     TreeNode curr = root;
     String out = "";
     for(int i =0; i < text.length(); i++){      
          if(curr.getValue() != ""){
             out += curr.getValue();
             curr = root;
             i--;
          }
          else if(text.substring(i,i+1).equals("1")){
             curr = curr.getRight();
          }
          else{
             curr = curr.getLeft();

          }  
     } 
     if(curr.getValue() != ""){
          out += curr.getValue();
     }
     return out;
   }
}

 /* TreeNode class for the AP Exams */
class TreeNode
{
   private Object value; 
   private TreeNode left, right;
   
   public TreeNode(Object initValue)
   { 
      value = initValue; 
      left = null; 
      right = null; 
   }
   
   public TreeNode(Object initValue, TreeNode initLeft, TreeNode initRight)
   { 
      value = initValue; 
      left = initLeft; 
      right = initRight; 
   }
   
   public Object getValue()
   { 
      return value; 
   }
   
   public TreeNode getLeft() 
   { 
      return left; 
   }
   
   public TreeNode getRight() 
   { 
      return right; 
   }
   
   public void setValue(Object theNewValue) 
   { 
      value = theNewValue; 
   }
   
   public void setLeft(TreeNode theNewLeft) 
   { 
      left = theNewLeft;
   }
   
   public void setRight(TreeNode theNewRight)
   { 
      right = theNewRight;
   }
}
