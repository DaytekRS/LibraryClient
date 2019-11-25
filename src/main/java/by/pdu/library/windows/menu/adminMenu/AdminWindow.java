package by.pdu.library.windows.menu.adminMenu;

import by.pdu.library.utils.support.LoadFXML;
import by.pdu.library.windows.Window;
import javafx.fxml.FXML;

public class AdminWindow extends Window {
    @FXML
    protected void cancelButton() {
        LoadFXML loader = ctx.getBean("loader", LoadFXML.class);
        loader.load("windows/menu/adminMenu/adminMenu.fxml", "Меню", stage, 360, 410);
    }
}
