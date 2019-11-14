package by.pdu.library.windows.menu.adminMenu.grade.add;

import by.pdu.library.mapper.GradeMapper;
import by.pdu.library.utils.AlertWindow;
import by.pdu.library.utils.support.StringSupport;
import by.pdu.library.windows.Window;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.apache.ibatis.session.SqlSession;

public class AddController extends Window {
    @FXML
    private TextField nameField;
    @FXML
    public void initialize() {

        Platform.runLater( () -> nameField.requestFocus());
    }
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
        Stage stage = (Stage)nameField.getScene().getWindow();
        stage.setUserData(Window.CLICK_ADD);
        SqlSession session = ctx.getBean("session", SqlSession.class);
        session.commit();
        stage.close();
    }
}
