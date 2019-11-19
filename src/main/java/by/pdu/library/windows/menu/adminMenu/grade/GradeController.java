package by.pdu.library.windows.menu.adminMenu.grade;

import by.pdu.library.domain.Grade;
import by.pdu.library.mapper.GradeMapper;
import by.pdu.library.utils.AlertWindow;
import by.pdu.library.utils.support.ApplicationContext;
import by.pdu.library.utils.support.LoadFXML;
import by.pdu.library.windows.Window;
import by.pdu.library.windows.menu.adminMenu.AdminWindow;
import by.pdu.library.windows.menu.adminMenu.grade.update.UpdateController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


public class GradeController extends AdminWindow {
    @FXML
    private TableView gradeTable;

    @Override
    public void setApplicationContext(ApplicationContext ctx) {
        super.setApplicationContext(ctx);
        init();
    }

    @FXML
    private void addGrade() {
        Stage stage = modalWindow("windows/menu/adminMenu/grade/add/add.fxml","Добавить степень преподавателя",275,130);
        Object data = stage.getUserData();
        if (data != null && (Integer) data == Window.CLICK_ADD) update();

    }

    @FXML
    private void removeGrade() {
        Object obj = gradeTable.getSelectionModel().getSelectedItem();
        if (obj == null) {
            AlertWindow.ErrorAlert("Нет выбранного элемента");
            return;
        }
        Grade grade = (Grade) obj;
        GradeMapper mapper = ctx.getBean("gradeMapper", GradeMapper.class);
        mapper.removeGrade(grade.getId());
        commit();
        update();
    }

    @FXML
    private void updateGrade() {
        Object obj = gradeTable.getSelectionModel().getSelectedItem();
        if (obj == null) {
            AlertWindow.ErrorAlert("Нет выбранного элемента");
            return;
        }
        Grade grade = (Grade) obj;
        LoadFXML loader = ctx.getBean("loader", LoadFXML.class);
        Stage stage = new Stage();

        UpdateController controller = (UpdateController) loader.loadModal("windows/menu/adminMenu/grade/update/update.fxml",
                "Редактировать степень преподавателя",
                stage,
                this.stage,
                275,
                130);

        controller.setGrade(grade);
        stage.showAndWait();
        Object data = stage.getUserData();
        if (data != null && (Integer) data == Window.CLICK_EDIT)
            update();
    }

    private void update() {
        GradeMapper gradeMapper = ctx.getBean("gradeMapper", GradeMapper.class);
        ObservableList<Grade> grades = FXCollections.observableArrayList(gradeMapper.getGrade());
        gradeTable.setItems(grades);
        gradeTable.refresh();

    }

    private void init() {
        update();
        ObservableList<TableColumn> columns = gradeTable.getColumns();
        columns.get(0).setCellValueFactory(new PropertyValueFactory<Grade, String>("name"));
    }

}
