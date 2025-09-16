// Name:J6-16-23
// Date: 03-08-23

interface BST_Generic_interface<E>
{
   public int size();
   public TreeNode<E> getRoot() ;
   public boolean contains(E obj);
   public void add(E obj);         //does not balance
   public void addBalanced(E obj); //AVL
   public void remove(E obj);      //does not balance
   public E min();
   public E max();
   public String display();
   public String toString();
}

/*******************
Copy your BST code.  Implement generics.
If you skipped remove() and/or addBalanced(), just leave the method bodies empty.
**********************/
public class BST_Generic<E extends Comparable<E>> implements BST_Generic_interface<E>
{
   private TreeNode<E> root;
   private int size;
   public BST_Generic()
   {
      root = null;
      size = 0;
   }
   public int size()
   {
      return size;
   }
   public TreeNode<E> getRoot()   //accessor method
   {
      return root;
   }
   public void add(E s)
   {
      size++;
      root = add(root,s);
   }
   private TreeNode<E> add(TreeNode<E> t, E s) //recursive helper method
   {     
      if(t == null){
         return new TreeNode(s, null, null);
      }
      if((t.getValue()).compareTo(s) < 0){
         t.setRight(add(t.getRight(), s));
       }
      else{
          t.setLeft(add(t.getLeft(), s));
      }
      return t;
   }
   public String display()
   {
      return (display( root, 0) );
   }
   private String display(TreeNode<E> t, int level) //recursive helper method
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
  
   public boolean contains( E obj)
   {
      return contains(root, obj);
   }
   private boolean contains(TreeNode<E> t, E x) //recursive helper method
   {
      if(t == null){
         return false;
      }
      else if(t.getValue().equals(x)){
         return true;
      }
      else if((t.getValue()).compareTo(x) > 0){
         return contains(t.getLeft(), x);
      }
      else{
         return contains(t.getRight(), x );
      }

   }
  
   public E min()
   {
      return min(root);
   }
   private E min(TreeNode<E> t)  //use iteration
   {
      if(t == null){
         return null;
      }
      while(t.getLeft() != null){
            t= t.getLeft();
      }
      return t.getValue();
   }
  
   public E max()
   {
      return max(root);
   }
   private E max(TreeNode<E> t)  //recursive helper method
   {
      if(t == null){
         return null;
      }
      if(t.getRight() == null){
         return t.getValue();
      }
      return max(t.getRight());
   }
  
   public String toString()
   {
      return toString(root);
   }
   private String toString(TreeNode<E> t)  //an in-order traversal.  Use recursion.
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
      int inorderend = inorder.indexOf(root)+1;
      preOrder += preorderExtension(inorder.substring(0,inorder.indexOf(root)), postorder.substring(0,inorder.indexOf(root))); //left
      preOrder += preorderExtension(inorder.substring(inorderend), postorder.substring(postorder.length()-(inorder.length()-inorderend))); //right
      return preOrder;
   }
   public void remove(E target)
   {
      root = remove(root, target);
      size--;
   }

   private TreeNode<E> remove(TreeNode<E> current, E target)
   {
      if(!current.getValue().equals(target)){
         if(current == null){
            return null;
         }
         else if(current.getValue().equals(target)){
             return current;
          }  
         else if((current.getValue()).compareTo(target) > 0){
            current.setLeft(remove(current.getLeft(), target));
          }
         else{
            current.setRight(remove(current.getRight(), target));
          }
      }
      else{
         if(current.getLeft() == null && current.getRight() == null){
            return null;
          }
         else if(current.getLeft() == null && current.getRight() != null){
            return current.getRight();
         }
         else if(current.getRight() == null && current.getLeft() != null ){
           return current.getLeft();
         }
         else{
             current.setValue(max(current.getLeft()));
             current.setLeft(remove(current.getLeft(), max(current.getLeft())));

         }
     }
     return current; 
   }
     /*  start the addBalanced methods */
   private int calcBalance(TreeNode<E> current) //height to right minus 
   {                                    //height to left
      if(current == null){
        return 0;
      }               //height to left
      return height(current.getRight())-height(current.getLeft());
   }

   private int height(TreeNode<E> t)   //from TreeLab
   {
      if(t == null)
         return -1;
      int i = 1;
      int l = height(t.getLeft());
      int r =  height(t.getRight());
      if(l > r)
         i += l;
      else if(r > l)
         i += r;
      else
         i+=r;
      return i;
   }

   public void addBalanced(E value)  
   {
      add(value);
      root = balanceTree(root, value);   // for an AVL tree.  You may change this line.
   }
   
   private TreeNode<E> balanceTree(TreeNode<E> t, E s)  //recursive.  Whatever makes sense.
   {
      if(t == null) {
         return null;
        }
      if((t.getValue()).compareTo(s) > 0){// recuring down
          t.setLeft(balanceTree(t.getLeft(), s)); 
          }
      else
        t.setRight(balanceTree(t.getRight(), s));
      int balance = calcBalance(t);
      if(Math.abs(balance) >= 2){ //unwinding and checking balance
         if(calcBalance(t.getRight()) < 0 && balance >= 2  ){       
               return RL(t);
               }
         else if( balance <= -2 && calcBalance(t.getLeft()) > 0 ){
               return LR(t);
         }
         else{
            if(balance < 0){
                 return RR(t);
               }
            else{
               return LL(t);
             }
           }
       }      
     return t;
   }
   
   private TreeNode<E> RR(TreeNode<E> t)
   {
      TreeNode<E> left = t.getLeft();
      TreeNode<E> right = t.getLeft().getRight();
     
      t.getLeft().setRight(t);
      t.setLeft(right);
      
      return left;
   }
   private TreeNode<E> LL(TreeNode<E> t)
   {
       TreeNode<E> left = t.getRight().getLeft();
      TreeNode<E> right = t.getRight();
     
      t.getRight().setLeft(t);
      t.setRight(left);
      
      return right;
   }
  
   private TreeNode<E> RL(TreeNode<E> t)
   {
      TreeNode<E> left = t.getLeft();
      TreeNode<E> right = t.getRight();

      t.setRight(RR(t.getRight()));
      return LL(t);
   }

   private TreeNode<E> LR(TreeNode<E> t)
   {
      TreeNode<E> left = t.getLeft();
      TreeNode<E> right = t.getRight();
     
      t.setLeft(LL(t.getLeft()));
      return RR(t);
   }
 }



/*******************
  Copy your TreeNode<E> code.  Implement generics.
**********************/
class TreeNode<E>
{
      private E value; 
      private TreeNode<E> left, right;
   
       public TreeNode(E initValue)
      { 
         value = initValue; 
         left = null; 
         right = null; 
      }
   
       public TreeNode(E initValue, TreeNode<E> initLeft, TreeNode<E> initRight)
      { 
         value = initValue; 
         left = initLeft; 
         right = initRight; 
      }
   
       public E getValue()
      { 
         return value; 
      }
   
       public TreeNode<E> getLeft() 
      { 
         return left; 
      }
   
       public TreeNode<E> getRight() 
      { 
         return right; 
      }
   
       public void setValue(E theNewValue) 
      { 
         value = theNewValue; 
      }
   
       public void setLeft(TreeNode<E> theNewLeft) 
      { 
         left = theNewLeft;
      }
   
       public void setRight(TreeNode<E> theNewRight)
      { 
         right = theNewRight;
      }
   }

