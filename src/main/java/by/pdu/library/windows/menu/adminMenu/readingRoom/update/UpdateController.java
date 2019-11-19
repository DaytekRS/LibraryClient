package by.pdu.library.windows.menu.adminMenu.readingRoom.update;

import by.pdu.library.domain.ReadingRoom;
import by.pdu.library.mapper.ReadingRoomMapper;
import by.pdu.library.utils.AlertWindow;
import by.pdu.library.utils.support.StringSupport;
import by.pdu.library.windows.SupportWindow;
import by.pdu.library.windows.Window;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class UpdateController extends SupportWindow {
    private ReadingRoom readingRoom;

    @FXML
    TextField nameField, addressField;

    public void setReadingRoom(ReadingRoom readingRoom) {
        this.readingRoom = readingRoom;
        nameField.setText(readingRoom.getName());
        addressField.setText(readingRoom.getAddress());
    }

    @FXML
    private void update() {
        String name = StringSupport.convertName(nameField.getText());
        String address = StringSupport.replaceSpaces(addressField.getText());
        if (name.equals("")) {
            AlertWindow.ErrorAlert("Поле "+ nameField.getPromptText()+" не может быть пустым");
            return;
        }
        if (address.equals("")) {
            AlertWindow.ErrorAlert("Поле "+ addressField.getPromptText()+" не может быть пустым");
            return;
        }
        ReadingRoomMapper mapper = ctx.getBean("readingRoomMapper", ReadingRoomMapper.class);
        readingRoom.setName(name);
        readingRoom.setAddress(address);
        mapper.updateReadingRoom(readingRoom);
        close(Window.CLICK_EDIT);
    }
}
