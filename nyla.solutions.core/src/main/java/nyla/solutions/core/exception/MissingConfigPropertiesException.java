package nyla.solutions.core.exception;


import nyla.solutions.core.operations.ClassPath;
import nyla.solutions.core.util.Config;


public class MissingConfigPropertiesException extends ConfigException
{

   /**
	 * 
	 */
	private static final long serialVersionUID = 5689827263132067133L;

	/**
    * Constructor for SetupException initializes internal 
    * data settings.
    * 
    */
   public MissingConfigPropertiesException(String key)
   {
	   super("Missing configuration property key:"+key+ " in environment variables, JVM system properties and property file "
			   + Config.RESOURCE_BUNDLE_NAME+".properties  in classpath:"+ClassPath.getClassPathText() );

    
   }//--------------------------------------------

	   

}
