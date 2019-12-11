package by.pdu.library.windows.menu.employeMenu;

import by.pdu.library.Main;
import by.pdu.library.domain.Card;
import by.pdu.library.utils.AlertWindow;
import by.pdu.library.utils.support.ApplicationContext;
import by.pdu.library.utils.support.ApplicationContextImpl;
import by.pdu.library.windows.Window;
import by.pdu.library.windows.menu.employeMenu.article.ArticleController;
import by.pdu.library.windows.menu.employeMenu.author.AuthorController;
import by.pdu.library.windows.menu.employeMenu.book.BookController;
import by.pdu.library.windows.menu.employeMenu.catalog.CatalogController;
import by.pdu.library.windows.menu.employeMenu.language.LanguageController;
import by.pdu.library.windows.menu.employeMenu.order.OrderController;
import by.pdu.library.windows.menu.employeMenu.publishingHouse.PublishingHouseController;
import by.pdu.library.windows.menu.employeMenu.users.UsersController;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.TreeView;
import javafx.stage.FileChooser;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MenuController extends Window {
    @FXML
    private TableView publishingHouseTable, languageTable, authorTable, userTable, articleTable, orderTable;

    @FXML
    private TreeTableView bookTree;

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

        ArticleController article = new ArticleController(articleTable);
        ctxController.inject(ArticleController.class, "article", article);

        BookController book = new BookController(bookTree);
        ctxController.inject(BookController.class, "book", book);

        OrderController order = new OrderController(orderTable);
        ctxController.inject(OrderController.class, "order", order);

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

    @FXML
    private void removeUser() {
        ctxController.getBean("users", UsersController.class).remove();
    }

    @FXML
    private void updateUser() {
        ctxController.getBean("users", UsersController.class).update();
    }

    @FXML
    private void addArticle() {
        ctxController.getBean("article", ArticleController.class).add();
    }

    @FXML
    private void removeArticle() {
        ctxController.getBean("article", ArticleController.class).remove();
    }

    @FXML
    private void updateArticle() {
        ctxController.getBean("article", ArticleController.class).update();
    }

    @FXML
    private void addBook() {
        ctxController.getBean("book", BookController.class).add();
    }

    @FXML
    private void addInstanceBook() {
        ctxController.getBean("book", BookController.class).addInstance();
    }

    @FXML
    private void updateInstanceBook() {
        ctxController.getBean("book", BookController.class).updateInstance();
    }

    @FXML
    private void removeInstanceBook() {
        ctxController.getBean("book", BookController.class).removeInstance();
    }

    @FXML
    private void updateBook() {
        ctxController.getBean("book", BookController.class).update();
    }

    @FXML
    private void removeBook() {
        ctxController.getBean("book", BookController.class).remove();
    }

    @FXML
    private void addOrder() {
        ctxController.getBean("order", OrderController.class).add();
    }

    @FXML
    private void updateOrder() {
        ctxController.getBean("order", OrderController.class).update();
    }

    @FXML
    private void removeOrder() {
        ctxController.getBean("order", OrderController.class).remove();
    }

    @FXML
    private void getCard() {
        try {
            if (userTable.getSelectionModel().getSelectedItem() == null) {
                AlertWindow.errorAlert("Не выбран элемент");
                return;
            }
            ArrayList<Card> dataBeanList = new ArrayList<>();
            dataBeanList.add((Card) userTable.getSelectionModel().getSelectedItem());
            JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(dataBeanList);
            Map<String, Object> parameters = new HashMap<>();
            System.out.println(Main.class.getResource("/report/library.jrxml"));
            File reportPattern = new File(Main.class.getResource("/report/library.jrxml").getFile());
            JasperDesign jasperDesign = JRXmlLoader.load(reportPattern);
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,
                    parameters, beanColDataSource);

            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf");
            fileChooser.getExtensionFilters().add(extFilter);

            File file = fileChooser.showSaveDialog(stage);

            if (file != null) {
                JasperExportManager.exportReportToPdfFile(jasperPrint, file.getPath());
                AlertWindow.informationAlert("Файл успешно сохранен");
            }

        } catch (Exception ex) {
            AlertWindow.errorAlert(ex.getMessage());
            ex.printStackTrace();
        }
    }



    @FXML
    private void update(){
        System.out.println("work");
        SqlSession session = ctx.getBean("session",SqlSession.class);
        session.commit();
        for (TabController tab : ctxController.getBeans(TabController.class)) {
            tab.setApplicationContext(ctx);
            tab.updateView();
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext ctx) {
        super.setApplicationContext(ctx);
        update();
    }
}
