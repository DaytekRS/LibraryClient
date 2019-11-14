package by.pdu.library.windows.menu.adminMenu.grade.update;

import by.pdu.library.domain.Grade;
import by.pdu.library.mapper.GradeMapper;
import by.pdu.library.utils.support.StringSupport;
import by.pdu.library.windows.SupportWindow;
import by.pdu.library.windows.Window;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.Collections;


public class UpdateController extends SupportWindow {
    @FXML
    private TextField nameField;

    private Grade grade;

    private void setName(String name){
        nameField.setText(name);
    }

    public void setGrade(Grade grade){
        this.grade=grade;
        setName(grade.getName());
    }

    @FXML
    private void update(){
        if (StringSupport.stringCheck(Collections.singletonList(nameField))) {
            String name = StringSupport.convertName(nameField.getText());
            GradeMapper mapper = ctx.getBean("gradeMapper", GradeMapper.class);
            grade.setName(name);
            mapper.updateGrade(grade);
            close(Window.CLICK_EDIT);
        }
    }
}
