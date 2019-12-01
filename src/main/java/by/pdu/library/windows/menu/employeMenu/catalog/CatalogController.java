package by.pdu.library.windows.menu.employeMenu.catalog;

import by.pdu.library.domain.Catalog;
import by.pdu.library.mapper.CatalogMapper;
import by.pdu.library.utils.AlertWindow;
import by.pdu.library.utils.CatalogSupport;
import by.pdu.library.utils.support.LoadFXML;
import by.pdu.library.windows.menu.employeMenu.TabController;
import by.pdu.library.windows.menu.employeMenu.catalog.add.AddController;
import by.pdu.library.windows.menu.employeMenu.catalog.update.UpdateController;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.stage.Stage;

public class CatalogController extends TabController {
    private TreeView<Catalog> treeView;
    private Catalog rootCatalog;


    public CatalogController(TreeView view) {
        super(view);
        this.treeView = view;
        rootCatalog = new Catalog();
        rootCatalog.setId("0");
        rootCatalog.setName("ББК");
    }

    @Override
    public void updateView() {
        CatalogSupport.refreshCatalog(ctx, treeView, rootCatalog);
    }

    private void selectItem(Catalog catalog) {
        TreeItem<Catalog> root = null;
        if (catalog.getRoot() == null) root = treeView.getRoot();
        else root = CatalogSupport.findRootItem(treeView.getRoot(), catalog);
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
            TreeItem<Catalog> root = CatalogSupport.findRootItem(treeView.getRoot(), item);
            if (root == null) root = treeView.getRoot();
            root.getChildren().remove(treeView.getSelectionModel().getSelectedItem());
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
                TreeItem<Catalog> root = CatalogSupport.findRootItem(treeView.getRoot(), obj.getValue());
                if (root == null) root = treeView.getRoot();
                root.getChildren().remove(obj);
                selectItem((Catalog) data);
            }
        }
    }

}
