package nyla.solutions.global.patterns.workthread;

import nyla.solutions.global.util.Debugger;

/**
 * 
 * <pre>
 * StartState starts a worker's thread if it is not alive
 * </pre> 
 * @author Gregory Green
 * @version 1.0
 */
public class StartState implements WorkState 
{   
   /**
    * 
    * @see java.lang.Runnable#run()
    */
   public void advise(SupervisedWorker worker)
   {
	   if(worker == null)
		   return;
	   
      Debugger.println(this, "Advising the worker "+worker.getName());
      
      Thread workerThread = worker.getThread();
      
      if(!workerThread.isAlive())
      {
         workerThread.start();
      }
      
      
   }// --------------------------------------------

   /**
    * @return the name
    */
   public String getName()
   {
      return name;
   }// --------------------------------------------

   /**
    * 
    * @see java.lang.Object#toString()
    */
   public String toString()
   {
    
      return name;
   }// --------------------------------------------
   private String name = this.getClass().getName();
}
