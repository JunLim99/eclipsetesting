import java.util.*;
import java.io.*;

public class QuizGrammar{
   public static void main(String[] args){
      try{
         Scanner s = new Scanner(new File("sentence.txt"));
         Map<String, String[]> map = new HashMap<>();
         
         while(s.hasNext()){
            String str = s.nextLine();
            //skip blank
            if(str.length()>0){
               String[] parts = str.split("::=");
               String[] rules = parts[1].trim().split("[|]");
               map.put(parts[0].trim(), rules);
            }
         }
         for(int i=0; i<5; i++){
         System.out.println(generateEquation(map, "<s>"));
         }

         } catch (FileNotFoundException e){
            System.out.println("Can't find a file.");
         }
   }
   
   public static String generateEquation(Map<String, String[]> map, String symbol){
      Random r = new Random();
      //base     
      if(!map.containsKey(symbol)){
         return symbol + " ";
      }
   // r.nextInt()
   // symbol : s
   // s -> np vp 
   //get symbol : string[0] = np , string [1] = vp
      String result = "";
      String str = map.get(symbol)[r.nextInt(map.get(symbol).length)];
      //to use for loop iteration, constructing string array.
      String[] token = str.split(" ");
      for(int i=0; i<token.length; i++){
         result += generateEquation(map, token[i]);
      }
      return result;
   }
         
      //Using scanner and tokenizing the string     
      /*
      Scanner s = new Scanner(map.get(symbol)[r.nextInt(map.get(symbol).length)]);
      String result = "";
      while(s.hasNext()){
         result += generateEquation(map, s.next());
      }
      return result;
      */
      
      //And this is only working for simple.txt
      /*
      if(!symbol.equals("exp")){
         return map.get(symbol)[r.nextInt(map.get(symbol).length)] + " ";
      }// Key = "exp"
      Scanner s = new Scanner(map.get(symbol)[r.nextInt(2)]);//"exp op num"
      if (s.next().equals("exp")){ //s.next() = newSymbol
         return generateEquation(map, "exp") + generateEquation(map, "op") + generateEquation(map, "num");
      }     
      return map.get("num")[r.nextInt(map.get("num").length)] + " ";
      */
   
}
   