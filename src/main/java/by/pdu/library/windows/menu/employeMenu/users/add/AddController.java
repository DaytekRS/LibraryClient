package by.pdu.library.windows.menu.employeMenu.users.add;

import by.pdu.library.windows.SupportWindow;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class AddController extends SupportWindow {

    private void add(){
        JavaMailSender mailSender = ctx.getBean("mailSender", JavaMailSender.class);
        SimpleMailMessage message = new SimpleMailMessage(ctx.getBean("registrationMessage",SimpleMailMessage.class));
        String password = net.lacit.laciteka.utilities.PasswordGen.generate(12);
        message.setTo("daytek.pc@gmail.com");
        message.setText("Hello ");
        mailSender.send(message);
    }
}
