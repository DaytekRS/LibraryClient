package by.pdu.library.windows.menu.adminMenu.faculty;

import by.pdu.library.domain.Faculty;
import by.pdu.library.mapper.FacultyMapper;
import by.pdu.library.utils.AlertWindow;
import by.pdu.library.utils.support.ApplicationContext;
import by.pdu.library.utils.support.LoadFXML;
import by.pdu.library.windows.Window;
import by.pdu.library.windows.menu.adminMenu.AdminWindow;
import by.pdu.library.windows.menu.adminMenu.faculty.update.UpdateController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class FacultyController extends AdminWindow {
    @FXML
    private TableView facultyTable;

    @Override
    public void setApplicationContext(ApplicationContext ctx) {
        super.setApplicationContext(ctx);
        init();
    }

    @FXML
    private void addFaculty() {
        Stage stage = modalWindow("windows/menu/adminMenu/faculty/add/add.fxml", "Добавить факультет", 275, 130);
        Object data = stage.getUserData();
        if (data != null && (Integer) data == Window.CLICK_ADD) update();

    }

    @FXML
    private void removeFaculty() {
        Object obj = facultyTable.getSelectionModel().getSelectedItem();
        if (obj == null) {
            AlertWindow.errorAlert("Нет выбранного элемента");
            return;
        }
        Faculty faculty = (Faculty) obj;
        FacultyMapper mapper = ctx.getBean("facultyMapper", FacultyMapper.class);
        mapper.removeFaculty(faculty.getId());
        commit();
        update();
    }

    @FXML
    private void updateFaculty() {
        Object obj = facultyTable.getSelectionModel().getSelectedItem();
        if (obj == null) {
            AlertWindow.errorAlert("Нет выбранного элемента");
            return;
        }
        Faculty faculty = (Faculty) obj;
        LoadFXML loader = ctx.getBean("loader", LoadFXML.class);
        Stage stage = new Stage();

        UpdateController controller = (UpdateController) loader.loadModal("windows/menu/adminMenu/faculty/update/update.fxml",
                "Редактировать факультет",
                stage,
                this.stage,
                275,
                130);

        controller.setFaculty(faculty);
        stage.showAndWait();
        Object data = stage.getUserData();
        if (data != null && (Integer) data == Window.CLICK_EDIT)
            update();
    }

    private void update() {
        FacultyMapper facultyMapper = ctx.getBean("facultyMapper", FacultyMapper.class);
        ObservableList<Faculty> faculties = FXCollections.observableArrayList(facultyMapper.getFaculty());
        facultyTable.setItems(faculties);
        facultyTable.refresh();
    }

    private void init() {
        update();
        ObservableList<TableColumn> columns = facultyTable.getColumns();
        columns.get(0).setCellValueFactory(new PropertyValueFactory<Faculty, String>("name"));
    }


}
