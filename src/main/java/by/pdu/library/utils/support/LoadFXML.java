package by.pdu.library.utils.support;

import by.pdu.library.Main;
import by.pdu.library.utils.AlertWindow;
import by.pdu.library.windows.Window;
import by.pdu.library.windows.authorization.AuthorizationController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LoadFXML {
    private ApplicationContext ctx;

    public LoadFXML(ApplicationContext ctx) {
        this.ctx = ctx;
    }

    public void setApplicationContext(ApplicationContext ctx) {
        this.ctx = ctx;
    }

    public void load(String fxml, String title, Stage primaryStage, int width, int height) {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource(fxml));
            Parent root = loader.load();
            Window controller = loader.getController();
            controller.setApplicationContext(ctx);
            primaryStage.setTitle(title);
            primaryStage.setScene(new Scene(root, width, height));
        } catch (IOException e) {
            AlertWindow.ErrorAlert("Произошла непредвиденная ошибка.\nПрограмма будет закрыта");
            e.printStackTrace();
            System.exit(0);
        }

    }
}
