package by.pdu.library.windows.menu.employeMenu.catalog.update;

import by.pdu.library.domain.Catalog;
import by.pdu.library.mapper.CatalogMapper;
import by.pdu.library.utils.AlertWindow;
import by.pdu.library.utils.CatalogSupport;
import by.pdu.library.utils.support.ApplicationContext;
import by.pdu.library.utils.support.StringSupport;
import by.pdu.library.windows.SupportWindow;
import by.pdu.library.windows.Window;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;


public class UpdateController extends SupportWindow {
    @FXML
    private TextField idField, catalogField;

    @FXML
    private TreeView<Catalog> treeView;

    private Catalog rootCatalog;

    private Catalog updateCatalog;

    @FXML
    public void initialize() {
        idField.setFocusTraversable(false);
    }


    @FXML
    private void update() {
        try {
            String id = StringSupport.replaceSpaces(idField.getText());
            String catalog = StringSupport.replaceSpaces(catalogField.getText());
            if (!id.equals("") && !catalog.equals("")) {
                CatalogMapper mapper = ctx.getBean("catalogMapper", CatalogMapper.class);
                TreeItem<Catalog> item = treeView.getSelectionModel().getSelectedItem();
                Catalog newCatalog = new Catalog();
                newCatalog.setName(catalog);
                newCatalog.setId(id);
                if (item == null || item.getValue() == rootCatalog)
                    mapper.updateRoot(updateCatalog.getId(), id, catalog);
                else {
                    Catalog root = item.getValue();
                    mapper.updateCatalog(updateCatalog.getId(), id, catalog, root.getId());
                    newCatalog.setRoot(root);
                }
                close(newCatalog);
            } else {
                AlertWindow.errorAlert("Не должно быть пустых полей");
            }
        } catch (Exception ex) {
            AlertWindow.checkException(ex.getMessage());
            ex.printStackTrace();
        }
    }


    @Override
    public void setApplicationContext(ApplicationContext ctx) {
        super.setApplicationContext(ctx);
        rootCatalog = CatalogSupport.initCatalog(ctx,treeView);
    }

    private void selectItem(TreeItem<Catalog> items) {
        if (items.getValue().getId().equals(updateCatalog.getRoot().getId())) {
            treeView.getSelectionModel().select(items);
        } else {
            for (TreeItem<Catalog> item : items.getChildren()) {
                selectItem(item);
            }
        }
    }

    public void setUpdateCatalog(Catalog updateCatalog) {
        this.updateCatalog = updateCatalog;
        idField.setText(updateCatalog.getId());
        catalogField.setText(updateCatalog.getName());

        if (updateCatalog.getRoot() != null) {
            selectItem(treeView.getRoot());
        }


        TreeItem<Catalog> root = null;
        if (updateCatalog.getRoot() == null) root = treeView.getRoot();
        else root = CatalogSupport.findRootItem(treeView.getRoot(), updateCatalog);
        System.out.println(root);
        TreeItem<Catalog> remove = null;
        if (root != null) {
            for (TreeItem<Catalog> find : root.getChildren()) {
                if (find.getValue().getId().equals(updateCatalog.getId())) remove = find;
            }
            root.getChildren().remove(remove);
        }
        treeView.refresh();
    }

    protected void close(Catalog catalog) {
        super.close(Window.CLICK_EDIT);
        stage.setUserData(catalog);
    }
}
