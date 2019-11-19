package by.pdu.library.windows.menu.adminMenu.faculty.update;

import by.pdu.library.domain.Faculty;
import by.pdu.library.mapper.FacultyMapper;
import by.pdu.library.utils.support.StringSupport;
import by.pdu.library.windows.SupportWindow;
import by.pdu.library.windows.Window;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.util.Collections;


public class UpdateController extends SupportWindow {
    @FXML
    private TextField nameField;

    private Faculty faculty;

    private void setName(String name) {
        nameField.setText(name);
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
        setName(faculty.getName());
    }

    @FXML
    private void update() {
        if (StringSupport.stringCheck(Collections.singletonList(nameField))) {
            String name = StringSupport.convertName(nameField.getText());
            FacultyMapper mapper = ctx.getBean("facultyMapper", FacultyMapper.class);
            faculty.setName(name);
            mapper.updateFaculty(faculty);
            close(Window.CLICK_EDIT);
        }
    }
}
