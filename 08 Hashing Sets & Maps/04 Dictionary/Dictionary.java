// Name: J6-16-23
// Date: 03-17-23

import java.io.*;
import java.util.*;

public class Dictionary {
	public static void main(String[] args) {
		PrintWriter outputFile = null;
		Scanner infile = null;
		try {
			infile = new Scanner(new File("spanglish.txt"));
			outputFile = new PrintWriter(new FileWriter("dictionaryOutput.txt"));
		} catch (Exception e) {
			System.out.println(e);
		}

		Map<String, Set<String>> eng2spn = makeDictionary(infile);
		outputFile.println("ENGLISH TO SPANISH");
		outputFile.println(display(eng2spn));

		Map<String, Set<String>> spn2eng = reverse(eng2spn);
		outputFile.println("SPANISH TO ENGLISH");
		outputFile.println(display(spn2eng));
		outputFile.close();

		System.out.println("File created.");
	}
	public static Map<String, Set<String>> makeDictionary(Scanner infile) {
		Map<String, Set<String>> map = new TreeMap<String, Set<String>>();
		while (infile.hasNext()) {
			add(map, infile.nextLine(), infile.nextLine());
		}
		return map;
	}
   /* if(!dictionary.containsKey(word)){
         Set<String> trans = new HashSet<String>();
         trans.add(translation);
         dictionary.put(word, trans);
      }
      else {
         Set trans = new HashSet();
         trans.add(dictionary.get(word));
         trans.add(translation);
         dictionary.put(word, trans);
     }
  */
	public static void add(Map<String, Set<String>> dictionary, String word, String translation) {
	   if(!dictionary.containsKey(word)){
         Set<String> trans = new HashSet<String>();
         trans.add(translation);
         dictionary.put(word, trans);
      }
      else {
         Set trans = dictionary.get(word);
         trans.add(translation);
         dictionary.put(word, trans);
     }
      
   }

	public static String display(Map<String, Set<String>> m) {

      String dis = "";
      
      for (Map.Entry<String,Set<String>> entry : m.entrySet()) {
         dis += (entry.getKey()+ " " + entry.getValue() + "\n");
      }
    
      return dis; 
	}

	public static Map<String, Set<String>> reverse(Map<String, Set<String>> dictionary) {
       Map<String, Set<String>> map = new TreeMap<String, Set<String>>();
      
      for(Map.Entry<String,Set<String>> entry : dictionary.entrySet()) {
         String key = entry.getKey();
         Set<String> values = entry.getValue();
         for(String val: values){
            add(map, val, key);
         }
      }
    
      return map;
   
	}
}

/********************
 * FILE INPUT:
 * holiday
 * fiesta
 * holiday
 * vacaciones
 * party
 * fiesta
 * celebration
 * fiesta
 * <etc.>
 *********************************** 
 * FILE OUTPUT:
 * ENGLISH TO SPANISH
 * banana [banana]
 * celebration [fiesta]
 * computer [computadora, ordenador]
 * double [doblar, doble, duplicar]
 * father [padre]
 * feast [fiesta]
 * good [bueno]
 * hand [mano]
 * hello [hola]
 * holiday [fiesta, vacaciones]
 * party [fiesta]
 * plaza [plaza]
 * priest [padre]
 * program [programa, programar]
 * sleep [dormir]
 * son [hijo]
 * sun [sol]
 * vacation [vacaciones]
 * 
 * SPANISH TO ENGLISH
 * banana [banana]
 * bueno [good]
 * computadora [computer]
 * doblar [double]
 * doble [double]
 * dormir [sleep]
 * duplicar [double]
 * fiesta [celebration, feast, holiday, party]
 * hijo [son]
 * hola [hello]
 * mano [hand]
 * ordenador [computer]
 * padre [father, priest]
 * plaza [plaza]
 * programa [program]
 * programar [program]
 * sol [sun]
 * vacaciones [holiday, vacation]
 * 
 **********************/