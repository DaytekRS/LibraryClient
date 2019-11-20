package by.pdu.library.windows.menu.adminMenu.employe;

import by.pdu.library.domain.Employe;
import by.pdu.library.mapper.EmployeMapper;
import by.pdu.library.utils.support.ApplicationContext;
import by.pdu.library.windows.Window;
import by.pdu.library.windows.menu.adminMenu.AdminWindow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class EmployeController extends AdminWindow {
    @FXML
    private TableView employeTable;


    @Override
    public void setApplicationContext(ApplicationContext ctx) {
        super.setApplicationContext(ctx);
        init();
    }

    @FXML
    private void addEmploye() {
        Stage stage = modalWindow("windows/menu/adminMenu/employe/add/add.fxml", "Добавить работника", 450, 170);
        Object data = stage.getUserData();
        System.out.println(data);
        if (data != null && (Integer) data == Window.CLICK_ADD) update();
    }

    private void update() {
        EmployeMapper employeMapper = ctx.getBean("employeMapper", EmployeMapper.class);
        ObservableList<Employe> employes = FXCollections.observableArrayList(employeMapper.getEmploye());
        employeTable.setItems(employes);
        employeTable.refresh();
    }

    private void init() {
        ObservableList<TableColumn> columns = employeTable.getColumns();
        columns.get(0).setCellValueFactory(new PropertyValueFactory<Employe, Integer>("id"));
        columns.get(1).setCellValueFactory(new PropertyValueFactory<Employe, String>("FIO"));
        columns.get(2).setCellValueFactory(new PropertyValueFactory<Employe, String>("room"));
        update();
    }
}
