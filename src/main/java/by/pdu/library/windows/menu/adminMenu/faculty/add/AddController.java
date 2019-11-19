package by.pdu.library.windows.menu.adminMenu.faculty.add;

import by.pdu.library.mapper.FacultyMapper;
import by.pdu.library.utils.support.StringSupport;
import by.pdu.library.windows.SupportWindow;
import by.pdu.library.windows.Window;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.util.Collections;

public class AddController extends SupportWindow {
    @FXML
    private TextField nameField;

    @FXML
    public void initialize() {
        nameField.setFocusTraversable(false);
    }

    @FXML
    private void add() {
        if ((StringSupport.stringCheck(Collections.singletonList(nameField)))) {
            String name = StringSupport.convertName(nameField.getText());
            FacultyMapper mapper = ctx.getBean("facultyMapper", FacultyMapper.class);
            mapper.insertFaculty(name);
            close(Window.CLICK_ADD);
        }
    }
}
