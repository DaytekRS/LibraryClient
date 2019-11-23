package by.pdu.library.windows.menu.adminMenu.group.update;

import by.pdu.library.domain.Faculty;
import by.pdu.library.domain.Group;
import by.pdu.library.mapper.FacultyMapper;
import by.pdu.library.mapper.GroupMapper;
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


public class UpdateController extends SupportWindow {
    private Group group;

    @FXML
    private TextField nameField;

    @FXML
    private ComboBox<Faculty> facultyBox;

    @FXML
    private void update() {
        try {
            String name = StringSupport.replaceSpaces(nameField.getText());

            if (name.equals("")) {
                AlertWindow.errorAlert("Не должно быть пустых полей");
                return;
            }
            GroupMapper mapper = ctx.getBean("groupMapper", GroupMapper.class);
            mapper.updateGroup(group.getId(), nameField.getText(), facultyBox.getValue().getId());
            close(Window.CLICK_EDIT);
        } catch (Exception ex) {
            AlertWindow.checkException(ex.getMessage());
        }
    }

    public void setGroup(Group group) {
        this.group = group;
        nameField.setText(group.getName());
        facultyBox.getSelectionModel().select(group.getFaculty());
    }

    @Override
    public void setApplicationContext(ApplicationContext ctx) {
        super.setApplicationContext(ctx);
        FacultyMapper mapper = ctx.getBean("facultyMapper", FacultyMapper.class);
        ObservableList<Faculty> faculties = FXCollections.observableArrayList(mapper.getFaculty());
        facultyBox.setItems(faculties);
    }
}
