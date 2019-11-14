package by.pdu.library.windows.menu.adminMenu.grade;

import by.pdu.library.domain.Faculty;
import by.pdu.library.domain.Grade;
import by.pdu.library.mapper.FacultyMapper;
import by.pdu.library.mapper.GradeMapper;
import by.pdu.library.utils.AlertWindow;
import by.pdu.library.utils.support.ApplicationContext;
import by.pdu.library.utils.support.LoadFXML;
import by.pdu.library.windows.Window;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class GradeController extends Window {
    @FXML
    private TableView gradeTable;

    @Override
    public void setApplicationContext(ApplicationContext ctx) {
        super.setApplicationContext(ctx);
        update();
    }

    @FXML
    private void addGrade() {
        LoadFXML loader = ctx.getBean("loader", LoadFXML.class);
        Stage stage = new Stage();
        stage.initOwner(gradeTable.getScene().getWindow());
        stage.initModality(Modality.APPLICATION_MODAL);
        loader.load("windows/menu/adminMenu/grade/add/add.fxml", "Добавить степень преподавателя", stage, 275, 130);
        stage.showAndWait();
        Object data = stage.getUserData();
        if (data!=null && (Integer)data == Window.CLICK_ADD) {
            System.out.println("add");
            update();
        }else System.out.println("cancel");
    }

    @FXML
    private void removeGrade(){
        Object obj = gradeTable.getSelectionModel().getSelectedItem();
        if (obj==null){
            AlertWindow.ErrorAlert("Нет выбранного элемента");
            return;
        }
        Grade grade = (Grade) obj;
        GradeMapper mapper = ctx.getBean("gradeMapper",GradeMapper.class);
        mapper.removeGrade(grade.getId());
        update();
    }

    private void update() {
        GradeMapper gradeMapper = ctx.getBean("gradeMapper", GradeMapper.class);
        ObservableList<Grade> grades = FXCollections.observableArrayList(gradeMapper.getGrade());
        gradeTable.setItems(grades);
        ObservableList<TableColumn> columns = gradeTable.getColumns();
        columns.get(0).setCellValueFactory(new PropertyValueFactory<Grade, String>("name"));
    }
}
