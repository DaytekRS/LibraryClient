package by.pdu.library.windows.menu.employeMenu.users.update;

import by.pdu.library.domain.*;
import by.pdu.library.mapper.CardMapper;
import by.pdu.library.utils.AlertWindow;
import by.pdu.library.windows.Window;
import by.pdu.library.windows.menu.employeMenu.users.UserModalWindow;
import javafx.fxml.FXML;

public class UpdateController extends UserModalWindow {
    private Card card;

    private boolean updateStudent(String birthday, String validDate) throws Exception {
        CardMapper mapper = ctx.getBean("cardMapper", CardMapper.class);
        if (groupBox.getValue() == null) {
            AlertWindow.errorAlert("Не выбранна " + groupBox.getPromptText());
            return false;
        }
        Long groupId = ((Group) groupBox.getValue()).getId();
        mapper.updateStudentCard(card.getId(), nameField.getText(), birthday, validDate, groupId);
        return true;
    }

    private boolean updateTeacher(String birthday, String validDate) throws Exception {
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
        mapper.updateTeacherCard(card.getId(), nameField.getText(), birthday, validDate, departmentId, gradeId);
        return true;
    }

    private boolean updateGraduateStudent(String birthday, String validDate) throws Exception {
        CardMapper mapper = ctx.getBean("cardMapper", CardMapper.class);
        mapper.updateGraduateStudent(card.getId(), nameField.getText(), birthday, validDate, scientificField.getText());
        return true;
    }

    private boolean updateOther(String birthday, String validDate) throws Exception {
        CardMapper mapper = ctx.getBean("cardMapper", CardMapper.class);
        mapper.updateOther(card.getId(), nameField.getText(), birthday, validDate, registrationField.getText(), workField.getText(), passportField.getText());
        return true;
    }

    @FXML
    private void update() {
        try {
            String birthday = birthdayPicker.getEditor().getText();
            String validDate = validPicker.getEditor().getText();
            String tabName = choosePane.getSelectionModel().getSelectedItem().getText();
            switch (tabName) {
                case "Студент":
                    if (!updateStudent(birthday, validDate)) return;
                    break;
                case "Преподаватель":
                    if (!updateTeacher(birthday, validDate)) return;
                    break;
                case "Аспирант":
                    if (!updateGraduateStudent(birthday, validDate)) return;
                    break;
                case "Другие":
                    if (!updateOther(birthday, validDate)) return;
                    break;
                default:
                    return;
            }
            close(Window.CLICK_EDIT);
        } catch (Exception ex) {
            AlertWindow.checkException(ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void setCard(Card card) {
        this.card = card;
        nameField.setText(card.getFIO());
        String[] dates = card.getBirthday().toString().split("-");
        String birthday = dates[2] + "." + dates[1] + "." + dates[0];
        birthdayPicker.getEditor().setText(birthday);
        dates = card.getValidDate().toString().split("-");
        String validDate = dates[2] + "." + dates[1] + "." + dates[0];
        validPicker.getEditor().setText(validDate);
        switch (card.getType()) {
            case "Студент":
                StudentCard studentCard = (StudentCard) card;
                groupBox.getSelectionModel().select(studentCard.getGroup());
                choosePane.getSelectionModel().select(0);
                break;
            case "Преподаватель":
                TeacherCard teacherCard = (TeacherCard) card;
                departmentBox.getSelectionModel().select(teacherCard.getDepartment());
                gradeBox.getSelectionModel().select(teacherCard.getGrade());
                choosePane.getSelectionModel().select(1);
                break;
            case "Аспирант":
                GraduateStudentCard graduateStudentCard = (GraduateStudentCard) card;
                scientificField.setText(graduateStudentCard.getScientificTopic());
                choosePane.getSelectionModel().select(2);
                break;
            case "Другие":
                OtherCard otherCard = (OtherCard) card;
                registrationField.setText(otherCard.getRegistration());
                workField.setText(otherCard.getWork());
                passportField.setText(otherCard.getPassport());
                choosePane.getSelectionModel().select(3);
                break;
            default:
                return;
        }
    }
}
