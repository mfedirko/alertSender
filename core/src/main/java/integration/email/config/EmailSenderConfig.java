package integration.email.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@PropertySource("classpath:email.properties")
@Configuration
public class EmailSenderConfig {

    private Logger logger = LoggerFactory.getLogger(EmailSenderConfig.class);

    @Value("${integration.com.example.service.email.host}") private String host;
    @Value("${integration.com.example.service.email.port}") private int port;
    @Value("${integration.com.example.service.email.username}") private String username;
    @Value("${integration.com.example.service.email.password}") private String password;
    @Value("${integration.com.example.service.email.auth.enable}") private String smtpAuth;
    @Value("${integration.com.example.service.email.starttls.enable}") private String startTlsEnabled;
    @Value("${integration.com.example.service.email.debug.enable}") private String debug;

    @Bean
    public JavaMailSender javaMailSender(){
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost(host);
        javaMailSender.setPort(port);
        javaMailSender.setUsername(username);
        javaMailSender.setPassword(password);
        Properties props = new Properties();
        props.put("mail.smtp.auth", smtpAuth);
        props.put("mail.smtp.starttls.enable", startTlsEnabled);
        props.put("mail.debug.enable",debug);
        javaMailSender.setJavaMailProperties(props);
        logger.debug("Mail sender config: {} {} {}",props,username,host);
        return javaMailSender;
    }
}
