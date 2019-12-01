package by.pdu.library.windows.menu.employeMenu.users;

import by.pdu.library.domain.Department;
import by.pdu.library.domain.Grade;
import by.pdu.library.domain.Group;
import by.pdu.library.mapper.DepartmentMapper;
import by.pdu.library.mapper.GradeMapper;
import by.pdu.library.mapper.GroupMapper;
import by.pdu.library.utils.support.ApplicationContext;
import by.pdu.library.windows.SupportWindow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;

public abstract class UserModalWindow extends SupportWindow {
    @FXML
    protected ComboBox groupBox, departmentBox, gradeBox;

    @FXML
    protected TabPane choosePane;

    @FXML
    protected TextField nameField, loginField, emailField, scientificField, registrationField, workField, passportField;

    @FXML
    protected DatePicker birthdayPicker, validPicker;

    @Override
    public void setApplicationContext(ApplicationContext ctx) {
        super.setApplicationContext(ctx);
        GroupMapper groupMapper = ctx.getBean("groupMapper", GroupMapper.class);
        GradeMapper gradeMapper = ctx.getBean("gradeMapper", GradeMapper.class);
        DepartmentMapper departmentMapper = ctx.getBean("departmentMapper", DepartmentMapper.class);
        ObservableList<Group> groups = FXCollections.observableArrayList(groupMapper.getGroup());
        ObservableList<Grade> grades = FXCollections.observableArrayList(gradeMapper.getGrade());
        ObservableList<Department> departments = FXCollections.observableArrayList(departmentMapper.getDepartment());
        groupBox.setItems(groups);
        gradeBox.setItems(grades);
        departmentBox.setItems(departments);
    }
}
