package by.pdu.library.windows.menu.employeMenu.article;

import by.pdu.library.domain.Author;
import by.pdu.library.domain.Catalog;
import by.pdu.library.mapper.AuthorMapper;
import by.pdu.library.utils.AlertWindow;
import by.pdu.library.utils.CatalogSupport;
import by.pdu.library.utils.support.ApplicationContext;
import by.pdu.library.windows.SupportWindow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ArticleInit extends SupportWindow {
    @FXML
    protected TextField nameField;

    @FXML
    protected TreeView<Catalog> catalogTree;

    @FXML
    protected TableView authorTable, authorArticleTable;

    protected Catalog rootCatalog;

    @FXML
    public void initialize() {
        ObservableList<TableColumn> columns = authorTable.getColumns();
        columns.get(0).setCellValueFactory(new PropertyValueFactory<Author, Long>("name"));

        ObservableList<TableColumn> columnsAr = authorArticleTable.getColumns();
        columnsAr.get(0).setCellValueFactory(new PropertyValueFactory<Author, Long>("name"));
    }

    @Override
    public void setApplicationContext(ApplicationContext ctx) {
        super.setApplicationContext(ctx);
        rootCatalog = CatalogSupport.initCatalog(ctx, catalogTree);
        AuthorMapper authorMapper = ctx.getBean("authorMapper", AuthorMapper.class);
        ObservableList<Author> authors = FXCollections.observableArrayList(authorMapper.getAuthor());
        authorTable.setItems(authors);
        authorTable.refresh();
    }

    @FXML
    private void addAuthor() {
        Author author = (Author) authorTable.getSelectionModel().getSelectedItem();
        if (author == null) {
            AlertWindow.errorAlert("Автор не выбран");
        } else {
            authorTable.getItems().remove(author);
            authorArticleTable.getItems().add(author);
        }
    }

    @FXML
    private void removeAuthor() {
        Author author = (Author) authorArticleTable.getSelectionModel().getSelectedItem();
        if (author == null) {
            AlertWindow.errorAlert("Автор не выбран");
        } else {
            authorArticleTable.getItems().remove(author);
            authorTable.getItems().add(author);
        }
    }
}
