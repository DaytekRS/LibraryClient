package by.pdu.library.windows;

import by.pdu.library.utils.support.ApplicationContext;
import by.pdu.library.utils.support.LoadFXML;
import javafx.stage.Stage;
import org.apache.ibatis.session.SqlSession;

public abstract class Window {
    protected Stage stage;
    public static int CLICK_ADD = 1;
    public static int CLICK_CANCEL = 2;
    public static int CLICK_EDIT = 3;
    public static int WINDOW_ERROR = 4;

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

    protected Stage modalWindow(String fxml, String title, int width, int height) {
        Stage stage = new Stage();
        LoadFXML loader = ctx.getBean("loader", LoadFXML.class);
        loader.loadModal(fxml,
                title,
                stage,
                this.stage,
                width,
                height);
        stage.showAndWait();
        return stage;
    }

    protected Stage modalWindow(String fxml, String title) {
        Stage stage = new Stage();
        LoadFXML loader = ctx.getBean("loader", LoadFXML.class);
        loader.loadModal(fxml,
                title,
                stage,
                this.stage);
        stage.showAndWait();
        return stage;
    }
}
