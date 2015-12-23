package nyla.solutions.global.patterns.command;


import nyla.solutions.global.exception.RequiredException;
import nyla.solutions.global.patterns.command.Environment;
import nyla.solutions.global.patterns.command.Executable;
import nyla.solutions.global.util.Debugger;

public class ClonerExecutable implements Executable
{
   /**
    * Clone the executable to execute the method
    */
   public Integer execute(Environment env)
   {
	if (this.executable == null)
	   throw new RequiredException("this.executable");
	
	try
	{
		Executable clone = null;
		for(int i=0; i < this.cloneCount;i++)
		{
		   clone = (Executable)this.executable.clone();
		   
		   clone.execute(env);
		   
		   Thread.sleep(pausePeriod);
		} 
		
		return 0;
	} 
	catch (Exception e)
	{
	   throw new nyla.solutions.global.exception.SystemException(Debugger.stackTrace(e));
	}

   }// ----------------------------------------------
   
   /**
    * @return the executable
    */
   public CloneableExecutable getExecutable()
   {
      return executable;
   }

   /**
    * @param executable the executable to set
    */
   public void setExecutable(CloneableExecutable executable)
   {
      this.executable = executable;
   }

   
   /**
    * @return the cloneCount
    */
   public int getCloneCount()
   {
      return cloneCount;
   }

   /**
    * @param cloneCount the cloneCount to set
    */
   public void setCloneCount(int cloneCount)
   {
      this.cloneCount = cloneCount;
   }

   
   /**
    * @return the pausePeriod
    */
   public long getPausePeriod()
   {
      return pausePeriod;
   }

   /**
    * @param pausePeriod the pausePeriod to set
    */
   public void setPausePeriod(long pausePeriod)
   {
      this.pausePeriod = pausePeriod;
   }


   private long pausePeriod = 0;

   private int cloneCount = 0;
   private CloneableExecutable executable = null;

}