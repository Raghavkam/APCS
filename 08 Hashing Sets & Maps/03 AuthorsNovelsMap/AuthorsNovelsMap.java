//  name:J6-16-23
// date:03-16-23         

import java.io.*;
import java.util.*;

public class AuthorsNovelsMap
{
   public static void main(String[] args) throws IOException
   {
      Scanner keyboard = new Scanner(System.in);
      System.out.print("\nEnter input file name: ");
      String inFileName = keyboard.nextLine().trim()+".txt";
      Scanner inputFile = new Scanner(new File(inFileName));
      //System.out.print("\nEnter output file name: ");
      //String outFileName = keyboard.nextLine().trim();
   
      AuthorsMap authors = readAndMakeTheList(inputFile);
      PrintWriter outputFile = new PrintWriter(new FileWriter("authorsNovelsOut.txt"));
      outputFile.println( authors.toString() );
      inputFile.close(); 						
      outputFile.close();
      System.out.println("File created.");
   }
   
   public static AuthorsMap readAndMakeTheList(Scanner inputFile)
   {
      AuthorsMap theAuthors = new AuthorsMap();
      while(inputFile.hasNextLine())
      {
         theAuthors.readOneLine(inputFile.nextLine());
      }
      return theAuthors;
   }
}

class AuthorsMap extends TreeMap<String, Set<String>>
{
  /**   you get a TreeMap for free  **/
   public AuthorsMap(){   
      super();     
      }
   public void readOneLine(String oneLine) 
   { 
     int count = 0;
     int comma = oneLine.indexOf(",");
     String auth = "";
     String boks = "";
     while(count < comma){
           auth += oneLine.substring(count, count+1);
           count++;
     }
     count += 2;
     while(count < oneLine.length()){
           boks += oneLine.substring(count, count+1);
           count++;
     }
     addAuthorOrNovel(auth, boks);
   }
   
   /**  either inserts a new Author mapping, or updates a previous Author mapping
        */
   public void addAuthorOrNovel(String name, String book)
   {
       name = name.toUpperCase();
       if(!this.containsKey(name)){
         Set<String> trans = new HashSet<String>();
         trans.add(book);
     
         this.put(name, trans);
      }
      else {
         Set trans = this.get(name);
         trans.add(book);
         this.put(name, trans);
     }

   }
   
   public String toString()
   {
       String dis = "";
      
      for (Map.Entry<String,Set<String>> entry : this.entrySet()) {
         String val = "";
         for(String s: entry.getValue()){
            val += s + ", ";
         }
         val = val.substring(0,val.length()-2);
         if(val.contains("[") || val.contains("]")){
            for(int i = 0; i < val.length(); i++){
               if(val.substring(i,i+1).equals("[") || val.substring(i,i+1).equals("]")){
                 val = val.substring(0,i) + val.substring(i+1);
               }
            }
         }
         dis += (entry.getKey()+ ": " + val + "\n");
      }
    
      return dis; 

   }
}
   

/**********************  SAMPLE RUN  ********************************
   /******** Output file for infile2:
   
   DOSTOEVSKI: Crime and Punishment, The Possessed, The Brothers Karamazov, The Grand Inquisitor
   FLAUBERT: Madame Bovary, A Simple Heart, Memoirs of a Madman, Sentimental Education
   STENDHAL: The Red and the Black
   TOLSTOY: Anna Karenina, War and Peace, The Death of Ivan Illyich, The Kreutzer Sonata
   
    **********************************/