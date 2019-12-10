package by.pdu.library.windows.menu.employeMenu.book.updateInstance;

import by.pdu.library.domain.Employe;
import by.pdu.library.domain.Instance;
import by.pdu.library.mapper.BookMapper;
import by.pdu.library.mapper.UserMapper;
import by.pdu.library.utils.AlertWindow;
import by.pdu.library.windows.menu.employeMenu.book.InstanceInit;
import javafx.fxml.FXML;

public class UpdateInstance extends InstanceInit {

    private Instance instance;

    @FXML
    private void update() {
        try {
            BookMapper mapper = ctx.getBean("bookMapper", BookMapper.class);
            mapper.updateInstance(instance.getNumberInstance(),Long.parseLong(numberInstance.getText()) );
            instance.setNumberInstance(Long.parseLong(numberInstance.getText()));
            close(instance);
        } catch (Exception ex) {
            AlertWindow.errorAlert(ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void setInstance(Instance instance) {
        this.instance = instance;
        this.numberInstance.setText(instance.getNumberInstance().toString());
    }
}
