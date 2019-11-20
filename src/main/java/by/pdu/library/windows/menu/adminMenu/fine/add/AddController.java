package by.pdu.library.windows.menu.adminMenu.fine.add;
import by.pdu.library.mapper.FacultyMapper;
import by.pdu.library.mapper.FineMapper;
import by.pdu.library.utils.support.ApplicationContext;
import by.pdu.library.utils.support.StringSupport;
import by.pdu.library.windows.SupportWindow;
import by.pdu.library.windows.Window;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.util.Collections;

public class AddController extends SupportWindow {
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

    @FXML
    private void add(){
        if ((StringSupport.stringCheck(Collections.singletonList(nameField)))) {
            String name = StringSupport.convertName(nameField.getText());
            FineMapper mapper = ctx.getBean("fineMapper", FineMapper.class);
            mapper.insertFine(name,Float.parseFloat(priceField.getText()));
            close(Window.CLICK_ADD);
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext ctx) {
        super.setApplicationContext(ctx);

    }
}
