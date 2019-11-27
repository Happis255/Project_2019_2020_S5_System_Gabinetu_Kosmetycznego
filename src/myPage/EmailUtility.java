package myPage;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class EmailUtility {

    public static void sendEmailWithAttachment(String host, String port,
                                               final String userName, final String password, String toAddress,
                                               String subject, String message, List<File> attachedFiles)
                                               throws AddressException, MessagingException {

        String filename, ext = "";

        if (attachedFiles != null && attachedFiles.size() > 0) {
            for (File aFile : attachedFiles) {
                filename = aFile.getName();
                ext = filename.substring(filename.lastIndexOf('.') + 1);
                if (!ext.equals("pdf") && !ext.equals("doc") && !ext.equals("docx") && !ext.equals("PDF") && !ext.equals("DOC") && !ext.equals("DOCX")) {
                    throw new MessagingException();
                }
            }
        }

        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.user", userName);
        properties.put("mail.password", password);

        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        };
        Session session = Session.getInstance(properties, auth);

        MimeMessage msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(userName));
        InternetAddress[] toAddresses = {new InternetAddress(toAddress)};
        msg.setRecipients(Message.RecipientType.TO, toAddresses);
        msg.setSubject( subject, "utf-8" );
        msg.setSentDate(new Date());

        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setHeader("Content-Type","text/html; charset=UTF-8");
        messageBodyPart.setContent(message, "text/html; charset=UTF-8");
        messageBodyPart.setHeader("Content-Transfer-Encoding", "quoted-printable");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);

        if (attachedFiles != null && attachedFiles.size() > 0) {
            for (File aFile : attachedFiles) {
                MimeBodyPart attachPart = new MimeBodyPart();
                try {
                    attachPart.attachFile(aFile);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                multipart.addBodyPart(attachPart);
            }
        }
        msg.setContent(multipart);
        Transport.send(msg);
    }
}