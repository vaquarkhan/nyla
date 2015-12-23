/**
 * 
 */
package nyla.solutions.dao;

import org.junit.Ignore;

import nyla.solutions.dao.XmlQuery;
import nyla.solutions.global.patterns.decorator.XmlXslDecorator;
import nyla.solutions.global.patterns.servicefactory.ServiceFactory;
import nyla.solutions.global.util.Debugger;
import junit.framework.TestCase;

/**
 * @author Gregory Green
 *
 */
@Ignore
public class XmlQueryTest extends TestCase
{

   /**
    * @param name
    */
   public XmlQueryTest(String name)
   {
	super(name);
   }

   /* (non-Javadoc)
    * @see junit.framework.TestCase#setUp()
    */
   protected void setUp() throws Exception
   {
	super.setUp();
   }

   /**
    * Test method for {@link nyla.solutions.dao.XmlQuery#getText()}.
    */
   public void testGetText()
   {
	String out = ((XmlQuery)ServiceFactory.getInstance().create(id)).getText();
	Debugger.println(this,out);
	assertNotNull(out);
	
   }
   public void testXslForQuery()
   {
		String out = ((XmlXslDecorator)ServiceFactory.getInstance().create(XmlXslDecorator.class.getName())).getText();
		Debugger.println(this,out);
		assertNotNull(out);
   }

   private String id = XmlQuery.class.getName();
}