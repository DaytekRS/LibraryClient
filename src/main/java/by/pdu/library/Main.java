package by.pdu.library;

import by.pdu.library.utils.support.ApplicationContextImpl;
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

        FXMLLoader loader = new FXMLLoader(getClass().getResource("windows/authorization/authorization.fxml"));
        Parent root = loader.load();
        AuthorizationController controller = loader.getController();
        controller.setApplicationContext(ctx);
        primaryStage.setTitle("Авторизация");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.getIcons().add(new Image("img/icon.png"));
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
