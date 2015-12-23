package nyla.solutions.global.io;


import java.io.File;
import java.io.IOException;



import nyla.solutions.global.exception.SystemException;
import nyla.solutions.global.io.CopyDirectoryExecutable;
import nyla.solutions.global.io.IO;
import nyla.solutions.global.patterns.command.Environment;
import nyla.solutions.global.patterns.command.Executable;
import nyla.solutions.global.util.Config;
import nyla.solutions.global.util.Debugger;


/**
 * Deletes a given file/folders
 * @author Gregory Green
 *
 */
public class DeleteExecutable  implements Executable
{
   /**
    * Delete the a given file/folder
    * @param env the environment information
    * @param args the input arguments
    */
   public void execute(Environment env, String[] args)
   {
	execute();	
   }// ----------------------------------------------
	public void execute()
	{
		try
		{
		   IO.delete(new File(this.destination));   
		} 
		catch (IOException e)
		{
		   throw new SystemException(Debugger.stackTrace(e));
		}
	}
   /**
    * @return destination
    * @see nyla.solutions.global.patterns.command.Command#execute(java.lang.Object)
    */
   public Integer execute(Environment source)
   {
     execute(source,null);
   	 return 0;
   }// --------------------------------------------------------
      
   public String getDestination()
   {
      return destination;
   }

   public void setDestination(String destination)
   {
      this.destination = destination;
   }

   
   private String destination = Config.getProperty(CopyDirectoryExecutable.class,"destination","");



   
}