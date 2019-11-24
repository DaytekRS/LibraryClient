package by.pdu.library.windows.menu.adminMenu.department.add;

import by.pdu.library.domain.Faculty;
import by.pdu.library.mapper.DepartmentMapper;
import by.pdu.library.mapper.FacultyMapper;
import by.pdu.library.utils.AlertWindow;
import by.pdu.library.utils.support.ApplicationContext;
import by.pdu.library.utils.support.StringSupport;
import by.pdu.library.windows.SupportWindow;
import by.pdu.library.windows.Window;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.apache.ibatis.exceptions.PersistenceException;

public class AddController extends SupportWindow {
    @FXML
    private TextField nameField;

    @FXML
    private ComboBox<Faculty> facultyBox;

    @FXML
    private void add() {
        Faculty faculty = facultyBox.getValue();
        if (faculty == null) {
            AlertWindow.errorAlert("Выберите " + facultyBox.getPromptText());
            return;
        }

        String name = StringSupport.convertName(nameField.getText());

        if (name.equals("")) {
            AlertWindow.errorAlert("Не должно быть пустых полей");
            return;
        }

        try {
            DepartmentMapper departmentMapper = ctx.getBean("departmentMapper", DepartmentMapper.class);
            departmentMapper.insertDepartment(name, faculty.getId());
            close(Window.CLICK_ADD);
        } catch (PersistenceException ex) {
            AlertWindow.checkException(ex.getMessage());
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext ctx) {
        super.setApplicationContext(ctx);
        FacultyMapper mapper = ctx.getBean("facultyMapper", FacultyMapper.class);
        System.out.println(mapper.getFaculty());
        ObservableList<Faculty> faculties = FXCollections.observableArrayList(mapper.getFaculty());
        facultyBox.setItems(faculties);
    }
}
