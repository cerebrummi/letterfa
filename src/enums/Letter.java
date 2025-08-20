package enums;

import java.util.ArrayDeque;

public enum Letter
{
   a("LMLMMM"), 
   b("LMMMMM"), 
   c("MMLMMM"), 
   d("MMMMMM");
   
   public final ArrayDeque<Character> pattern = new ArrayDeque<Character>();
   public final String stringPattern;

   Letter(String pattern)
   {
      for(String symbol : pattern.split(""))
      {
         this.pattern.add(symbol.charAt(0));
      }
      
      stringPattern = pattern;
   }
}
