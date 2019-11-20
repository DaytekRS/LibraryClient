package by.pdu.library.windows.menu.adminMenu.readingRoom.add;

import by.pdu.library.mapper.ReadingRoomMapper;
import by.pdu.library.utils.AlertWindow;
import by.pdu.library.utils.support.StringSupport;
import by.pdu.library.windows.SupportWindow;
import by.pdu.library.windows.Window;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;


public class AddController extends SupportWindow {
    @FXML
    TextField nameField, addressField;

    @FXML
    public void initialize() {
        nameField.setFocusTraversable(false);
    }

    @FXML
    private void add() {
        String name = StringSupport.convertName(nameField.getText());
        String address = StringSupport.replaceSpaces(addressField.getText());
        if (name.equals("")) {
            AlertWindow.ErrorAlert("Поле " + nameField.getPromptText() + " не может быть пустым");
            return;
        }
        if (address.equals("")) {
            AlertWindow.ErrorAlert("Поле " + addressField.getPromptText() + " не может быть пустым");
            return;
        }
        ReadingRoomMapper mapper = ctx.getBean("readingRoomMapper", ReadingRoomMapper.class);
        mapper.insertReadingRoom(name, address);
        close(Window.CLICK_ADD);
    }
}
