@Grab(group='org.apache.commons', module='commons-email', version='1.5')
import java.util.zip.*
import groovy.util.AntBuilder.*
import javax.mail.*
import javax.mail.internet.*
import javax.mail.Session.*
import javax.mail.Authenticator.*
import org.apache.commons.mail.util.*
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;

date = new Date().format('yyyy-MM-dd')

 // Create the attachment
EmailAttachment attachment = new EmailAttachment();
attachment.setPath("./jenkins_${date}.zip");
attachment.setDisposition(EmailAttachment.ATTACHMENT);
attachment.setDescription("");
attachment.setName("Jenkins log");

// Create the email message
MultiPartEmail email = new MultiPartEmail();
email.setAuthenticator(new DefaultAuthenticator("test@sender.com", "password"))
email.setHostName("hostname.com");
email.setSmtpPort(587)
email.setTLS(true)
email.addTo("receiver@test.net", "reciever");
email.setFrom("test@sender.com", "Me");
email.setSubject("Jenkins Log of the day");
email.setMsg("Here's your daily log, Have a nice day");

// add the attachment
email.attach(attachment);

// send the email
email.send();    
