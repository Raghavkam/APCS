 //Name:J6-16-23
 //Date:05-10-23
 
import java.util.*;

/* implement the API for java.util.PriorityQueue
 *     a min-heap of objects in an ArrayList<E> in a resource class
 * test this class with HeapPriorityQueue_Driver.java.
 * test this class with LunchRoom.java.
 * add(E) and remove()  must work in O(log n) time
 */
public class HeapPriorityQueue<E extends Comparable<E>> 
{
   private ArrayList<E> myHeap;
   
   public HeapPriorityQueue()
   {
      myHeap = new ArrayList<E>();
      myHeap.add(null);
   }
   
   public ArrayList<E> getHeap()   //for Codepost
   {
      return myHeap;
   }
   
   public int lastIndex()
   {
      return myHeap.size()-1;
   }
   
   public boolean isEmpty()
   {
     if(myHeap.size() <= 1){
         return true;
     }
     return false;
   }
   
   public boolean add(E obj)
   {
      myHeap.add(obj);
      heapUp(lastIndex());
      return true;
   }
   
   public E remove()
   {
      if(isEmpty()) {
         return null;
      }
      swap(1, lastIndex());
      E ret = myHeap.remove(lastIndex());
      heapDown(1,lastIndex());
      return(ret);
   }
   
   public E peek()
   {
      if(isEmpty()) {
         return null;
      }
      swap(1,lastIndex());
      E ret = myHeap.get(1);
      swap(1, lastIndex());
      return(ret);
   }
   
   //  it's a min-heap of objects in an ArrayList<E> in a resource class
   public void heapUp(int k)
   {
      int p = k/2;
      
      if(p != 0 && k != 0){
         if(myHeap.get(p).compareTo(myHeap.get(k)) > 0){ 
            swap(p, k);
            heapUp(p);
         }
      } 
   }
   
   private void swap(int a, int b)
   {
      E temp = myHeap.get(b);
      myHeap.set(b,myHeap.get(a));
      myHeap.set(a,temp);
     
   }
   
  //  it's a min-heap of objects in an ArrayList<E> in a resource class
   public void heapDown(int k, int lastIndex)
   {
      
      int c1 = 2*k;
      int c2 = 2*k+1;
      
      if(c1 <= lastIndex && c2 <= lastIndex){  
         if(myHeap.get(c2).compareTo(myHeap.get(c1)) < 0 ){ // switch c1 to smaller  one
            c1 = c2;
         }
         if(myHeap.get(c1).compareTo(myHeap.get(k))  < 0){
            swap(c1, k);
            heapDown(c1,lastIndex());
         }
      }
   }
   
   public String toString()
   {
      return myHeap.toString();
   }  
}
