package by.pdu.library;

import by.pdu.library.config.EmailConfig;
import by.pdu.library.utils.support.ApplicationContextImpl;
import by.pdu.library.utils.support.LoadFXML;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import oracle.sql.JAVA_STRUCT;
import org.apache.ibatis.session.SqlSession;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Properties;

public class Main extends Application {

    private ApplicationContextImpl ctx;

    @Override
    public void start(Stage primaryStage) throws Exception {
        ctx = new ApplicationContextImpl();
        EmailConfig emailConfig = new EmailConfig();
        emailConfig.setApplicationContext(ctx);
        emailConfig.configure();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        ctx.inject(BCryptPasswordEncoder.class,"passwordEncoder",passwordEncoder);
        LoadFXML loader = new LoadFXML(ctx);
        ctx.inject(LoadFXML.class, "loader", loader);
        loader.load("windows/authorization/authorization.fxml", "Авторизация", primaryStage, 300, 275);
        primaryStage.getIcons().add(new Image("img/icon.png"));
        primaryStage.centerOnScreen();
        primaryStage.show();

        primaryStage.setOnCloseRequest(we -> {
            SqlSession session = ctx.getBean("session", SqlSession.class);
            if (session != null) {
                session.close();
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
