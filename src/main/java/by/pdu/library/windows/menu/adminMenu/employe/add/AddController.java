package by.pdu.library.windows.menu.adminMenu.employe.add;

import by.pdu.library.domain.ReadingRoom;
import by.pdu.library.mapper.EmployeMapper;
import by.pdu.library.mapper.ReadingRoomMapper;
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
    private TextField nameField, loginField, passwordField;

    @FXML
    private ComboBox<ReadingRoom> roomBox;

    @FXML
    private void add() {
        ReadingRoom room = roomBox.getValue();
        if (room == null) {
            AlertWindow.errorAlert("Выберите " + roomBox.getPromptText());
            return;
        }

        String name = StringSupport.replaceSpaces(nameField.getText());
        String login = StringSupport.replaceSpaces(loginField.getText());
        String password = StringSupport.replaceSpaces(passwordField.getText());

        if (name.equals("") || login.equals("") || password.equals("")) {
            AlertWindow.errorAlert("Не должно быть пустых полей");
            return;
        }

        try {
            EmployeMapper employeMapper = ctx.getBean("employeMapper", EmployeMapper.class);
            employeMapper.createEmploye(login, password, name, room.getId());
            close(Window.CLICK_ADD);
        } catch (PersistenceException ex) {
            AlertWindow.checkException(ex.getMessage());
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext ctx) {
        super.setApplicationContext(ctx);
        ReadingRoomMapper mapper = ctx.getBean("readingRoomMapper", ReadingRoomMapper.class);
        ObservableList<ReadingRoom> rooms = FXCollections.observableArrayList(mapper.getReadingRoom());
        roomBox.setItems(rooms);
    }
}
