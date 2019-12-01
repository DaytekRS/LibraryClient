package by.pdu.library.utils;

import by.pdu.library.domain.Catalog;
import by.pdu.library.mapper.CatalogMapper;
import by.pdu.library.utils.support.ApplicationContext;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

import java.util.ArrayList;

public class CatalogSupport {
    public static Catalog initCatalog(ApplicationContext ctx, TreeView<Catalog> catalogTree) {
        Catalog rootCatalog = new Catalog();
        rootCatalog.setId("0");
        rootCatalog.setName("ББК");
        refreshCatalog(ctx, catalogTree, rootCatalog);
        return rootCatalog;
    }

    public static void refreshCatalog(ApplicationContext ctx, TreeView<Catalog> catalogTree, Catalog rootCatalog) {
        CatalogMapper mapper = ctx.getBean("catalogMapper", CatalogMapper.class);
        TreeItem<Catalog> root = new TreeItem<>(rootCatalog);
        catalogTree.setRoot(root);

        for (Catalog catalog : mapper.getRootCatalog()) {
            TreeItem<Catalog> items = new TreeItem<>(catalog);
            root.getChildren().add(items);
            setTree(items, mapper);
        }
        catalogTree.refresh();
    }

    private static void setTree(TreeItem<Catalog> items, CatalogMapper mapper) {
        ArrayList<Catalog> child = new ArrayList<>(mapper.getCatalogByRoot(items.getValue().getId()));
        if (child.size() != 0) {
            for (Catalog catalog : child) {
                TreeItem<Catalog> treeChild = new TreeItem(catalog);
                items.getChildren().add(treeChild);
                setTree(treeChild, mapper);
            }
        }
    }

    public static TreeItem<Catalog> findRootItem(TreeItem<Catalog> root, Catalog catalog) {
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
}
