package by.pdu.library.windows.menu.adminMenu.fine;

import by.pdu.library.domain.Employe;
import by.pdu.library.domain.Fine;
import by.pdu.library.mapper.EmployeMapper;
import by.pdu.library.mapper.FineMapper;
import by.pdu.library.utils.support.ApplicationContext;
import by.pdu.library.windows.Window;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class FineController extends Window {


    @FXML
    private TableView fineTable;



    @Override
    public void setApplicationContext(ApplicationContext ctx) {
        super.setApplicationContext(ctx);
        FineMapper fineMapper = ctx.getBean("fineMapper",FineMapper.class);
        ObservableList<Fine> fines = FXCollections.observableArrayList(fineMapper.getFine());
        fineTable.setItems(fines);
        ObservableList<TableColumn> columns = fineTable.getColumns();
        columns.get(0).setCellValueFactory(new PropertyValueFactory<Fine, String>("name"));
        columns.get(1).setCellValueFactory(new PropertyValueFactory<Fine, Float>("price"));
    }


}
