// Name: J6-16-23
// Date:  02-26-23
/*  Represents a binary expression tree.
 *  The BXT builds itself from postorder expressions. It can
 *  evaluate and print itself.  Also prints inorder and postorder strings. 
 */
 
import java.util.*;

public class BXT
{
   public static final String operators = "+ - * / % ^ !";
   private TreeNode root;   
   
   public BXT()
   {
      root = null;
   }
   public TreeNode getRoot()   //for Codepost
   {
      return root;
   }
    
   public void buildTree(String str)
   {
     	String[] nodes = str.split(" ");
      Stack<TreeNode> nums = new Stack<TreeNode>();
      for(String i : nodes){
         TreeNode cur = new TreeNode(i, null, null);
         if(!isOperator(i)){
            nums.push(cur);
         }
         else{
            if(i.equals("!")){
               cur.setLeft(nums.pop());
            }
            else{
               cur.setRight(nums.pop());
               cur.setLeft(nums.pop());
               }
            nums.push(cur);
         }
      }
      root = nums.pop();
   }
   
   public double evaluateTree()
   {
      return evaluateNode(root);
   }
   
   private double evaluateNode(TreeNode t)  //recursive
   {
      if(t == null){
         return 0.0;
      }
      if(isOperator((String)t.getValue())){
         if(((String)t.getValue()).equals("!")){
            return computeTerm((String)t.getValue(),evaluateNode(t.getLeft()), 0.0 );
         }
         else{
            return computeTerm((String)t.getValue(),evaluateNode(t.getLeft()), evaluateNode(t.getRight()));
         }
      }

      return Double.parseDouble((String)(t.getValue()));
   }
   
   private double computeTerm(String s, double a, double b)
   {
      if(s.equals("+")){
         return b+a;
      }
      if(s.equals("-")){
         return a-b;
      }
      if(s.equals("*")){
         return b*a;
      }
      if(s.equals("/")){
         return a/b;
      }
      if(s.equals("%")){
         return a%b;
      }
      if(s.equals("^")){
         return Math.pow(a,b);
      }
      if(s.equals("!")){
         int num = 1;
         for(int i = 1; i <= a; i++){
            num *= i;
         }
         return num;
        }
      return 0.0;
   }
   
   private boolean isOperator(String s)
   {
      if(operators.contains(s)){
         return true;
      }
      return false;
   }
   
   public String display()
   {
      return display(root, 0);
   }
   
   private String display(TreeNode t, int level)
   {
      String toRet = "";
      if(t == null)
         return "";
      toRet += display(t.getRight(), level + 1); //recurse right
      for(int k = 0; k < level; k++)
         toRet += "\t";
      toRet += t.getValue() + "\n";
      toRet += display(t.getLeft(), level + 1); //recurse left
      return toRet;
   }
    
   public String inorderTraverse()
   {
      return inorderTraverse(root);
   }
   
   private  String inorderTraverse(TreeNode t)
   {
      String toReturn = "";
      if(t == null)
         return "";
      toReturn += inorderTraverse(t.getLeft()); 
      toReturn += t.getValue() + " ";              //process root
      toReturn += inorderTraverse(t.getRight());  //recurse right
      return toReturn;
   }
   
   public String preorderTraverse()
   {
      return preorderTraverse(root);
   }
   
   private String preorderTraverse(TreeNode root)
   {
      String toReturn = "";
      if(root == null)
         return "";
      toReturn += root.getValue() + " ";              //process root
      toReturn += preorderTraverse(root.getLeft());   //recurse left
      toReturn += preorderTraverse(root.getRight());  //recurse right
      return toReturn;
   }
   
  /* extension */
   public String inorderTraverseWithParentheses()
    {
       return inorderTraverseWithParentheses(root);
    }
    
    private int priority(String s){
      if(s.equals("+")){
         return 1;
      }
      if(s.equals("-")){
         return 1;
      }
      if(s.equals("*")){
         return 2;
      }
      if(s.equals("/")){
         return 2;
      }
      if(s.equals("%")){
         return 2;
      }
      if(s.equals("^")){
         return 3;
      }
      if(s.equals("!")){
         return 2;
      }
      return 5;
    }
    
    private String inorderTraverseWithParentheses(TreeNode t)
    {
      String toReturn = "";
      if(t == null)
         return "";
      String left = inorderTraverseWithParentheses(t.getLeft());
      String right = inorderTraverseWithParentheses(t.getRight());
      if((t.getLeft() != null)            // left
         && (priority((String)t.getLeft().getValue()) < priority((String)t.getValue()))){
         toReturn += "( "+left+ ") ";
  	   }
      else{
          toReturn += left;
         }
       if((t.getRight() != null) //root and right
         && (priority((String)t.getRight().getValue()) <= priority((String)t.getValue()))){
         toReturn += t.getValue()+" ( "+right+") ";
         return toReturn;
  	      }
        toReturn +=t.getValue()+" "+right;
      return toReturn;

    }
}