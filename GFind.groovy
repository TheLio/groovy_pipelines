@Grab(group='org.apache.commons', module='commons-email', version='1.5')
import java.util.zip.*
import javax.mail.*
import javax.mail.internet.*
import javax.mail.Session.*
import javax.mail.Authenticator.*
import org.apache.commons.mail.EmailAttachment.*
import org.apache.commons.mail.util.*
import org.apache.commons.mail.*
import groovy.util.AntBuilder.*
import org.apache.commons.mail.MultiPartEmail
import java.io.File.*
import org.apache.commons.mail.DefaultAuthenticator;

//date and get file by name
date = new Date().format('yyyy-MM-dd')

//Zip Ant
new AntBuilder().zip( destFile: "jenkins_${date}.zip" ) {
  fileset( dir: '.' ) {
    include( name:"jenkins_${date}.csv" )
  }
}

// Create the email message
MultiPartEmail email = new MultiPartEmail();
email.setAuthenticator(new DefaultAuthenticator("test@test.com", "password"))
email.setHostName("example.com");
email.setSmtpPort(587)
email.setTLS(true)
email.addTo("receiver@mail.com", "sender");
email.setFrom("test@sender.com", "Me");
email.setSubject("Jenkins Log of the day");
email.setMsg("Here's your daily log, Have a nice day");

 // Create the attachment
EmailAttachment attachment = new EmailAttachment();
attachment.setPath("./jenkins_${date}.zip");
attachment.setDisposition(EmailAttachment.ATTACHMENT);
attachment.setDescription("");
attachment.setName("Jenkins log");

// add the attachment
email.attach(attachment);

// send the email
email.send();    
