package common;

import enums.Letter;

public class LetterFA
{
   /**
    * 4 is the step where letter a has formed.
    */
   private int walksetBn = 4;
   private String walksetBPn = "M";

   private int offset = 6;
   StringBuilder walksetCPn = new StringBuilder(Letter.a.pattern);

   private int counterA;
   private int counterB;
   private int counterC;
   private int counterD;

   public void step()
   {
      walksetBn++;

      walksetBPn = String.valueOf(walksetCPn.charAt(0));

      fractalProcessMove();

      if ("L".equalsIgnoreCase(walksetBPn.toString()))
      {
         fractalProcessCopy();
         fractalProcessChange();
      }

      counterA = 0;
      counterB = 0;
      counterC = 0;
      counterD = 0;

      String copyCPn = walksetCPn.toString();
      String head = copyCPn.substring(0, offset);
      int start = offset;
      int end = offset + 6;
      for (; end < copyCPn.length();)
      {
         String letter = copyCPn.substring(start, end);
         start += 6;
         end += 6;
         matchLetter(letter);
      }
      matchLetter(copyCPn.substring(start) + head);
   }

   public void matchLetter(String letter)
   {
      if (Letter.a.pattern.equals(letter))
      {
         counterA++;
      }
      else if (Letter.b.pattern.equals(letter))
      {
         counterB++;
      }
      else if (Letter.c.pattern.equals(letter))
      {
         counterC++;
      }
      else if (Letter.d.pattern.equals(letter))
      {
         counterD++;
      }
      else
      {
         System.err.println("invalid letter " + letter + " n = " + walksetBn);
         System.exit(1);
      }
   }

   private void fractalProcessChange()
   {
      int index = walksetBn + 1;
      for (int i = 0; i < walksetCPn.length(); i++)
      {
         if ((index % walksetBn) == 0)
         {
            walksetCPn.replace(i, i + 1, "M");
         }
         index++;
      }
   }

   private void fractalProcessCopy()
   {

      String copy = walksetCPn.toString();

      for (int i = 1; i < walksetBn; i++)
      {
         walksetCPn.append(copy);
      }
   }

   private void fractalProcessMove()
   {
      String firstSymbol = walksetCPn.substring(0, 1);
      walksetCPn.append(firstSymbol);
      walksetCPn.deleteCharAt(0);

      offset--;
      if (offset < 0)
      {
         offset = 5;
      }
   }

   public int getCounterA()
   {
      return counterA;
   }

   public int getCounterB()
   {
      return counterB;
   }

   public int getCounterC()
   {
      return counterC;
   }

   public int getCounterD()
   {
      return counterD;
   }

   public int getWalksetBn()
   {
      return walksetBn;
   }

   public Integer getPatternSize()
   {
      return walksetCPn.length();
   }
}
