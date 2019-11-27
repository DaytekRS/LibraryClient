package by.pdu.library.windows.menu.employeMenu.catalog;

import by.pdu.library.domain.Catalog;
import by.pdu.library.mapper.CatalogMapper;
import by.pdu.library.windows.menu.employeMenu.TabController;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

import java.util.ArrayList;

public class CatalogController extends TabController {
    TreeView treeView;
    TreeItem<Catalog> root;

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

    public CatalogController(TreeView view) {
        super(view);
        this.treeView = view;
        Catalog rootCatalog = new Catalog();
        rootCatalog.setId("0");
        rootCatalog.setName("ББК");
        root = new TreeItem<>(rootCatalog);
        treeView.setRoot(root);
    }

    @Override
    public void updateView() {
        CatalogMapper mapper = ctx.getBean("catalogMapper", CatalogMapper.class);
        for (Catalog catalog : mapper.getRootCatalog()) {
            TreeItem<Catalog> items = new TreeItem<>(catalog);
            root.getChildren().add(items);
            setTree(items, mapper);
        }
    }

    @Override
    public void update() {

    }

    @Override
    public void add() {

    }

    @Override
    public void remove() {

    }

   /* @Override
    public void update() {
        Object obj = tableView.getSelectionModel().getSelectedItem();
        if (obj == null) {
            AlertWindow.errorAlert("Нет выбранного элемента");
            return;
        }
        Language language = (Language) obj;
        LoadFXML loader = ctx.getBean("loader", LoadFXML.class);
        Stage stage = new Stage();

        UpdateController controller = (UpdateController) loader.loadModal("windows/menu/employeMenu/language/update/update.fxml",
                "Редактировать язык",
                stage,
                this.stage,
                275,
                130);

        controller.setLanguage(language);
        stage.showAndWait();
        Object data = stage.getUserData();
        if (data != null && (Integer) data == Window.CLICK_EDIT)
            updateView();
    }

    @Override
    public void add() {
        Stage stage = modalWindow("windows/menu/employeMenu/language/add/add.fxml", "Добавить язык", 275, 130);
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
        Language language = (Language) obj;
        LanguageMapper mapper = ctx.getBean("languageMapper", LanguageMapper.class);
        mapper.removeLanguage(language.getId());
        commit();
        updateView();
    }*/
}
