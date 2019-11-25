package by.pdu.library.windows.menu.employeMenu.publishingHouse.update;

import by.pdu.library.domain.PublishingHouse;
import by.pdu.library.mapper.PublishingHouseMapper;
import by.pdu.library.utils.support.StringSupport;
import by.pdu.library.windows.SupportWindow;
import by.pdu.library.windows.Window;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.util.Collections;


public class UpdateController extends SupportWindow {
    @FXML
    private TextField nameField;

    private PublishingHouse publishingHouse;

    private void setName(String name) {
        nameField.setText(name);
    }

    public void setPublishingHouse(PublishingHouse publishingHouse) {
        this.publishingHouse = publishingHouse;
        setName(publishingHouse.getName());
    }

    @FXML
    private void update() {
        if (StringSupport.stringCheck(Collections.singletonList(nameField))) {
            String name = StringSupport.convertName(nameField.getText());
            PublishingHouseMapper mapper = ctx.getBean("publishingHouseMapper", PublishingHouseMapper.class);
            mapper.updatePublishingHouse(publishingHouse.getId(), name);
            close(Window.CLICK_EDIT);
        }
    }
}
