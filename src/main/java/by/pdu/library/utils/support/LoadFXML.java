package by.pdu.library.utils.support;

import by.pdu.library.Main;
import by.pdu.library.utils.AlertWindow;
import by.pdu.library.windows.Window;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
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

    public Window loadModal(String fxml, String title, Stage primaryStage, Stage owner, int width, int height) {
        primaryStage.initOwner(owner);
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        return load(fxml, title, primaryStage, width, height);
    }

    public Window loadModal(String fxml, String title, Stage primaryStage, Stage owner, int width, int height, boolean resizable) {
        primaryStage.initOwner(owner);
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        return load(fxml, title, primaryStage, width, height, resizable);
    }

    public Window load(String fxml, String title, Stage primaryStage, int width, int height) {
        return load(fxml, title, primaryStage, width, height, false);
    }

    public Window load(String fxml, String title, Stage primaryStage, int width, int height, boolean resizable) {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource(fxml));
            Parent root = loader.load();
            Window controller = loader.getController();
            controller.setApplicationContext(ctx);
            controller.setStage(primaryStage);
            primaryStage.setTitle(title);
            primaryStage.setScene(new Scene(root, width, height));
            primaryStage.getIcons().removeAll();
            primaryStage.getIcons().add(new Image("img/icon.png"));
            primaryStage.setResizable(resizable);
            return controller;
        } catch (IOException e) {
            AlertWindow.errorAlert("Произошла непредвиденная ошибка.\nПрограмма будет закрыта");
            e.printStackTrace();
            System.exit(0);
        }
        return null;
    }
}
