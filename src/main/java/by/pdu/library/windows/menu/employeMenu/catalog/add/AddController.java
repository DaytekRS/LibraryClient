package by.pdu.library.windows.menu.employeMenu.catalog.add;

import by.pdu.library.domain.Catalog;
import by.pdu.library.mapper.CatalogMapper;
import by.pdu.library.utils.AlertWindow;
import by.pdu.library.utils.support.StringSupport;
import by.pdu.library.windows.SupportWindow;
import by.pdu.library.windows.Window;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;


public class AddController extends SupportWindow {
    @FXML
    private TextField idField, catalogField;

    @FXML
    private TreeView<Catalog> treeView;

    private Catalog rootCatalog;

    private Catalog addCatalog;

    @FXML
    public void initialize() {
        idField.setFocusTraversable(false);
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

    protected void close(Catalog catalog) {
        super.close(Window.CLICK_ADD);
        stage.setUserData(catalog);
    }

    @FXML
    private void selectItem() {
        if (!treeView.getSelectionModel().getSelectedItem().getValue().getId().equals("0"))
            idField.setText(treeView.getSelectionModel().getSelectedItem().getValue().getId());
    }

    public void setTreeView(TreeView<Catalog> treeView) {
        this.treeView.setRoot(treeView.getRoot());
        this.treeView.setSelectionModel(treeView.getSelectionModel());
        selectItem();
        treeView.refresh();
    }
}
