package common;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Locale;

import enums.Letter;

public class StartLetterFa
{
   private final static int NUMBER_OF_STEPS = 17;

   private static HashMap<String, Integer> counter = new HashMap<>();

   public static void main(String[] args)
   {
      LetterFA letterFA = new LetterFA();

      for (int i = letterFA.getWalksetBn(); i < NUMBER_OF_STEPS; i++)
      {
         letterFA.step();
         
         counter.put(letterFA.getPatternSize() + "_" + (i + 1) + "_a",
               letterFA.getCounterA());
         counter.put(letterFA.getPatternSize() + "_" + (i + 1) + "_b",
               letterFA.getCounterB());
         counter.put(letterFA.getPatternSize() + "_" + (i + 1) + "_c",
               letterFA.getCounterC());
         counter.put(letterFA.getPatternSize() + "_" + (i + 1) + "_d",
               letterFA.getCounterD());
      }

      ArrayList<String> keys = new ArrayList<>(counter.keySet());
      handleCounter(keys);

   }

   public static void handleCounter(ArrayList<String> keys)
   {
      keys.sort(new Comparator<String>()
      {

         @Override
         public int compare(String o1, String o2)
         {
            if (o1.split("_")[0].compareTo(o2.split("_")[0]) != 0)
            {
               int o1Int = Integer.parseInt(o1.split("_")[0]);
               int o2Int = Integer.parseInt(o2.split("_")[0]);
               return Integer.compare(o1Int, o2Int);
            }

            if (o1.split("_")[1].compareTo(o2.split("_")[1]) != 0)
            {
               int o1Int = Integer.parseInt(o1.split("_")[1]);
               int o2Int = Integer.parseInt(o2.split("_")[1]);
               return Integer.compare(o1Int, o2Int);
            }

            Collator coll = Collator.getInstance(Locale.GERMAN);
            coll.setStrength(Collator.PRIMARY);
            return coll.compare(o1.split("_")[2], o2.split("_")[2]);
         }
      });

      int a = 0, b = 0, c = 0, d = 0;
      double total = 0;
      for (String key : keys)
      {
         if (Letter.a.name().compareTo(key.split("_")[2]) == 0)
         {
            a = counter.get(key);
         }
         else if (Letter.b.name().compareTo(key.split("_")[2]) == 0)
         {
            b = counter.get(key);
         }
         else if (Letter.c.name().compareTo(key.split("_")[2]) == 0)
         {
            c = counter.get(key);
         }
         else if (Letter.d.name().compareTo(key.split("_")[2]) == 0)
         {
            d = counter.get(key);
            total = a + b + c + d;
         }
         else
         {
            System.err.println("wrong letter");
            System.exit(1);
         }

         System.out.println(
               key.split("_")[2] + " = " + counter.get(key) + " size = " + key);
         
         if (Letter.d.name().compareTo(key.split("_")[2]) == 0)
         {
            System.out.println("total = " + total);

            System.out.println("a = " + (a/total));
            System.out.println("b = " + (b/total));
            System.out.println("c = " + (c/total));
            System.out.println("d = " + (d/total));
            
            System.out.println("--------------------");
         }

      }
   }

}
