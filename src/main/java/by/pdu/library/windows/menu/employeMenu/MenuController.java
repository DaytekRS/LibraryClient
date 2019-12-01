package by.pdu.library.windows.menu.employeMenu;

import by.pdu.library.utils.support.ApplicationContext;
import by.pdu.library.utils.support.ApplicationContextImpl;
import by.pdu.library.windows.Window;
import by.pdu.library.windows.menu.employeMenu.author.AuthorController;
import by.pdu.library.windows.menu.employeMenu.catalog.CatalogController;
import by.pdu.library.windows.menu.employeMenu.language.LanguageController;
import by.pdu.library.windows.menu.employeMenu.publishingHouse.PublishingHouseController;
import by.pdu.library.windows.menu.employeMenu.users.UsersController;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeView;

public class MenuController extends Window {
    @FXML
    private TableView publishingHouseTable, languageTable, authorTable, userTable;

    @FXML
    private TreeView catalogTree;

    private ApplicationContextImpl ctxController;

    @FXML
    public void initialize() {
        ctxController = new ApplicationContextImpl();

        PublishingHouseController publishingHouse = new PublishingHouseController(publishingHouseTable);
        ctxController.inject(PublishingHouseController.class, "publishingHouse", publishingHouse);

        LanguageController language = new LanguageController(languageTable);
        ctxController.inject(LanguageController.class, "language", language);

        AuthorController author = new AuthorController(authorTable);
        ctxController.inject(AuthorController.class, "author", author);

        CatalogController catalog = new CatalogController(catalogTree);
        ctxController.inject(CatalogController.class, "catalog", catalog);

        UsersController users = new UsersController(userTable);
        ctxController.inject(UsersController.class, "users", users);
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

    @FXML
    private void addAuthor() {
        ctxController.getBean("author", AuthorController.class).add();
    }

    @FXML
    private void removeAuthor() {
        ctxController.getBean("author", AuthorController.class).remove();
    }

    @FXML
    private void updateAuthor() {
        ctxController.getBean("author", AuthorController.class).update();
    }

    @FXML
    private void addCatalog() {
        ctxController.getBean("catalog", CatalogController.class).add();
    }

    @FXML
    private void removeCatalog() {
        ctxController.getBean("catalog", CatalogController.class).remove();
    }

    @FXML
    private void updateCatalog() {
        ctxController.getBean("catalog", CatalogController.class).update();
    }

    @FXML
    private void addUser() {
        ctxController.getBean("users", UsersController.class).add();
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
