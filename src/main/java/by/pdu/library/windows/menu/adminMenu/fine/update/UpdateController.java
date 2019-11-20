package by.pdu.library.windows.menu.adminMenu.fine.update;

import by.pdu.library.domain.Fine;
import by.pdu.library.mapper.FineMapper;
import by.pdu.library.utils.support.StringSupport;
import by.pdu.library.windows.SupportWindow;
import by.pdu.library.windows.Window;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.util.Collections;

public class UpdateController extends SupportWindow {
    private Fine fine;

    @FXML
    private TextField priceField,nameField;

    @FXML
    public void initialize(){
        priceField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d{0,10}([\\.]\\d{0,2})?")) {
                priceField.setText(oldValue);
            }
        });
    }

    public void setFine(Fine fine){
        this.fine=fine;
        priceField.setText(fine.getPrice().toString());
        nameField.setText(fine.getName());
    }

    @FXML
    private void update() {
        if (StringSupport.stringCheck(Collections.singletonList(nameField))) {
            String name = StringSupport.convertName(nameField.getText());
            FineMapper mapper = ctx.getBean("fineMapper", FineMapper.class);
            mapper.updateFine(fine.getId(),name,Float.parseFloat(priceField.getText()));
            close(Window.CLICK_EDIT);
        }
    }
}
