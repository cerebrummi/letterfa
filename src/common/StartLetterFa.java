package common;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class StartLetterFa
{
   private final static int NUMBER_OF_STEPS = 17;

   private static HashMap<String, Integer> counterA = new HashMap<>();
   private static HashMap<String, Integer> counterB = new HashMap<>();
   private static HashMap<String, Integer> counterC = new HashMap<>();
   private static HashMap<String, Integer> counterD = new HashMap<>();

   public static void main(String[] args)
   {
      LetterFA letterFA = new LetterFA();

      for (int i = letterFA.getWalksetBn(); i < NUMBER_OF_STEPS; i++)
      {
         letterFA.step();
         counterA.put(letterFA.getPatternSize() + "_" + (i+1),
               letterFA.getCounterA());
         counterB.put(letterFA.getPatternSize() + "_" + (i+1),
               letterFA.getCounterB());
         counterC.put(letterFA.getPatternSize() + "_" + (i+1),
               letterFA.getCounterC());
         counterD.put(letterFA.getPatternSize() + "_" + (i+1),
               letterFA.getCounterD());
      }

      ArrayList<String> keys = new ArrayList<>(counterA.keySet());
      keys.sort(new Comparator<String>() {

         @Override
         public int compare(String o1, String o2)
         {
            int o1Int = Integer.parseInt(o1.split("_")[1]);
            int o2Int = Integer.parseInt(o2.split("_")[1]);
            return Integer.compare(o1Int, o2Int);
         }
         
      });
      
      for (String key : keys)
      {
         System.out.println("a = " + counterA.get(key) + " size = " + key);
         System.out.println("b = " + counterB.get(key) + " size = " + key);
         System.out.println("c = " + counterC.get(key) + " size = " + key);
         System.out.println("d = " + counterD.get(key) + " size = " + key);
         System.out.println("-------------------------------------------------");
      }

   }

}
