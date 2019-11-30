package by.pdu.library.utils;


import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Optional;

public class AlertWindow {
    public static void errorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Ошибка");
        alert.setContentText(message);
        ((Stage) alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image("img/icon.png"));
        alert.showAndWait();
    }

    public static ButtonType confirmationAlert(String message) {
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

    public static void errorAlert() {
        errorAlert("Фиг пойми что за ошибка ;)\nТут мои привилегии кончаются\nБегите глупцы :D");
    }

    public static void checkException(String message) {
        if (message.contains("ORA-01920") || message.contains("ORA-20001")) {
            AlertWindow.errorAlert("Такой пользователь существует.\nПопробуйте снова.");
            return;
        }

        if (message.contains("ORA-00001") || message.contains("ORA-20000")) {
            AlertWindow.errorAlert("Одно из полей должно быть уникально.\nПожалуйста исправьте данные и попробуйте снова.");
            return;
        }

        if (message.contains("ORA")) {
            errorAlert("Ошибка Oracle.\nГлупцы бегите быстрее к администратору\nи сообщите код ошибки\n" + message);
        } else {
            errorAlert();
        }
    }
}
