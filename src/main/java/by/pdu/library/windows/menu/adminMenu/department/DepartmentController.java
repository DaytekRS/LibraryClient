package by.pdu.library.windows.menu.adminMenu.department;

import by.pdu.library.domain.Department;
import by.pdu.library.mapper.DepartmentMapper;
import by.pdu.library.utils.AlertWindow;
import by.pdu.library.utils.support.ApplicationContext;
import by.pdu.library.utils.support.LoadFXML;
import by.pdu.library.windows.Window;
import by.pdu.library.windows.menu.adminMenu.AdminWindow;
import by.pdu.library.windows.menu.adminMenu.department.update.UpdateController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class DepartmentController extends AdminWindow {

    @FXML
    private TableView departmentTable;

    @Override
    public void setApplicationContext(ApplicationContext ctx) {
        super.setApplicationContext(ctx);
        update();
        ObservableList<TableColumn> columns = departmentTable.getColumns();
        columns.get(0).setCellValueFactory(new PropertyValueFactory<Department, String>("name"));
        columns.get(1).setCellValueFactory(new PropertyValueFactory<Department, String>("faculty"));
    }

    @FXML
    private void addDepartment() {
        Stage stage = modalWindow("windows/menu/adminMenu/department/add/add.fxml", "Добавить кафедру", 295, 165);
        Object data = stage.getUserData();
        if (data != null && (Integer) data == Window.CLICK_ADD) update();
    }

    @FXML
    private void removeDepartment() {
        Object obj = departmentTable.getSelectionModel().getSelectedItem();
        if (obj == null) {
            AlertWindow.errorAlert("Нет выбранного элемента");
            return;
        }
        Department department = (Department) obj;
        DepartmentMapper mapper = ctx.getBean("departmentMapper", DepartmentMapper.class);

        mapper.dropDepartment(department.getId());
        commit();
        update();
    }

    @FXML
    private void updateDepartment() {
        Object obj = departmentTable.getSelectionModel().getSelectedItem();
        if (obj == null) {
            AlertWindow.errorAlert("Нет выбранного элемента");
            return;
        }
        Department department = (Department) obj;
        LoadFXML loader = ctx.getBean("loader", LoadFXML.class);
        Stage stage = new Stage();

        UpdateController controller = (UpdateController) loader.loadModal("windows/menu/adminMenu/department/update/update.fxml",
                "Редактировать кафедру",
                stage,
                this.stage,
                295,
                165);

        controller.setDepartment(department);
        stage.showAndWait();
        Object data = stage.getUserData();
        if (data != null && (Integer) data == Window.CLICK_EDIT)
            update();
    }

    private void update() {
        DepartmentMapper departmentMapper = ctx.getBean("departmentMapper", DepartmentMapper.class);
        ObservableList<Department> departments = FXCollections.observableArrayList(departmentMapper.getDepartment());
        departmentTable.setItems(departments);
        departmentTable.refresh();
    }

}
