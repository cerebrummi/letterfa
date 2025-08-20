package common;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

import enums.Letter;

public class LetterFA
{
   /**
    * 4 is the step where letter a has formed.
    */
   private int walksetBn = 4;
   private Character walksetBPn = 'M';

   private int offset = 0;
   ArrayDeque<Character> walksetCPn = new ArrayDeque<>(Letter.a.pattern);

   private int counterA;
   private int counterB;
   private int counterC;
   private int counterD;

   public void step()
   {
      walksetBn++;

      walksetBPn = walksetCPn.peekFirst();

      fractalProcessMove();

      if (Character.compare('L', walksetBPn) == 0)
      {
         fractalProcessCopy();
         fractalProcessChange();
      }

      countLetters();
   }

   private void countLetters()
   {
      counterA = 0;
      counterB = 0;
      counterC = 0;
      counterD = 0;

      LinkedList<Character> walksetCPnList = new LinkedList<>();
      walksetCPnList.addAll(walksetCPn);
      
      int start = offset;
      int end = 6 - offset;
      
      List<Character> head = walksetCPnList.subList(0, start);
      List<Character> tail = walksetCPnList.subList(walksetCPnList.size() - end, walksetCPnList.size());
      
      tail.addAll(head);
      StringBuilder fullLetter = new StringBuilder();
      for(char symbol : tail)
      {
         fullLetter.append(symbol);
      }
      matchLetter(fullLetter.toString());
   }

   public void matchLetter(String letter)
   {
      if (Letter.a.stringPattern.equals(letter))
      {
         System.out.println("letter " + letter + " n = " + walksetBn);
         counterA++;
      }
      else if (Letter.b.stringPattern.equals(letter))
      {
         System.out.println("letter " + letter + " n = " + walksetBn);
         counterB++;
      }
      else if (Letter.c.stringPattern.equals(letter))
      {
         System.out.println("letter " + letter + " n = " + walksetBn);
         counterC++;
      }
      else if (Letter.d.stringPattern.equals(letter))
      {
         System.out.println("letter " + letter + " n = " + walksetBn);
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
      ArrayDeque<Character> changed = new ArrayDeque<>();
      ArrayList<Character> walksetCPnList = new ArrayList<>();
      walksetCPnList.addAll(walksetCPn);

      IntStream.range(walksetBn + 1, walksetCPn.size())
            .allMatch((index) -> findMultiples(index) ? changed.add('M')
                  : changed.add(walksetCPnList.get(index)));
   }

   private boolean findMultiples(int index)
   {
      if ((index % walksetBn) == 0)
      {
         return true;
      }

      return false;
   }

   private void fractalProcessCopy()
   {
      ArrayDeque<Character> copy = new ArrayDeque<>(walksetCPn);

      for (int i = 1; i < walksetBn; i++)
      {
         walksetCPn.addAll(copy);
      }
   }

   private void fractalProcessMove()
   {
      char firstSymbol = walksetCPn.pollFirst();
      walksetCPn.add(firstSymbol);

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
      return walksetCPn.size();
   }
}
