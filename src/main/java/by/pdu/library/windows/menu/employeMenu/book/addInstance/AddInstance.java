package by.pdu.library.windows.menu.employeMenu.book.addInstance;

import by.pdu.library.domain.Employe;
import by.pdu.library.domain.Instance;
import by.pdu.library.mapper.BookMapper;
import by.pdu.library.mapper.UserMapper;
import by.pdu.library.utils.AlertWindow;
import by.pdu.library.windows.menu.employeMenu.book.InstanceInit;
import javafx.fxml.FXML;

public class AddInstance extends InstanceInit {
    @FXML
    private void add() {
        try {
            BookMapper mapper = ctx.getBean("bookMapper", BookMapper.class);
            UserMapper userMapper = ctx.getBean("userMapper", UserMapper.class);
            Employe user = ctx.getBean("user", Employe.class);
            mapper.insertInstance(edition.getId(), user.getRoom().getId(), Long.parseLong(numberInstance.getText()));
            Instance instance = new Instance(Long.parseLong(numberInstance.getText()), edition);
            close(instance);
        } catch (Exception ex) {
            AlertWindow.errorAlert(ex.getMessage());
            ex.printStackTrace();
        }
    }
}
