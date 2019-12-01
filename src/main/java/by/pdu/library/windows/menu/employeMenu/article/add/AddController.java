package by.pdu.library.windows.menu.employeMenu.article.add;

import by.pdu.library.domain.Author;
import by.pdu.library.domain.Catalog;
import by.pdu.library.mapper.ArticleMapper;
import by.pdu.library.utils.AlertWindow;
import by.pdu.library.windows.Window;
import by.pdu.library.windows.menu.employeMenu.article.ArticleInit;
import javafx.fxml.FXML;

public class AddController extends ArticleInit {


    @FXML
    private void add() {
        try {
            if (catalogTree.getSelectionModel().getSelectedItem() == null) {
                AlertWindow.errorAlert("Нет выбранного элемента");
                return;
            }

            Catalog item = catalogTree.getSelectionModel().getSelectedItem().getValue();
            if (item == rootCatalog) {
                AlertWindow.errorAlert("Нельзя выбирать этот элемент");
            } else {
                ArticleMapper mapper = ctx.getBean("articleMapper", ArticleMapper.class);
                Long id = mapper.nextId();
                mapper.insertArticle(id, nameField.getText(), item.getId());
                for (Object author : authorArticleTable.getItems()) {
                    mapper.insertAuthor(((Author) author).getId(), id);
                }
                close(Window.CLICK_ADD);
            }
        } catch (Exception ex) {
            AlertWindow.errorAlert(ex.getMessage());
            ex.printStackTrace();
        }
    }


}
