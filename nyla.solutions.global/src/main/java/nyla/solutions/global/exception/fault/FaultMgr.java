
package nyla.solutions.global.exception.fault;

import java.util.HashMap;
import java.util.Map;

import nyla.solutions.global.exception.SetupException;
import nyla.solutions.global.exception.SystemException;
import nyla.solutions.global.util.Config;
import nyla.solutions.global.util.Debugger;

/**
 * Handles mapping of Exception thrown to have a standard Fault error code/category 
 * @author Gregory Green
 *
 */
public class FaultMgr implements FaultService
{
	public FaultMgr()
	{
		
		this.init();
	}// -----------------------------------------------
	
	/**
	 * Maps a given exception into a GEDI exception
	 * @param e the throw exception
	 * @return GediException with the correct mapped error codes/categories
	 */
	//@Override
	public FaultException raise(Throwable e)  
	{
		return raise(e,null);
	}// --------------------------------------------------------
	
	/**
	 * Maps a given exception into a GEDI exception
	 * @param e the throw exception
	 * @return GediException with the correct mapped error codes/categories
	 */
	//@Override
	public FaultException raise(Throwable e, Object argument)  
	{
		Debugger.println(e);
		
		if(e instanceof FaultException)
		{
			FaultException faultException = (FaultException)e;
			this.constructFault(faultException, argument);
			return faultException;
			
		}
			
		
		//wrap into  exception
		FaultException faultException = new SystemException(e);
		FaultError faultError = faultErrorMap.get(e.getClass().getName());
		
		if(faultError != null)
		{
			faultException.setCategory(faultError.getCategory());
			faultException.setCode(faultError.getCode());
		}
		
		constructFault(faultException, argument);
		
		return faultException;
	}// -----------------------------------------------

	public void constructFault(FaultException faultException, Object argument)
	{
		
		//check argument
		if(argument != null && faultException.getArgument() == null)
			faultException.setArgument(argument);
		

		StackTraceElement stackTraceElement = null;
		if(faultException.getModule() == null)
		{
			if(this.defaultModule != null && this.defaultModule.length() > 0)
				faultException.setModule(defaultModule);
			else
			{
				stackTraceElement = determineCauseStackTraceElementsn(faultException);
				
				if(stackTraceElement != null)
					faultException.setModule(stackTraceElement.getFileName());
			}
				
		}
		
		if(faultException.getOperation() == null)
		{
			if(this.defaultOperation != null && this.defaultOperation.length() > 0)
				faultException.setOperation(this.defaultOperation);
			else
			{
				if(stackTraceElement == null)
					stackTraceElement = determineCauseStackTraceElementsn(faultException);
				
				if(stackTraceElement != null)
					faultException.setOperation(stackTraceElement.getMethodName());
				
				
				//add line number
				if((faultException.getNotes() == null || faultException.getNotes().length() == 0) && stackTraceElement != null)
				{
					faultException.setNotes(lineNumberNotePrefix+stackTraceElement.getLineNumber());
					
				}
			}
			
			
		}
		
		
		
	}// -----------------------------------------------
	
	public static StackTraceElement determineCauseStackTraceElementsn(Throwable e)
	{
		
		if(e.getCause() != null)
			return determineCauseStackTraceElementsn(e.getCause());
		
		StackTraceElement[] stackTraceElements = e.getStackTrace();
		
		if(stackTraceElements == null || stackTraceElements.length == 0)
			return null;

		
		return stackTraceElements[0];
		
	}// --------------------------------------------------------
	
	private void init()
	{
		
		faultErrorMap = new HashMap<String,FaultError>();
		
		faultErrorMap.put(nyla.solutions.global.exception.SetupException.class.getName(), new FaultError(SetupException.DEFAULT_ERROR_CODE,SetupException.DEFAULT_ERROR_CATEGORY));
		faultErrorMap.put(nyla.solutions.global.exception.ConnectionException.class.getName(), new FaultError(SetupException.DEFAULT_ERROR_CODE,SetupException.DEFAULT_ERROR_CATEGORY));
		faultErrorMap.put(nyla.solutions.global.exception.ConfigException.class.getName(), new FaultError(SetupException.DEFAULT_ERROR_CODE,SetupException.DEFAULT_ERROR_CATEGORY));
		
	}

	/**
	 * @return the faultErrorMap
	 */
	public Map<String, FaultError> getFaultErrorMap()
	{
		return faultErrorMap;
	}

	/**
	 * @param faultErrorMap the faultErrorMap to set
	 */
	public void setFaultErrorMap(Map<String, FaultError> faultErrorMap)
	{
		this.faultErrorMap = faultErrorMap;
	}

	/**
	 * @return the defaultModule
	 */
	public String getDefaultModule()
	{
		return defaultModule;
	}

	/**
	 * @param defaultModule the defaultModule to set
	 */
	public void setDefaultModule(String defaultModule)
	{
		this.defaultModule = defaultModule;
	}

	/**
	 * @return the defaultOperation
	 */
	public String getDefaultOperation()
	{
		return defaultOperation;
	}

	/**
	 * @param defaultOperation the defaultOperation to set
	 */
	public void setDefaultOperation(String defaultOperation)
	{
		this.defaultOperation = defaultOperation;
	}



	/**
	 * @return the lineNumberNotePrefix
	 */
	public String getLineNumberNotePrefix()
	{
		return lineNumberNotePrefix;
	}

	/**
	 * @param lineNumberNotePrefix the lineNumberNotePrefix to set
	 */
	public void setLineNumberNotePrefix(String lineNumberNotePrefix)
	{
		this.lineNumberNotePrefix = lineNumberNotePrefix;
	}



	private Map<String, FaultError> faultErrorMap = null;
	private String lineNumberNotePrefix = Config.getProperty(FaultMgr.class,"lineNumberNotePrefix","Line number "); 
	private String defaultModule = Config.getProperty(FaultMgr.class,"defaultModule","");
	private String defaultOperation = Config.getProperty(FaultMgr.class,"defaultOperation","");
	

}
