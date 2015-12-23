package nyla.solutions.global.json;
import nyla.solutions.global.operations.ClassPath;
import nyla.solutions.global.patterns.creational.BuilderDirector;
import nyla.solutions.global.util.Config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

/**
 * To add additional register serialize
 * 
 * Use the following property
 * solutions.global.json.GSON.builderDirectorClassName=someClass
 * 
 * 
 * Usage
 * 
 * JSON json = JSON.newInstance();
		
   String jsonString = json.toJson(paging);
		
 * @author Gregory Green
 *
 */
public class GSON extends JSON
{
	/**
	 * Constructor
	 */
	public GSON()
	{
	
		director = ClassPath.newInstance(builderDirectorClassName);
		
		GsonBuilder builder = new GsonBuilder();		     
		director.construct(builder);
		
		this.gson = builder.create();
	}// -----------------------------------------------
	public synchronized String toJson(Object src)
	{
		return gson.toJson(src);
	}// -----------------------------------------------
	@SuppressWarnings("rawtypes")
	public synchronized String toJson(Object src, Class classType)
	{
		return gson.toJson(src, classType);
	}// -----------------------------------------------

	@SuppressWarnings({ "unchecked"})
	public synchronized <T> T fromJson(String json, Class<?> classOfT)
	{
		return (T)gson.fromJson(json, classOfT);
	}// -----------------------------------------------

	
	/**
	 * @param src
	 * @return
	 * @see com.google.gson.Gson#toJsonTree(java.lang.Object)
	 */
	public JsonElement toJsonTree(Object src)
	{
		return gson.toJsonTree(src);
	}// --------------------------------------------------------


	private final BuilderDirector<GsonBuilder> director;
	private String builderDirectorClassName = Config.getProperty(GSON.class,"builderDirectorClassName",GsonBuilderDirector.class.getName());
	private final Gson gson;
	
}
