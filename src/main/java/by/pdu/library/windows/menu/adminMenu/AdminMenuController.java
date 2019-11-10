package by.pdu.library.windows.menu.adminMenu;

import by.pdu.library.utils.support.LoadFXML;
import by.pdu.library.Main;
import by.pdu.library.domain.Employe;
import by.pdu.library.mapper.EmployeMapper;
import by.pdu.library.utils.support.ApplicationContext;
import by.pdu.library.windows.Window;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

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

}
