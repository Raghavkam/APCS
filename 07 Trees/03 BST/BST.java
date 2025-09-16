//Name: J6-16-23
//Date: 02-22-23

interface BSTinterface
{
   public int size();
   public TreeNode getRoot();
   public boolean contains(String obj);
   public void add(String obj);           //does not balance
   //public void addBalanced(String obj);  //AVL
   //public void remove(String obj);       //BST with remove
   //public void removeBalanced(String obj); //extra lab 
   public String min();
   public String max();
   public String display();
   public String toString();
}

/*******************
Represents a binary search tree holding Strings. 
Implements (most of) BSTinterface, above. 
The recursive methods all have a public method calling a private helper method. 
Copy the display() method from TreeLab. 
**********************/
class BST implements BSTinterface
{
   private TreeNode root;
   private int size;
   public BST()
   {
      root = null;
      size = 0;
   }
   public int size()
   {
      return size;
   }
   public TreeNode getRoot()   //accessor method
   {
      return root;
   }
   /***************************************
   @param s -- one string to be inserted
   ****************************************/
   public void add(String s) 
   {
      size++;
      root = add(root,s);
   }
   private TreeNode add(TreeNode t, String s) //recursive helper method
   {      
      if(t == null){
         return new TreeNode(s, null, null);
      }
      if(((String) t.getValue()).compareTo(s) < 0){
         t.setRight(add(t.getRight(), s));
       }
      else{
          t.setLeft(add(t.getLeft(), s));
      }
      return t;
   }
     /*************************
      Copy the display() method from TreeLab. 
      **********************/
   public String display()
   {
      return (display( root, 0) );
   }
   private String display(TreeNode t, int level) //recursive helper method
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
   
   public boolean contains( String obj)
   {
      return contains(root, obj);
   }
   private boolean contains(TreeNode t, String x) //recursive helper method
   {
      if(t == null){
         return false;
      }
      else if(t.getValue().equals(x)){
         return true;
      }
      else if(((String)t.getValue()).compareTo(x) > 0){
         return contains(t.getLeft(), x);
      }
      else{
         return contains(t.getRight(), x );
      }

   }
   
   public String min()
   {
      return min(root);
   }
   private String min(TreeNode t)  //use iteration
   {
      if(t == null){
         return null;
      }
      while(t.getLeft() != null){
            t= t.getLeft();
      }
      return (String)t.getValue();
   }
   
   public String max()
   {
      return max(root);
   }
   private String max(TreeNode t)  //recursive helper method
   {
      if(t == null){
         return null;
      }
      if(t.getRight() == null){
         return (String)t.getValue();
      }
      return max(t.getRight());
   }
   
   public String toString()
   {
      return toString(root);
   }
   private String toString(TreeNode t)  //an in-order traversal.  Use recursion.
   {
      String toReturn = "";
      if(t == null)
         return "";
      toReturn += toString(t.getLeft()); 
      toReturn += t.getValue() + " ";              //process root
      toReturn += toString(t.getRight());  //recurse right
      return toReturn;     
   }
   
   public String preorderExtension(String inorder, String postorder)
   {
      String preOrder = "";
      if(inorder.length() == 0 || postorder.length() == 0){
         return preOrder;
      }  
      
      String root = postorder.substring(postorder.length()-1);
      postorder =  postorder.substring(0, postorder.length()-1); 
      preOrder += root;
      
             //root
      int inorderend = inorder.indexOf(root)+1;
      preOrder += preorderExtension(inorder.substring(0,inorder.indexOf(root)), postorder.substring(0,inorder.indexOf(root))); //left
      preOrder += preorderExtension(inorder.substring(inorderend), postorder.substring(postorder.length()-(inorder.length()-inorderend))); //right
      
        
      return preOrder;
   }
   
   
   
   public String preorderprint()
   {
      return preorderprint(root);
   }

   public String preorderprint(TreeNode t)
   {
      String toReturn = "";
      if(t == null)
         return "";
      toReturn += t.getValue() + " ";              //process root
      toReturn += preorderprint(t.getLeft());   //recurse left
      toReturn += preorderprint(t.getRight());  //recurse right
      return toReturn;  
   }
   
   public String postorderprint()
   {
      return postorderprint(root);
   }
   public String postorderprint(TreeNode t)
   {
      String toReturn = "";
      if(t == null)
         return "";
      toReturn += postorderprint(t.getLeft());   //recurse left
      toReturn += postorderprint(t.getRight());
      toReturn += t.getValue() + " ";              //process root
       //recurse right
      return toReturn;  
   }
}
