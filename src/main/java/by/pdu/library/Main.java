package by.pdu.library;

import by.pdu.library.utils.support.ApplicationContextImpl;
import by.pdu.library.utils.support.LoadFXML;
import by.pdu.library.windows.authorization.AuthorizationController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    private ApplicationContextImpl ctx;

    @Override
    public void start(Stage primaryStage) throws Exception {
        ctx = new ApplicationContextImpl();

        LoadFXML loader = new LoadFXML(ctx);
        ctx.inject(LoadFXML.class,"loader",loader);
        loader.load("windows/authorization/authorization.fxml","Авторизация",primaryStage,300,275);
        primaryStage.getIcons().add(new Image("img/icon.png"));
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}