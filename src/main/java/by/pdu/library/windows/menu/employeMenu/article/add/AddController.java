package by.pdu.library.windows.menu.employeMenu.article.add;

import by.pdu.library.domain.Catalog;
import by.pdu.library.mapper.ArticleMapper;
import by.pdu.library.mapper.CatalogMapper;
import by.pdu.library.utils.AlertWindow;
import by.pdu.library.utils.support.ApplicationContext;
import by.pdu.library.windows.SupportWindow;
import by.pdu.library.windows.Window;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

import java.util.ArrayList;

public class AddController extends SupportWindow {
    @FXML
    private TextField nameField;

    @FXML
    private TreeView<Catalog> catalogTree;

    private Catalog rootCatalog;

    @FXML
    private void add() {
        if (catalogTree.getSelectionModel().getSelectedItem() == null) {
            AlertWindow.errorAlert("Нет выбранного элемента");
            return;
        }

        Catalog item = catalogTree.getSelectionModel().getSelectedItem().getValue();
        if (item == rootCatalog) {
            AlertWindow.errorAlert("Нельзя выбирать этот элемент");
        } else {
            ArticleMapper mapper = ctx.getBean("articleMapper", ArticleMapper.class);
            mapper.insertArticle(nameField.getText(), item.getId());
            close(Window.CLICK_ADD);
        }
    }

    private void setTree(TreeItem<Catalog> items, CatalogMapper mapper) {
        ArrayList<Catalog> child = new ArrayList<>(mapper.getCatalogByRoot(items.getValue().getId()));
        if (child.size() != 0) {
            for (Catalog catalog : child) {
                TreeItem<Catalog> treeChild = new TreeItem(catalog);
                items.getChildren().add(treeChild);
                setTree(treeChild, mapper);
            }
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext ctx) {
        super.setApplicationContext(ctx);

        CatalogMapper mapper = ctx.getBean("catalogMapper", CatalogMapper.class);
        rootCatalog = new Catalog();
        rootCatalog.setId("0");
        rootCatalog.setName("ББК");
        TreeItem<Catalog> root = new TreeItem<>(rootCatalog);
        catalogTree.setRoot(root);

        for (Catalog catalog : mapper.getRootCatalog()) {
            TreeItem<Catalog> items = new TreeItem<>(catalog);
            root.getChildren().add(items);
            setTree(items, mapper);
        }
        catalogTree.refresh();
    }
}
