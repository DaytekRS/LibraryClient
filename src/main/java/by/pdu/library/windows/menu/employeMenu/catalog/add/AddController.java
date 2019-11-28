package by.pdu.library.windows.menu.employeMenu.catalog.add;

import by.pdu.library.domain.Catalog;
import by.pdu.library.mapper.CatalogMapper;
import by.pdu.library.utils.AlertWindow;
import by.pdu.library.utils.support.ApplicationContext;
import by.pdu.library.utils.support.StringSupport;
import by.pdu.library.windows.SupportWindow;
import by.pdu.library.windows.Window;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

import java.util.ArrayList;


public class AddController extends SupportWindow {
    @FXML
    private TextField idField, catalogField;

    @FXML
    private TreeView<Catalog> treeView;

    private Catalog rootCatalog;

    @FXML
    public void initialize() {
        idField.setFocusTraversable(false);
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


    @FXML
    private void add() {
        try {
            String id = StringSupport.replaceSpaces(idField.getText());
            String catalog = StringSupport.replaceSpaces(catalogField.getText());
            if (!id.equals("") && !catalog.equals("")) {
                CatalogMapper mapper = ctx.getBean("catalogMapper", CatalogMapper.class);
                TreeItem<Catalog> item = treeView.getSelectionModel().getSelectedItem();
                Catalog newCatalog = new Catalog();
                newCatalog.setName(catalog);
                newCatalog.setId(id);
                if (item == null || item.getValue() == rootCatalog) {
                    mapper.insertRoot(id, catalog);
                } else {
                    Catalog root = item.getValue();
                    mapper.insertCatalog(id, catalog, root.getId());
                    newCatalog.setRoot(root);
                }
                close(newCatalog);
            } else {
                AlertWindow.errorAlert("Не должно быть пустых полей");
            }
        } catch (Exception ex) {
            AlertWindow.checkException(ex.getMessage());
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
        treeView.setRoot(root);
        for (Catalog catalog : mapper.getRootCatalog()) {
            TreeItem<Catalog> items = new TreeItem<>(catalog);
            root.getChildren().add(items);
            setTree(items, mapper);
        }
        treeView.refresh();
    }

    protected void close(Catalog catalog) {
        super.close(Window.CLICK_ADD);
        stage.setUserData(catalog);
    }
}
