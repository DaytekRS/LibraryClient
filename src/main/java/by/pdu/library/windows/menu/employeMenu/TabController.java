package by.pdu.library.windows.menu.employeMenu;

import by.pdu.library.windows.Window;
import javafx.scene.control.Control;

public abstract class TabController extends Window {

    public TabController(Control view) {
    }

    public abstract void updateView();

    public abstract void update();

    public abstract void add();

    public abstract void remove();
}
