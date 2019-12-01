package by.pdu.library.windows.menu.employeMenu.book;

import by.pdu.library.domain.*;
import by.pdu.library.mapper.ArticleMapper;
import by.pdu.library.mapper.AuthorMapper;
import by.pdu.library.mapper.LanguageMapper;
import by.pdu.library.mapper.PublishingHouseMapper;
import by.pdu.library.utils.AlertWindow;
import by.pdu.library.utils.CatalogSupport;
import by.pdu.library.utils.support.ApplicationContext;
import by.pdu.library.windows.SupportWindow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class BookInit extends SupportWindow {

    @FXML
    protected TextField nameField, priceField, yearField, imageField, numberField, monthField;

    @FXML
    protected TableView articleTable, periodicTable, authorTable, authorPerTable;

    @FXML
    protected TextArea descriptionArea;

    @FXML
    protected TreeView<Catalog> catalogTree;

    @FXML
    protected ComboBox languageBox, publishingBox;

    @FXML
    protected TabPane choosePane;

    protected Catalog rootCatalog;

    @FXML
    public void initialize() {
        ObservableList<TableColumn> columns = articleTable.getColumns();
        columns.get(0).setCellValueFactory(new PropertyValueFactory<Article, String>("name"));

        ObservableList<TableColumn> columnsPer = periodicTable.getColumns();
        columnsPer.get(0).setCellValueFactory(new PropertyValueFactory<Article, String>("name"));

        ObservableList<TableColumn> columnsAut = authorTable.getColumns();
        columnsAut.get(0).setCellValueFactory(new PropertyValueFactory<Author, String>("name"));

        ObservableList<TableColumn> columnsAutPer = authorPerTable.getColumns();
        columnsAutPer.get(0).setCellValueFactory(new PropertyValueFactory<Author, String>("name"));
    }

    @FXML
    private void addArticle() {
        Article article = (Article) articleTable.getSelectionModel().getSelectedItem();
        if (article == null) {
            AlertWindow.errorAlert("Артикл не выбран");
        } else {
            articleTable.getItems().remove(article);
            periodicTable.getItems().add(article);
        }
    }

    @FXML
    private void addAuthor() {
        Author author = (Author) authorTable.getSelectionModel().getSelectedItem();
        if (author == null) {
            AlertWindow.errorAlert("Автор не выбран");
        } else {
            authorTable.getItems().remove(author);
            authorPerTable.getItems().add(author);
        }
    }

    @FXML
    private void removeArticle() {
        Article article = (Article) periodicTable.getSelectionModel().getSelectedItem();
        if (article == null) {
            AlertWindow.errorAlert("Артикл не выбран");
        } else {
            articleTable.getItems().add(article);
            periodicTable.getItems().remove(article);
        }
    }

    @FXML
    private void removeAuthor() {
        Author author = (Author) authorPerTable.getSelectionModel().getSelectedItem();
        if (author == null) {
            AlertWindow.errorAlert("Автор не выбран");
        } else {
            authorTable.getItems().add(author);
            authorPerTable.getItems().remove(author);
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext ctx) {
        super.setApplicationContext(ctx);
        rootCatalog = CatalogSupport.initCatalog(ctx, catalogTree);

        LanguageMapper languageMapper = ctx.getBean("languageMapper", LanguageMapper.class);
        ObservableList<Language> languages = FXCollections.observableArrayList(languageMapper.getLanguage());
        languageBox.setItems(languages);

        PublishingHouseMapper publishingHouseMapper = ctx.getBean("publishingHouseMapper", PublishingHouseMapper.class);
        ObservableList<PublishingHouse> houses = FXCollections.observableArrayList(publishingHouseMapper.getPublishingHouse());
        publishingBox.setItems(houses);

        ArticleMapper articleMapper = ctx.getBean("articleMapper", ArticleMapper.class);
        ObservableList<Article> article = FXCollections.observableArrayList(articleMapper.getArticle());
        articleTable.setItems(article);

        AuthorMapper authorMapper = ctx.getBean("authorMapper", AuthorMapper.class);
        ObservableList<Author> authors = FXCollections.observableArrayList(authorMapper.getAuthor());
        authorTable.setItems(authors);
    }

    protected void close(Edition edition) {
        super.close();
        stage.setUserData(edition);
    }
}
