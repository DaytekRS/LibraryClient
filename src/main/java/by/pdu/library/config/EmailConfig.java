package by.pdu.library.config;

import by.pdu.library.utils.support.ApplicationContext;
import org.apache.ibatis.io.Resources;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.io.IOException;
import java.io.Reader;
import java.util.Properties;

public class EmailConfig {

    ApplicationContext ctx;

    public void setApplicationContext(ApplicationContext ctx) {
        this.ctx = ctx;
    }

    public void configure() {
        try {
            Properties emailProperties = new Properties();
            Reader inputStream = Resources.getResourceAsReader("email.properties");
            emailProperties.load(inputStream);
            JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
            mailSender.setHost(emailProperties.getProperty("mail.host"));
            mailSender.setPort(Integer.parseInt(emailProperties.getProperty("mail.port")));
            mailSender.setUsername(emailProperties.getProperty("mail.username"));
            mailSender.setPassword(emailProperties.getProperty("mail.password"));
            mailSender.setProtocol(emailProperties.getProperty("mail.protocol"));
            Properties props = new Properties();
            props.put("mail.smtps.auth", emailProperties.getProperty("mail.smtps.auth"));
            mailSender.setJavaMailProperties(props);

            SimpleMailMessage registrationMessage = new SimpleMailMessage();
            registrationMessage.setFrom(emailProperties.getProperty("mail.from"));
            registrationMessage.setSubject(emailProperties.getProperty("mail.subjectRegistration"));

            ctx.inject(SimpleMailMessage.class, "registrationMessage", registrationMessage);
            ctx.inject(JavaMailSender.class, "mailSender", mailSender);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
