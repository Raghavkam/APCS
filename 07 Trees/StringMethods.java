import java.util.Scanner;
import java.util.Arrays;

public class StringMethods {

   public static void main(String[] args){
      Scanner in = new Scanner(System.in);
      System.out.print("Please type a string: ");
      String s = in.nextLine();
      System.out.println(s.charAt(2));
      System.out.println(s.length());
      System.out.println(s.charAt(0));
      System.out.println(s.charAt(s.length()-1));
      System.out.println(s.charAt(s.length()-2));
      System.out.println(s.substring(3,8));
      System.out.println(s.substring(s.length()-5));
      System.out.println(s.substring(3));
      System.out.println(s.toLowerCase());
      System.out.println(s.toUpperCase());
      System.out.println(Arrays.toString(s.split("")));
      System.out.println(s.substring(0,s.length()-1));
      System.out.println(s.substring(1));
     
      int count = 0;
      for(int i =0; i <s.length(); i ++){
         if(s.charAt(i) == 'e'){
            count++;
         }
      }
      System.out.println(count);
      count = 0;
      for(int i =0; i <s.length(); i ++){
         if(s.charAt(i) == 'e' || s.charAt(i) == 'E'){
            count++;
         }
      }
      System.out.println(count);
      char[] vows = {'a','e','i','o','u'};
      String vowstring = "aeiou";
      count = 0;
      for(int i = 0;i<s.length(); i ++){
         if(vowstring.contains(s.substring(i,i+1).toLowerCase())){
            count ++;
         }
      }
      System.out.println(count);
      String fin = "";
      for(int i = 0;i<s.length(); i ++){
         if(vowstring.contains(s.substring(i,i+1).toLowerCase())){
            fin+= s.charAt(i);
         }
      }
     
      System.out.println(Arrays.toString(fin.split("")));
      String newfin = "";
      for(int i = 0;i<s.length(); i = i+2){
       
         newfin+= s.charAt(i);
         
      }
      System.out.println(newfin);
      String newnewfin = "";
      for(int i = 1;i<s.length(); i = i+2){
       
         newnewfin+= s.charAt(i);
         
      }
      System.out.println(newnewfin);
      String[] subs  = new String[s.length()-1];
      for(int i = 0; i <s.length()-1;i++){
         subs[i] = s.substring(i,i+2);
      }
      System.out.println(Arrays.toString(subs));
     
      String f = "";
      for(int i =0; i < s.length(); i ++){
         if((i)%3 == 0){
            f+= "!"        ;
         }
         else{
            f+= s.charAt(i);
         }

         
      }
      System.out.println(f);
      String g = s.substring(0,2);
      for(int i =2; i < s.length(); i ++){
         if((i+1)%3 == 0){
            g+= "!" ;      
         }
         else{
            g+= s.charAt(i);
         }

         
      }
      System.out.println(g);




     
   }
}
