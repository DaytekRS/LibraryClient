package by.pdu.library.windows.menu.employeMenu.article.update;

import by.pdu.library.domain.Article;
import by.pdu.library.domain.Author;
import by.pdu.library.domain.Catalog;
import by.pdu.library.mapper.ArticleMapper;
import by.pdu.library.utils.AlertWindow;
import by.pdu.library.windows.Window;
import by.pdu.library.windows.menu.employeMenu.article.ArticleInit;
import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;

public class UpdateController extends ArticleInit {
    private Article article;

    @FXML
    private void update() {
        if (catalogTree.getSelectionModel().getSelectedItem() == null) {
            AlertWindow.errorAlert("Нет выбранного элемента");
            return;
        }

        Catalog item = catalogTree.getSelectionModel().getSelectedItem().getValue();
        if (item == rootCatalog) {
            AlertWindow.errorAlert("Нельзя выбирать этот элемент");
        } else {
            ArticleMapper mapper = ctx.getBean("articleMapper", ArticleMapper.class);
            mapper.updateArticle(article.getId(), nameField.getText(), item.getId());
            mapper.removeAllAuthor(article.getId());
            for (Object author : authorArticleTable.getItems()) {
                mapper.insertAuthor(((Author) author).getId(), article.getId());
            }
            close(Window.CLICK_EDIT);
        }
    }

    private void selectItem(TreeItem<Catalog> items) {
        if (items.getValue().getId().equals(article.getCatalog().getId())) {
            catalogTree.getSelectionModel().select(items);
        } else {
            for (TreeItem<Catalog> item : items.getChildren()) {
                selectItem(item);
            }
        }
    }

    public void setArticle(Article article) {
        this.article = article;
        nameField.setText(article.getName());
        selectItem(catalogTree.getRoot());

        for (Object author : article.getAuthors()) {
            authorTable.getItems().remove(author);
            authorArticleTable.getItems().add(author);
        }
    }
}
