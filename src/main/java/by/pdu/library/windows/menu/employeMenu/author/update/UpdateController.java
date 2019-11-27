package by.pdu.library.windows.menu.employeMenu.author.update;

import by.pdu.library.domain.Author;
import by.pdu.library.mapper.AuthorMapper;
import by.pdu.library.utils.support.StringSupport;
import by.pdu.library.windows.SupportWindow;
import by.pdu.library.windows.Window;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.util.Collections;


public class UpdateController extends SupportWindow {
    @FXML
    private TextField nameField;

    private Author author;

    private void setName(String name) {
        nameField.setText(name);
    }

    public void setAuthor(Author author) {
        this.author = author;
        setName(author.getName());
    }

    @FXML
    private void update() {
        if (StringSupport.stringCheck(Collections.singletonList(nameField))) {
            String name = StringSupport.replaceSpaces(nameField.getText());
            AuthorMapper mapper = ctx.getBean("authorMapper", AuthorMapper.class);
            mapper.updateAuthor(author.getId(), name);
            close(Window.CLICK_EDIT);
        }
    }
}
