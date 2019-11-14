package by.pdu.library.windows.menu.adminMenu.grade.add;

import by.pdu.library.mapper.GradeMapper;
import by.pdu.library.utils.AlertWindow;
import by.pdu.library.utils.support.StringSupport;
import by.pdu.library.windows.SupportWindow;
import by.pdu.library.windows.Window;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class AddController extends SupportWindow {
    @FXML
    private TextField nameField;

    @FXML
    private void add(){
        String name = StringSupport.replaceSpaces(nameField.getText());
        if (name.equals("")){
            AlertWindow.ErrorAlert("Поле "+nameField.getPromptText()+" не может быть пустым");
            return;
        }

        if (StringSupport.containsDigital(name)){
            AlertWindow.ErrorAlert("Поле "+nameField.getPromptText()+" не может содеражать цифры");
            return;
        }

        name = StringSupport.convertName(name);
        GradeMapper mapper = ctx.getBean("gradeMapper", GradeMapper.class);
        mapper.insertGrade(name);
        close(Window.CLICK_ADD);
    }
}
