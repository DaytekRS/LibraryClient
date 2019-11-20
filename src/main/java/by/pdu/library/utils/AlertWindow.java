package by.pdu.library.utils;


import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Optional;

public class AlertWindow {
    public static void ErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Ошибка");
        alert.setContentText(message);
        ((Stage) alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image("img/icon.png"));
        alert.showAndWait();
    }

    public static ButtonType ConfirmationAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Удалить");
        alert.setHeaderText("Удаление");
        alert.setResizable(false);
        alert.setContentText(message);
        //  alert.setContentText("Вы действиствительно хотите удалить выбранный элемент?");
        ((Stage) alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image("img/icon.png"));
        Optional<ButtonType> result = alert.showAndWait();
        return result.get();
    }

    public static void ErrorAlert() {
        ErrorAlert("Фиг пойми что за ошибка ;)\nТут мои привилегии кончаются\nБегите глупцы :D");
    }
}
