package by.pdu.library.windows.menu.adminMenu.grade;

import by.pdu.library.domain.Faculty;
import by.pdu.library.domain.Grade;
import by.pdu.library.mapper.FacultyMapper;
import by.pdu.library.mapper.GradeMapper;
import by.pdu.library.utils.support.ApplicationContext;
import by.pdu.library.windows.Window;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


public class GradeController extends Window {
    @FXML
    private TableView gradeTable;

    @Override
    public void setApplicationContext(ApplicationContext ctx) {
        super.setApplicationContext(ctx);
        GradeMapper gradeMapper = ctx.getBean("gradeMapper",GradeMapper.class);
        ObservableList<Grade> grades = FXCollections.observableArrayList(gradeMapper.getGrade());
        gradeTable.setItems(grades);
        ObservableList<TableColumn> columns = gradeTable.getColumns();
        columns.get(0).setCellValueFactory(new PropertyValueFactory<Grade, String>("name"));
    }
}
