package by.pdu.library.windows.menu.adminMenu.faculty;

import by.pdu.library.domain.Faculty;
import by.pdu.library.mapper.FacultyMapper;
import by.pdu.library.utils.support.ApplicationContext;
import by.pdu.library.windows.Window;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class FacultyController extends Window {
    @FXML
    private TableView facultyTable;

    @Override
    public void setApplicationContext(ApplicationContext ctx) {
        super.setApplicationContext(ctx);
        FacultyMapper facultyMapper = ctx.getBean("facultyMapper",FacultyMapper.class);
        ObservableList<Faculty> faculties = FXCollections.observableArrayList(facultyMapper.getFaculty());
        facultyTable.setItems(faculties);
        ObservableList<TableColumn> columns = facultyTable.getColumns();
        columns.get(0).setCellValueFactory(new PropertyValueFactory<Faculty, String>("name"));
    }
}
