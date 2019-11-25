package by.pdu.library;

import by.pdu.library.utils.support.ApplicationContextImpl;
import by.pdu.library.utils.support.LoadFXML;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.apache.ibatis.session.SqlSession;

public class Main extends Application {

    private ApplicationContextImpl ctx;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.close();

        ctx = new ApplicationContextImpl();

        LoadFXML loader = new LoadFXML(ctx);
        ctx.inject(LoadFXML.class, "loader", loader);
        loader.load("windows/authorization/authorization.fxml", "Авторизация", primaryStage, 300, 275);
        primaryStage.getIcons().add(new Image("img/icon.png"));
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
