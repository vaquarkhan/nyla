package nyla.solutions.global.io;

import java.io.*;
import java.util.regex.*;
/**
 * <pre>
 * WildCardFilter filter used when monitor files.
 * Based on file system wild card search parameters.
 * 
 * 
 * *.exe matches application.exe
 *  ABC?.exe matches ABC1.exe but not AABC.exe
 * </pre> 
 * @author Gregory Green
 * @version 1.0
 */
public class WildCardFilter implements FilenameFilter
{
   /**
    * Note the * will be replaced with .* and ? will be 
    * replaced with .? to be used in a regular expression
    * @param aFilter
    */
   public WildCardFilter(String aFilter)
   {

      toStringFilter  = aFilter;
      //Replace .* with ..*
      
      aFilter = aFilter.replaceAll("\\*", ".*");
      
      //Replace ? with .?
      aFilter = aFilter.replaceAll("\\?", ".?");
      
      pattern = Pattern.compile(aFilter);
   }//------------------------------------------
   /* (non-Javadoc)
    * @see java.io.FileFilter#accept(java.io.File)
    */
   public boolean accept(File pathname, String aName)
   {
       return pattern.matcher(aName).matches();
   }//----------------------------------------
   public String toString()
   {
      return this.toStringFilter;
   }
   private String toStringFilter = "";
   private Pattern pattern = null;

}