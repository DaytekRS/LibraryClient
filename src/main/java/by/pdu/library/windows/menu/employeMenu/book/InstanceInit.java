package by.pdu.library.windows.menu.employeMenu.book;

import by.pdu.library.domain.Edition;
import by.pdu.library.windows.SupportWindow;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class InstanceInit extends SupportWindow {
    protected Edition edition;

    @FXML
    protected TextField numberInstance;

    public void setEdition(Edition edition) {
        this.edition = edition;
    }

    protected void close(Edition edition) {
        super.close();
        stage.setUserData(edition);
    }
}
