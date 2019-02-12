package integration.email;

import model.entity.api.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;

public class EmailSenderImpl implements EmailClient {

    private Logger logger = LoggerFactory.getLogger(EmailClient.class);

    private JavaMailSender mailSender;


    public EmailSenderImpl(JavaMailSender mailSender){
        this.mailSender = mailSender;
    }

    private  boolean sendRawMessage(String body, String subject, String[] to, String[] cc,
                                    String[] bcc, String replyTo, String from){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setSentDate(new Date());
        mailMessage.setText(body);
        mailMessage.setSubject(subject);
        mailMessage.setTo(to);
        mailMessage.setCc(cc);
        mailMessage.setBcc(bcc);
        mailMessage.setReplyTo(replyTo);
        mailMessage.setFrom(from);

        try{
            this.mailSender.send(mailMessage);
            return true;
        }
        catch (MailException ex) {
            // simply log it and go on...
            logger.error("Failed to send email with error : {}",ex);
            return false;
        }
    }

    private boolean sendMimeMessage(final String body, final String subject, final String[] to, final String[] cc,
                                    final String[] bcc, final String replyTo, final String from){
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            final MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
            helper.setText(body, true);
            helper.setSentDate(new Date());
            if (subject != null) helper.setSubject(subject);
            if (to != null) helper.setTo(to);
            if (cc != null) helper.setCc(cc);
            if (bcc != null) helper.setBcc(bcc);
            if (replyTo != null) helper.setReplyTo(replyTo);
            if (from != null) helper.setFrom(from);
        }
        catch (MessagingException e){
            logger.error("Failed to create email with error : {}",e);
            return false;
        }
        try{
            this.mailSender.send(mimeMessage);
            return true;
        }
        catch (MailException ex) {
            // simply log it and go on...
            logger.error("Failed to send email with error : {}",ex);
            return false;
        }
    }
    @Override
    public boolean sendEmail(Email email){
        return sendEmail(email.getBody(),email.getSubject(),email.getTo(),email.getCc(),
                email.getBcc(),email.getReplyTo(),email.getFrom(),email.isHTML());
    }
    private boolean sendEmail(String body, String subject, String[] to, String[] cc,
                             String[] bcc, String replyTo, String from, boolean isHTML) {
        if (!isHTML) {
            System.out.println("Sending raw text integration.templates.com.example.service.email");
            return sendRawMessage(body,subject,to,cc,bcc,replyTo,from);
        }
        else {
            System.out.println("Sending HTML integration.templates.com.example.service.email");
            return sendMimeMessage(body, subject, to, cc, bcc, replyTo, from);

        }

    }



}
