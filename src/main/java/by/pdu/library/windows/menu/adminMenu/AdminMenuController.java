package by.pdu.library.windows.menu.adminMenu;

import by.pdu.library.utils.support.LoadFXML;
import by.pdu.library.windows.Window;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class AdminMenuController extends Window {

    @FXML
    private Button employeButton;


    @FXML
    private void onEmploye() {
        LoadFXML loader = ctx.getBean("loader", LoadFXML.class);
        loader.load("windows/menu/adminMenu/employe/employe.fxml", "Сотрудники", ((Stage) employeButton.getScene().getWindow()), 600, 400);
    }

    @FXML
    private void onReadingRoom() {
        LoadFXML loader = ctx.getBean("loader", LoadFXML.class);
        loader.load("windows/menu/adminMenu/readingRoom/readingRoom.fxml", "Читальные залы", ((Stage) employeButton.getScene().getWindow()), 600, 400);
    }


    @FXML
    private void onFine() {
        LoadFXML loader = ctx.getBean("loader", LoadFXML.class);
        loader.load("windows/menu/adminMenu/fine/fine.fxml", "Штрафы", ((Stage) employeButton.getScene().getWindow()), 600, 400);
    }
}
