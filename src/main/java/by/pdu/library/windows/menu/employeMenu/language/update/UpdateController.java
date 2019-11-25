package by.pdu.library.windows.menu.employeMenu.language.update;

import by.pdu.library.domain.Language;
import by.pdu.library.mapper.LanguageMapper;
import by.pdu.library.utils.support.StringSupport;
import by.pdu.library.windows.SupportWindow;
import by.pdu.library.windows.Window;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.util.Collections;


public class UpdateController extends SupportWindow {
    @FXML
    private TextField nameField;

    private Language language;

    private void setName(String name) {
        nameField.setText(name);
    }

    public void setLanguage(Language language) {
        this.language = language;
        setName(language.getName());
    }

    @FXML
    private void update() {
        if (StringSupport.stringCheck(Collections.singletonList(nameField))) {
            String name = StringSupport.convertName(nameField.getText());
            LanguageMapper mapper = ctx.getBean("languageMapper", LanguageMapper.class);
            mapper.updateLanguage(language.getId(), name);
            close(Window.CLICK_EDIT);
        }
    }
}
