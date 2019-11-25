package by.pdu.library.windows.menu.employeMenu;

import by.pdu.library.utils.support.ApplicationContext;
import by.pdu.library.utils.support.ApplicationContextImpl;
import by.pdu.library.windows.Window;
import by.pdu.library.windows.menu.employeMenu.language.LanguageController;
import by.pdu.library.windows.menu.employeMenu.publishingHouse.PublishingHouseController;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

public class MenuController extends Window {
    @FXML
    TableView publishingHouseTable, languageTable;

    private ApplicationContextImpl ctxController;

    @FXML
    public void initialize() {
        ctxController = new ApplicationContextImpl();

        PublishingHouseController publishingHouse = new PublishingHouseController(publishingHouseTable);
        ctxController.inject(PublishingHouseController.class, "publishingHouse", publishingHouse);

        LanguageController language = new LanguageController(languageTable);
        ctxController.inject(LanguageController.class, "language", language);
    }

    @FXML
    private void addPublishingHouse() {
        ctxController.getBean("publishingHouse", PublishingHouseController.class).add();
    }

    @FXML
    private void removePublishingHouse() {
        ctxController.getBean("publishingHouse", PublishingHouseController.class).remove();
    }

    @FXML
    private void updatePublishingHouse() {
        ctxController.getBean("publishingHouse", PublishingHouseController.class).update();
    }

    @FXML
    private void addLanguage() {
        ctxController.getBean("language", LanguageController.class).add();
    }

    @FXML
    private void removeLanguage() {
        ctxController.getBean("language", LanguageController.class).remove();
    }

    @FXML
    private void updateLanguage() {
        ctxController.getBean("language", LanguageController.class).update();
    }


    @Override
    public void setApplicationContext(ApplicationContext ctx) {
        super.setApplicationContext(ctx);
        for (TabController tab : ctxController.getBeans(TabController.class)) {
            tab.setApplicationContext(ctx);
            tab.updateView();
        }
    }
}
