package by.pdu.library.windows.menu.adminMenu.employe;

import by.pdu.library.domain.Employe;
import by.pdu.library.domain.ReadingRoom;
import by.pdu.library.mapper.EmployeMapper;
import by.pdu.library.utils.support.ApplicationContext;
import by.pdu.library.windows.Window;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class EmployeController extends Window {
    @FXML
    private TableView employeTable;


    @Override
    public void setApplicationContext(ApplicationContext ctx) {
        super.setApplicationContext(ctx);
        EmployeMapper employeMapper = ctx.getBean("employeMapper",EmployeMapper.class);
        ObservableList<Employe> employes = FXCollections.observableArrayList(employeMapper.getEmploye());
        employeTable.setItems(employes);
        ObservableList<TableColumn> columns = employeTable.getColumns();
        columns.get(0).setCellValueFactory(new PropertyValueFactory<Employe, Integer>("id"));
        columns.get(1).setCellValueFactory(new PropertyValueFactory<Employe, String>("FIO"));
        columns.get(2).setCellValueFactory(new PropertyValueFactory<Employe, String>("room"));


    }
}
