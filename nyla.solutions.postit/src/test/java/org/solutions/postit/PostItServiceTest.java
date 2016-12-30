package org.solutions.postit;

import java.io.File;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.junit.Assert;

import nyla.solutions.core.patterns.servicefactory.ServiceFactory;
import nyla.solutions.core.util.Debugger;
import nyla.solutions.net.postit.Package;
import nyla.solutions.net.postit.PostItService;


public class PostItServiceTest
{


   public static void main(String[] args)
   {
   }// --------------------------------------------

   /*
    * Test method for 'org.solutions.postit.PostItService.post(String, Postable)'
    */
   public void testPost()
   throws Exception
   {
      PostItService service = (PostItService)
      ServiceFactory.getInstance().create(PostItService.SERVICE_NAME);
      
      String text = "Testing 123";
      
      Package p = Package.newInstance(text);
      
      Debugger.println(this,"text="+p.getText());
      
      String name = "This is Your Name";
      p.setName(name);
      
      File file = new File("/temp/test.doc");
      
      p.setAttachment(file);
      
      
      Assert.assertNotNull(p.getAttachment());
      
      Assert.assertTrue(name.equals(p.getName()));
      
      Assert.assertTrue(text.equals(p.getText()));
      
      service.post("green_gregory@businessedge.com",p);
      
      //post all
      service.post(p);
   }// --------------------------------------------
   public void testDavMail()
   throws Exception
   {
      Properties prop = new Properties();
      prop.setProperty("mail.debug","true");
      
      // Set the default enveloppe sender address
      prop.setProperty("mail.davmail.from", "green_gregory@hotmail.com");
      Session ses = Session.getInstance(prop);

      // Create the transport connection
      Transport transport = ses.getTransport("davmail_xmit");
      transport.connect(null, "green_gregory", "redmoon");

      // Prepare the message
      MimeMessage txMsg = new MimeMessage(ses);
      txMsg.setSubject("Test subject");

      InternetAddress addrFrom = new InternetAddress(
      "green_gregory@hotmail.com");
      txMsg.setFrom(addrFrom);

      InternetAddress addrTo = new InternetAddress(
      "your_recipient's_address", "your_recipient's_name");
      txMsg.addRecipient(Message.RecipientType.TO, addrTo);

      txMsg.setText("Hello world !");
      txMsg.setSentDate(new Date());

      // Send the message
      transport.sendMessage(txMsg, txMsg.getAllRecipients());

   }

}
