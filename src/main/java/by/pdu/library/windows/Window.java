package by.pdu.library.windows;

import by.pdu.library.utils.support.ApplicationContext;
import javafx.stage.Stage;
import org.apache.ibatis.session.SqlSession;

public abstract class Window {
    protected Stage stage;
    public static int CLICK_ADD = 1;
    public static int CLICK_CANCEL = 2;
    public static int CLICK_EDIT = 3;

    protected ApplicationContext ctx;

    public void setApplicationContext(ApplicationContext ctx) {
        this.ctx = ctx;
    }


    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void commit() {
        SqlSession session = ctx.getBean("session", SqlSession.class);
        session.commit();
    }

}
