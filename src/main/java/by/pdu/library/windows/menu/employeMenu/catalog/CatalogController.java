package by.pdu.library.windows.menu.employeMenu.catalog;

import by.pdu.library.domain.Catalog;
import by.pdu.library.mapper.CatalogMapper;
import by.pdu.library.utils.AlertWindow;
import by.pdu.library.utils.support.LoadFXML;
import by.pdu.library.windows.menu.employeMenu.TabController;
import by.pdu.library.windows.menu.employeMenu.catalog.add.AddController;
import by.pdu.library.windows.menu.employeMenu.catalog.update.UpdateController;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.stage.Stage;

import java.util.ArrayList;

public class CatalogController extends TabController {
    private TreeView<Catalog> treeView;
    private Catalog rootCatalog;

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

    private TreeItem<Catalog> findRootItem(TreeItem<Catalog> root, Catalog catalog) {
        if (catalog.getRoot() == null) return null;
        if (root.getValue().getId().equals(catalog.getRoot().getId())) {
            return root;
        } else {
            for (TreeItem<Catalog> item : root.getChildren()) {
                TreeItem<Catalog> find = findRootItem(item, catalog);
                if (find != null) return find;
            }
        }
        return null;
    }

    public CatalogController(TreeView view) {
        super(view);
        this.treeView = view;
        rootCatalog = new Catalog();
        rootCatalog.setId("0");
        rootCatalog.setName("ББК");
    }

    @Override
    public void updateView() {
        CatalogMapper mapper = ctx.getBean("catalogMapper", CatalogMapper.class);
        TreeItem<Catalog> root = new TreeItem<>(rootCatalog);
        treeView.setRoot(root);
        for (Catalog catalog : mapper.getRootCatalog()) {
            TreeItem<Catalog> items = new TreeItem<>(catalog);
            root.getChildren().add(items);
            setTree(items, mapper);
        }
        treeView.refresh();
    }

    private void selectItem(Catalog catalog) {
        TreeItem<Catalog> root = null;
        if (catalog.getRoot() == null) root = treeView.getRoot();
        else root = findRootItem(treeView.getRoot(), catalog);
        if (root != null) {
            TreeItem<Catalog> item = new TreeItem<>(catalog);
            root.getChildren().add(item);
            treeView.getSelectionModel().select(item);
        }
        treeView.refresh();
    }

    @Override
    public void add() {
        LoadFXML loader = ctx.getBean("loader", LoadFXML.class);
        Stage stage = new Stage();

        AddController controller = (AddController) loader.loadModal("windows/menu/employeMenu/catalog/add/add.fxml",
                "Добавить каталог",
                stage,
                this.stage,
                600,
                400);

        controller.setTreeView(treeView);
        stage.showAndWait();
        Object data = stage.getUserData();
        if (data != null) {
            selectItem((Catalog) data);
        }
    }

    @Override
    public void remove() {
        if (treeView.getSelectionModel().getSelectedItem() == null) {
            AlertWindow.errorAlert("Нет выбранного элемента");
            return;
        }
        Catalog item = treeView.getSelectionModel().getSelectedItem().getValue();
        if (item == rootCatalog) {
            AlertWindow.errorAlert("Нельзя удалить выбранный элемент");
        } else {
            CatalogMapper mapper = ctx.getBean("catalogMapper", CatalogMapper.class);
            mapper.removeCatalog(item.getId());
            commit();
            TreeItem<Catalog> root = findRootItem(treeView.getRoot(), item);
            if (root == null) root = treeView.getRoot();
            root.getChildren().remove(treeView.getSelectionModel().getSelectedItem());
            /*TreeItem<Catalog> remove = null;
            for (TreeItem<Catalog> find : root.getChildren()) {
                if (find.getValue() == item) remove = find;
            }
            root.getChildren().remove(remove);*/
            treeView.refresh();

        }
    }

    public void update() {
        TreeItem<Catalog> obj = treeView.getSelectionModel().getSelectedItem();
        if (obj == null) {
            AlertWindow.errorAlert("Нет выбранного элемента");
            return;
        }
        if (obj.getValue() == rootCatalog) {
            AlertWindow.errorAlert("Нельзя редактировать выбранный элемент");
        } else {
            Catalog catalog = obj.getValue();
            LoadFXML loader = ctx.getBean("loader", LoadFXML.class);
            Stage stage = new Stage();

            UpdateController controller = (UpdateController) loader.loadModal("windows/menu/employeMenu/catalog/update/update.fxml",
                    "Редактировать каталог",
                    stage,
                    this.stage,
                    600,
                    400);

            controller.setUpdateCatalog(catalog);
            stage.showAndWait();
            Object data = stage.getUserData();
            if (data != null) {
                TreeItem<Catalog> root = findRootItem(treeView.getRoot(), obj.getValue());
                if (root == null) root = treeView.getRoot();
                root.getChildren().remove(obj);
                selectItem((Catalog) data);
            }
        }
    }

}
