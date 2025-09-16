// Name:J6-16-23
// Date:05-04-23

import java.util.*;
import java.text.DecimalFormat;

public class HeapSort
{
   public static int N;  //9 or 100
	
   public static void main(String[] args)
   {
      /* Phase 2 by itself: Given a heap, sort it. Do this part first. */
    /*  N = 9;  
      double heap[] = {-1,99,80,85,17,30,84,2,16,1};  // size of array = N+1
      
      display(heap);
      sort(heap);
      display(heap);
      System.out.println(isSorted(heap)); */
      
      /* Phases 1 and 2:  Generate 100 random numbers, make a heap, sort it.  */
      N = 4;
    //   double[] heap = new double[N + 1];  // size of array = N+1
       
       double[] heap = {0.0, 7.2, 3.4, 6.4, 9.9};  //a special case
      // heap = createRandom(heap);
       display(heap);
       makeHeap(heap, N);
       display(heap); 
       sort(heap);
       display(heap);
       System.out.println( isSorted(heap) ); 
   }
   
	//******* methods in Phase 2 by itself ******************************************
   public static void display(double[] array)
   {
      for(int k = 1; k < array.length; k++)
         System.out.print(array[k] + "    ");
      System.out.println("\n");	
   }
   
   public static void sort(double[] array)
   {
     /* enter your code here */
      
      int len = array.length - 1;
      while(len >= 3)
      {
         len--;
         swap(array, 1, len+1);
         heapDown(array, 1, len); 
            
      }
      
      if(array[1] > array[2])   //just an extra swap, if needed.
            swap(array, 1, 2);
         
      
   }
  
   public static void swap(double[] array, int a, int b)
   {
   
      double temp = array[b];
      array[b] = array[a];
      array[a] = temp;
   }
   
   //it's a max-heap. Parents are larger than each child.
   public static void heapDown(double[] array, int k, int lastIndex)
   {
      int c1 = 2*k;
      int c2 = 2*k+1;
      
      if(c1 <= lastIndex && c2 <= lastIndex){  
         if(array[c2] > array[c1]){ // switch c1 to larger one
            c1 = c2;
         }
         if(array[k] < array[c1]){
            swap(array, c1, k);
            heapDown(array, c1, lastIndex);
         }
      }
     
   
   }
   
   public static boolean isSorted(double[] arr)
   {
       for(int i = 0; i < arr.length-1; i ++){
         if(arr[i] > arr[i+1])
            return false;

       }
       return true;
   }
   
   //****** methods in Phase 1 *******************************************

  	//Generate 100 random numbers (between 1 and 100, formatted to 2 decimal places)
   //Post-condition:  array[0] == -1, the rest of the array is random
   public static double[] createRandom(double[] array)
   {  
      array[0] = -1;
      DecimalFormat df = new DecimalFormat("#0.00");
      double num = 0.0;   //because heaps don't use index 0
      for(int i = 1; i <= 100; i++){
         num = (Math.random() * 100) + 1;
         array[i] = Double.parseDouble(df.format(num)); 
      }
      return array;
   }
   
   //turn the random array into a heap
   //Post-condition:  array[0] == -1, the rest of the array is in heap-order
   public static void makeHeap(double[] array, int lastIndex)
   {
      for(int i = lastIndex/2 ; i >=1 ; i--){
            heapDown(array, i, lastIndex);
      }
   }
}

