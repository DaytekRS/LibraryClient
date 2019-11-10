package by.pdu.library.windows.menu.adminMenu.department;

import by.pdu.library.domain.Department;
import by.pdu.library.mapper.DepartmentMapper;
import by.pdu.library.utils.support.ApplicationContext;
import by.pdu.library.windows.Window;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class DepartmentController extends Window {

    @FXML
    private TableView departmentTable;

    @Override
    public void setApplicationContext(ApplicationContext ctx) {
        super.setApplicationContext(ctx);
        DepartmentMapper departmentMapper = ctx.getBean("departmentMapper",DepartmentMapper.class);
        ObservableList<Department> departments = FXCollections.observableArrayList(departmentMapper.getDepartment());
        departmentTable.setItems(departments);
        ObservableList<TableColumn> columns = departmentTable.getColumns();
        columns.get(0).setCellValueFactory(new PropertyValueFactory<Department, String>("name"));
        columns.get(1).setCellValueFactory(new PropertyValueFactory<Department, String>("faculty"));
    }
}
