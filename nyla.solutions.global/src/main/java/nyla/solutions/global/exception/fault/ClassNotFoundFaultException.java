/**
 * 
 */
package nyla.solutions.global.exception.fault;




/**
 * Class Not Found Exception
 * @author Gregory Green
 *
 */
public class ClassNotFoundFaultException extends FaultException
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7441834969107493112L;

	/**
	 * 
	 * @param functionName the function name
	 */
	public ClassNotFoundFaultException(String className)
	{
		super(className);
		
		this.setCategory(FaultException.DEFAULT_ERROR_CATEGORY_NM);
		this.setCode("DF002");
	}// -----------------------------------------------

	/**
	 * 
	 * @param functionName the function name
	 */
	public ClassNotFoundFaultException(String className, Exception e)
	{
		super(className,e);
		this.setCategory(FaultException.DEFAULT_ERROR_CATEGORY_NM);
		this.setCode("DF002");
	}
	
}