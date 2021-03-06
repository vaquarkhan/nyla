package nyla.solutions.core.io.csv;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import nyla.solutions.core.io.IO;


/**
 * Test for the CsvWriter class
 * @author Gregory Green
 *
 */
public class CsvWriterTest
{
	@Test
	public void testAddCells() throws Exception
	{
		StringBuilder builder = new StringBuilder();
		CsvWriter.addCells(builder, "hello","world");
		
		assertEquals(builder.toString(),"\"hello\",\"world\"");
		
	}//------------------------------------------------
	@Test
	public void testToCsv() throws Exception
	{
		assertEquals(CsvWriter.toCSV("hello","world"),"\"hello\",\"world\"");
		
	}//------------------------------------------------

	@Test
	public void testAppendRowStringArray()
	throws IOException
	{
		File file = new File("target/runtime/CsvWriterTest.csv");
		CsvWriter writer = new CsvWriter(file);
		
		int cnt  = 0;
		String ts = String.valueOf(System.currentTimeMillis());
		writer.appendRow(ts,String.valueOf(cnt));
		
		assertTrue(file.exists());
		assertTrue(IO.readFile(file).contains(ts));
		
	}

}
