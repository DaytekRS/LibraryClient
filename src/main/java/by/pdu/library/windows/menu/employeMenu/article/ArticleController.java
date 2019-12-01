package by.pdu.library.windows.menu.employeMenu.article;

import by.pdu.library.domain.Article;
import by.pdu.library.mapper.ArticleMapper;
import by.pdu.library.utils.AlertWindow;
import by.pdu.library.utils.support.LoadFXML;
import by.pdu.library.windows.Window;
import by.pdu.library.windows.menu.employeMenu.TabController;
import by.pdu.library.windows.menu.employeMenu.article.update.UpdateController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ArticleController extends TabController {
    private TableView tableView;

    public ArticleController(TableView tableView) {
        super(tableView);
        this.tableView = tableView;
        ObservableList<TableColumn> columns = tableView.getColumns();
        columns.get(0).setCellValueFactory(new PropertyValueFactory<Article, String>("name"));
        columns.get(1).setCellValueFactory(new PropertyValueFactory<Article, String>("catalog"));
    }

    @Override
    public void updateView() {
        ArticleMapper mapper = ctx.getBean("articleMapper", ArticleMapper.class);
        ObservableList<Article> articles = FXCollections.observableArrayList(mapper.getArticle());
        tableView.setItems(articles);
        tableView.refresh();
    }

    @Override
    public void update() {
        Object obj = tableView.getSelectionModel().getSelectedItem();
        if (obj == null) {
            AlertWindow.errorAlert("Нет выбранного элемента");
            return;
        }
        Article article = (Article) obj;
        LoadFXML loader = ctx.getBean("loader", LoadFXML.class);
        Stage stage = new Stage();

        UpdateController controller = (UpdateController) loader.loadModal("windows/menu/employeMenu/article/update/update.fxml",
                "Редактировать статью",
                stage,
                this.stage,
                545,
                575);

        controller.setArticle(article);
        stage.showAndWait();
        Object data = stage.getUserData();
        if (data != null && (Integer) data == Window.CLICK_EDIT)
            updateView();
    }

    @Override
    public void add() {
        Stage stage = modalWindow("windows/menu/employeMenu/article/add/add.fxml", "Добавить статью", 545, 575);
        Object data = stage.getUserData();
        if (data != null && (Integer) data == Window.CLICK_ADD) updateView();
    }

    @Override
    public void remove() {
        Object obj = tableView.getSelectionModel().getSelectedItem();
        if (obj == null) {
            AlertWindow.errorAlert("Нет выбранного элемента");
            return;
        }
        Article article = (Article) obj;
        ArticleMapper mapper = ctx.getBean("articleMapper", ArticleMapper.class);
        mapper.removeArticle(article.getId());
        commit();
        updateView();
    }
}
