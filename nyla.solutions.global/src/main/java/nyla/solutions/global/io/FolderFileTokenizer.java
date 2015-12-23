package nyla.solutions.global.io;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;



import nyla.solutions.global.exception.RequiredException;
import nyla.solutions.global.io.FileTokenizer;
import nyla.solutions.global.io.IO;
import nyla.solutions.global.util.Config;


public class FolderFileTokenizer
{	
	/**
	 * @return the folder
	 */
	public Collection<FileTokenizer> getFileTokenizer()
	{
		return fileList;
	}//---------------------------------------------

	/**
	 * @param folder the folder to set
	 */
	public void setFolder(File folder)
	{
		if(folder == null)
			throw new RequiredException("folder in FolderFileTokenizer");
		
		if(!folder.isDirectory())
			throw new RequiredException("valid folder "+folder.getAbsolutePath());
		
		this.folder = folder;
		
		File[] files = IO.listFiles(this.folder, this.listFilter);
		
		fileList = new ArrayList<FileTokenizer>(files.length);
		for(int i=0;i < files.length;i++)
		{
			fileList.add(new FileTokenizer(files[i]));
		}
		
	}//---------------------------------------------	

	/**
	 * @return the listFilter
	 */
	public String getListFilter()
	{
		return listFilter;
	}

	/**
	 * @param listFilter the listFilter to set
	 */
	public void setListFilter(String listFilter)
	{
		this.listFilter = listFilter;
	}

	/**
	 * @return the folder
	 */
	public File getFolder()
	{
		return folder;
	}//---------------------------------------------

	private ArrayList<FileTokenizer> fileList = null;
	private String listFilter = Config.getProperty(this.getClass(),"listFilter","*.*");
	private File folder = null;
}