package nyla.solutions.core.io.csv;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map.Entry;
import java.util.TreeMap;

import nyla.solutions.core.patterns.conversion.Converter;
import nyla.solutions.core.util.Text;

/**
 * 
 * @author Gregory Green
 *
 */
public class BeanPropertiesToCsvConverter<ObjectType> implements Converter<ObjectType, String>
{

	private final static String GET_PREFIX = "get";
	private final static String SEPARATOR = ",";
	private final static String QUOTE = "\"";
	private final static String NEWLINE = System.getProperty("line.separator","\n");
	private TreeMap<String,Method> methods;
	private final String headerRow;
	
	
	public BeanPropertiesToCsvConverter(Class<ObjectType> objectClass)
	{
		Method[]  methodArray = objectClass.getMethods();
		
		
		Method m;
		String methodName = null;

		methods = new TreeMap<String,Method>();
		
		for (int i = 0; i < methodArray.length; i++)
		{
			m = methodArray[i];
			methodName = m.getName();
			if(! methodName.startsWith(GET_PREFIX) || m.getParameterCount() != 0)
				continue; //not properties
			
	
			methods.put(methodName, m);
		}
		
		StringBuilder csv = new StringBuilder();
		for (String keyMethodName : methods.keySet())
		{
			if(csv.length() != 0)
				csv.append(SEPARATOR);
			
			csv.append(QUOTE).append(format(toFieldName(keyMethodName))).append(QUOTE);
			
		}
		csv.append(NEWLINE);
		
		this.headerRow = csv.toString();
		
	}//------------------------------------------------
	
	/**
	 * @return the headerRow
	 */
	public String getHeaderRow()
	{
		return headerRow;
	}


	@Override
	public String convert(ObjectType sourceObject)
	{
		if(sourceObject == null)
			return null;
		
		String methodName = null;
		Object value =null;
		StringBuilder csv = new StringBuilder();
		Object[] args = {};
		Method m;

		
		for (Entry<String,Method> entry : this.methods.entrySet())
		{
			m = entry.getValue();
			methodName = m.getName();
			try
			{
				value = m.invoke(sourceObject, args);
				
				if(csv.length() != 0)
					csv.append(SEPARATOR);
				
				csv.append(QUOTE).append(format(value)).append(QUOTE);
				
			}
			catch (IllegalAccessException|IllegalArgumentException|InvocationTargetException e)
			{
				throw new RuntimeException("Unable to get property for method:"+methodName+" error:"+e.getMessage(),e);
			}
		}
		
		csv.append(NEWLINE);
		
		return csv.toString();
		
	}


	private String toFieldName(String methodName)
	{		
		return new StringBuilder().append(
				Character.toLowerCase(methodName.charAt(3)))
				.append(methodName.substring(4)).toString(); 
	}
	
	   /**
	    * 
	    * @param object the text version of the object will be formatted
	    * @return text where the separator is replaced with replacement character
	    */
	   private String format(Object object)
	   {
	      String text = Text.toString(object);
	      return Text.replace("\"", "\"\"", text);
	   }// --------------------------------------------
	
}