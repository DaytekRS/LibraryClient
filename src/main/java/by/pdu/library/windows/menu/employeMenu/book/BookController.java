package by.pdu.library.windows.menu.employeMenu.book;

import by.pdu.library.domain.Book;
import by.pdu.library.domain.Edition;
import by.pdu.library.domain.Instance;
import by.pdu.library.mapper.BookMapper;
import by.pdu.library.utils.AlertWindow;
import by.pdu.library.utils.support.LoadFXML;
import by.pdu.library.windows.menu.employeMenu.TabController;
import by.pdu.library.windows.menu.employeMenu.book.update.UpdateController;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Comparator;

public class BookController extends TabController {
    private TreeItem<Edition> root;

    @FXML
    TreeTableView view;

    public BookController(TreeTableView view) {
        super(view);
        this.view = view;
    }

    @Override
    public void updateView() {
        BookMapper mapper = ctx.getBean("bookMapper", BookMapper.class);
        ArrayList<Edition> editions = new ArrayList<>(mapper.getBook());
        editions.addAll(mapper.getPeriodic());
        editions.sort(Comparator.comparing(Edition::getId));
        ObservableList<TreeTableColumn> columns = view.getColumns();
        columns.get(0).setCellValueFactory(new TreeItemPropertyValueFactory<Edition, String>("name"));
        columns.get(1).setCellValueFactory(new TreeItemPropertyValueFactory<Edition, String>("language"));
        columns.get(2).setCellValueFactory(new TreeItemPropertyValueFactory<Edition, String>("catalog"));
        columns.get(3).setCellValueFactory(new TreeItemPropertyValueFactory<Instance, Long>("numberInstance"));
        Book rootEdition = new Book();
        rootEdition.setName("Книги");
        root = new TreeItem<>(rootEdition);
        for (Edition edition : editions) {
            TreeItem<Edition> children = new TreeItem<>(edition);
            root.getChildren().add(children);
        }

        view.setRoot(root);
    }

    @Override
    public void update() {
        Object obj = view.getSelectionModel().getSelectedItem();
        if (obj == null) {
            AlertWindow.errorAlert("Нет выбранного элемента");
            return;
        }
        Edition edition = (Edition) ((TreeItem) obj).getValue();

        if (edition.getClass().equals(Instance.class)) {
            AlertWindow.errorAlert("Нельзя выбрать экземпляр");
            return;
        }

        LoadFXML loader = ctx.getBean("loader", LoadFXML.class);
        Stage stage = new Stage();

        UpdateController controller = (UpdateController) loader.loadModal("windows/menu/employeMenu/book/update/update.fxml",
                "Редактировать статью",
                stage,
                this.stage,
                860,
                480);

        controller.setEdition(edition);
        stage.showAndWait();
        Object data = stage.getUserData();
        if (data != null) {
            root.getChildren().remove(view.getSelectionModel().getSelectedItem());
            edition = (Edition) data;
            root.getChildren().add(new TreeItem<>(edition));
            view.refresh();
        }
    }

    @Override
    public void add() {
        Stage stage = modalWindow("windows/menu/employeMenu/book/add/add.fxml", "Добавить книгу", 860, 480);
        Object data = stage.getUserData();
        if (data != null) {
            Edition edition = (Edition) data;
            root.getChildren().add(new TreeItem<>(edition));
            view.refresh();
            //updateView();
        }
        //if (data != null && (Integer) data == Window.CLICK_ADD) updateView();
    }

    @Override
    public void remove() {
        Object obj = view.getSelectionModel().getSelectedItem();
        if (obj == null) {
            AlertWindow.errorAlert("Нет выбранного элемента");
            return;
        }
        Edition edition = (Edition) ((TreeItem) obj).getValue();
        BookMapper mapper = ctx.getBean("bookMapper", BookMapper.class);
        mapper.removeEdition(edition.getId());
        commit();
        root.getChildren().remove(view.getSelectionModel().getSelectedItem());
        view.refresh();
    }
}
