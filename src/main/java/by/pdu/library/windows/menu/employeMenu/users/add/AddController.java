package by.pdu.library.windows.menu.employeMenu.users.add;

import by.pdu.library.domain.Department;
import by.pdu.library.domain.Grade;
import by.pdu.library.domain.Group;
import by.pdu.library.mapper.CardMapper;
import by.pdu.library.utils.AlertWindow;
import by.pdu.library.utils.PasswordGen;
import by.pdu.library.windows.Window;
import by.pdu.library.windows.menu.employeMenu.users.UserModalWindow;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class AddController extends UserModalWindow {
    @FXML
    private ComboBox groupBox, departmentBox, gradeBox;

    @FXML
    private TabPane choosePane;

    @FXML
    private TextField nameField, loginField, emailField, scientificField, registrationField, workField, passportField;

    @FXML
    private DatePicker birthdayPicker, validPicker;

    private boolean addStudent(String birthday, String validDate, String password) throws Exception {
        CardMapper mapper = ctx.getBean("cardMapper", CardMapper.class);
        if (groupBox.getValue() == null) {
            AlertWindow.errorAlert("Не выбранна " + groupBox.getPromptText());
            return false;
        }
        Long groupId = ((Group) groupBox.getValue()).getId();
        mapper.createStudentCard(loginField.getText(), password, emailField.getText(), nameField.getText(), birthday, validDate, groupId);
        return true;
    }

    private boolean addTeacher(String birthday, String validDate, String password) throws Exception {
        CardMapper mapper = ctx.getBean("cardMapper", CardMapper.class);
        if (departmentBox.getValue() == null) {
            AlertWindow.errorAlert("Не выбранна " + groupBox.getPromptText());
            return false;
        }
        if (gradeBox.getValue() == null) {
            AlertWindow.errorAlert("Не выбранна " + groupBox.getPromptText());
            return false;
        }
        Long departmentId = ((Department) departmentBox.getValue()).getId();
        Long gradeId = ((Grade) gradeBox.getValue()).getId();
        mapper.createTeacherCard(loginField.getText(), password, emailField.getText(), nameField.getText(), birthday, validDate, departmentId, gradeId);
        return true;
    }

    private boolean addGraduateStudent(String birthday, String validDate, String password) throws Exception {
        CardMapper mapper = ctx.getBean("cardMapper", CardMapper.class);
        mapper.createGraduateStudent(loginField.getText(), password, emailField.getText(), nameField.getText(), birthday, validDate, scientificField.getText());
        return true;
    }

    private boolean addOther(String birthday, String validDate, String password) throws Exception {
        CardMapper mapper = ctx.getBean("cardMapper", CardMapper.class);
        mapper.createOther(loginField.getText(), password, emailField.getText(), nameField.getText(), birthday, validDate, registrationField.getText(), workField.getText(), passportField.getText());
        return true;
    }

    @FXML
    private void add() {
        try {
            String birthday = birthdayPicker.getEditor().getText();
            String validDate = validPicker.getEditor().getText();
            CardMapper mapper = ctx.getBean("cardMapper", CardMapper.class);
            BCryptPasswordEncoder passwordEncoder = ctx.getBean("passwordEncoder", BCryptPasswordEncoder.class);
            String genPassword = PasswordGen.generate(12);
            String password = passwordEncoder.encode(genPassword);
            String tabName = choosePane.getSelectionModel().getSelectedItem().getText();
            switch (tabName) {
                case "Студент":
                    if (!addStudent(birthday, validDate, password)) return;
                    break;
                case "Преподаватель":
                    if (!addTeacher(birthday, validDate, password)) return;
                    break;
                case "Аспирант":
                    if (!addGraduateStudent(birthday, validDate, password)) return;
                    break;
                case "Другие":
                    if (!addOther(birthday, validDate, password)) return;
                    break;
                default:
                    return;
            }
            JavaMailSender mailSender = ctx.getBean("mailSender", JavaMailSender.class);
            SimpleMailMessage message = new SimpleMailMessage(ctx.getBean("registrationMessage", SimpleMailMessage.class));

            message.setTo(emailField.getText());
            message.setText("Hello " + loginField.getText() + ",\nYou password: " + genPassword);
            mailSender.send(message);
            close(Window.CLICK_ADD);
        } catch (Exception ex) {
            AlertWindow.checkException(ex.getMessage());
            ex.printStackTrace();
        }
    }
}
