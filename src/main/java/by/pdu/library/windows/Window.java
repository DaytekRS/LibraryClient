package by.pdu.library.windows;

import by.pdu.library.utils.support.ApplicationContext;

public class Window {
    public static int CLICK_ADD = 1;
    public static int CLICK_CANCEL = 2;

    protected ApplicationContext ctx;

    public void setApplicationContext(ApplicationContext ctx) {
        this.ctx = ctx;
    }
}
