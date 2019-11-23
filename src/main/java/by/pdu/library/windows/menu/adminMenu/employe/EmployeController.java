package by.pdu.library.windows.menu.adminMenu.employe;

import by.pdu.library.domain.Employe;
import by.pdu.library.mapper.EmployeMapper;
import by.pdu.library.utils.AlertWindow;
import by.pdu.library.utils.support.ApplicationContext;
import by.pdu.library.utils.support.LoadFXML;
import by.pdu.library.windows.Window;
import by.pdu.library.windows.menu.adminMenu.AdminWindow;
import by.pdu.library.windows.menu.adminMenu.employe.update.UpdateController;
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

    @FXML
    private void removeEmploye() {
        Object obj = employeTable.getSelectionModel().getSelectedItem();
        if (obj == null) {
            AlertWindow.errorAlert("Нет выбранного элемента");
            return;
        }
        Employe employe = (Employe) obj;
        EmployeMapper mapper = ctx.getBean("employeMapper", EmployeMapper.class);
        mapper.dropEmploye(employe.getLogin());
        commit();
        update();
    }

    @FXML
    private void updateEmploye() {
        Object obj = employeTable.getSelectionModel().getSelectedItem();
        if (obj == null) {
            AlertWindow.errorAlert("Нет выбранного элемента");
            return;
        }
        Employe employe = (Employe) obj;
        LoadFXML loader = ctx.getBean("loader", LoadFXML.class);
        Stage stage = new Stage();

        UpdateController controller = (UpdateController) loader.loadModal("windows/menu/adminMenu/employe/update/update.fxml",
                "Редактировать сотрудника",
                stage,
                this.stage,
                295,
                165);

        controller.setEmploye(employe);
        stage.showAndWait();
        Object data = stage.getUserData();
        if (data != null && (Integer) data == Window.CLICK_EDIT)
            update();
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
