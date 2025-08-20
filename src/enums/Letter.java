package enums;

public enum Letter
{
   a("LMLMMM"), 
   b("LMMMMM"), 
   c("MMLMMM"), 
   d("MMMMMM");
   
   final private String pattern;

   Letter(String pattern)
   {
      this.pattern = pattern;
   }

   public String getPattern()
   {
      return pattern;
   }
}
