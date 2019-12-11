package by.pdu.library.windows.menu.employeMenu.book;

import by.pdu.library.domain.*;
import by.pdu.library.mapper.BookMapper;
import by.pdu.library.utils.AlertWindow;
import by.pdu.library.utils.support.LoadFXML;
import by.pdu.library.windows.menu.employeMenu.TabController;
import by.pdu.library.windows.menu.employeMenu.book.addInstance.AddInstance;
import by.pdu.library.windows.menu.employeMenu.book.takeInstance.TakeController;
import by.pdu.library.windows.menu.employeMenu.book.takeOrder.TakeOrderController;
import by.pdu.library.windows.menu.employeMenu.book.update.UpdateController;
import by.pdu.library.windows.menu.employeMenu.book.updateInstance.UpdateInstance;
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
        ObservableList<TreeTableColumn> columns = view.getColumns();
        columns.get(0).setCellValueFactory(new TreeItemPropertyValueFactory<Edition, String>("name"));
        columns.get(1).setCellValueFactory(new TreeItemPropertyValueFactory<Edition, String>("language"));
        columns.get(2).setCellValueFactory(new TreeItemPropertyValueFactory<Edition, String>("catalog"));
        columns.get(3).setCellValueFactory(new TreeItemPropertyValueFactory<Instance, Long>("numberInstance"));
    }

    @Override
    public void updateView() {
        BookMapper mapper = ctx.getBean("bookMapper", BookMapper.class);
        Employe user = ctx.getBean("user",Employe.class);
        ArrayList<Edition> editions = new ArrayList<>(mapper.getBook());
        editions.addAll(mapper.getPeriodic());
        editions.sort(Comparator.comparing(Edition::getId));

        Book rootEdition = new Book();
        rootEdition.setName("Книги");
        root = new TreeItem<>(rootEdition);
        for (Edition edition : editions) {
            TreeItem<Edition> children = new TreeItem<>(edition);
            root.getChildren().add(children);
            for (Instance instance: mapper.getInstance(edition.getId(),user.getRoom().getId())){
                TreeItem<Edition> childrenInstance = new TreeItem<>(instance);
                children.getChildren().add(childrenInstance);
            }
        }

        view.setRoot(root);
    }



    @FXML
    public void takeBook(){
        TreeItem obj = (TreeItem)view.getSelectionModel().getSelectedItem();
        if (obj == null) {
            AlertWindow.errorAlert("Нет выбранного элемента");
            return;
        }
        Edition instance = (Edition) obj.getValue();
        if (instance.getClass().equals(Instance.class)) {
            LoadFXML loader = ctx.getBean("loader", LoadFXML.class);
            Stage stage = new Stage();

            TakeController controller = (TakeController) loader.loadModal("windows/menu/employeMenu/book/takeInstance/take.fxml",
                    "Выдать экземпляр",
                    stage,
                    this.stage);

            controller.setInstance((Instance) instance);
            stage.showAndWait();
            Object data = stage.getUserData();
            if (data != null) {
                view.refresh();
                // updateView();
            }
        }else{
            AlertWindow.errorAlert("Нет правильно выбранного элемента");
        }
    }

    @FXML
    public void takeOrder(){
            LoadFXML loader = ctx.getBean("loader", LoadFXML.class);
            Stage stage = new Stage();

            TakeOrderController controller = (TakeOrderController) loader.loadModal("windows/menu/employeMenu/book/takeOrder/take.fxml",
                    "Выдать экземпляр",
                    stage,
                    this.stage);

            stage.showAndWait();
            Object data = stage.getUserData();
            if (data != null) {
                updateView();
            }
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
                "Редактировать книгу",
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

    public void addInstance(){
        TreeItem obj = (TreeItem)view.getSelectionModel().getSelectedItem();
        if (obj == null) {
            AlertWindow.errorAlert("Нет выбранного элемента");
            return;
        }
        Edition edition = (Edition) obj.getValue();
        System.out.println(obj.getClass());
        if (edition.getClass().equals(Book.class) || edition.getClass().equals(Periodic.class) ) {
            LoadFXML loader = ctx.getBean("loader", LoadFXML.class);
            Stage stage = new Stage();

            AddInstance controller = (AddInstance) loader.loadModal("windows/menu/employeMenu/book/addInstance/addInstance.fxml",
                    "Добавить экземпляр",
                    stage,
                    this.stage);

            controller.setEdition(edition);
            stage.showAndWait();
            Object data = stage.getUserData();
            if (data != null) {
                TreeItem<Edition> childrenInstance = new TreeItem<>((Instance)data);
                obj.getChildren().add(childrenInstance);
                view.refresh();
               // updateView();
            }
        }else{
            AlertWindow.errorAlert("Нет правильно выбранного элемента");
        }
    }

    public void updateInstance(){
        TreeItem obj = (TreeItem)view.getSelectionModel().getSelectedItem();
        if (obj == null) {
            AlertWindow.errorAlert("Нет выбранного элемента");
            return;
        }
        Edition instance = (Edition) obj.getValue();
        System.out.println(obj.getClass());
        if (instance.getClass().equals(Instance.class)) {
            LoadFXML loader = ctx.getBean("loader", LoadFXML.class);
            Stage stage = new Stage();

            UpdateInstance controller = (UpdateInstance) loader.loadModal("windows/menu/employeMenu/book/updateInstance/updateInstance.fxml",
                    "Редактировать экземпляр",
                    stage,
                    this.stage);

            controller.setInstance((Instance) instance);
            stage.showAndWait();
            Object data = stage.getUserData();
            if (data != null) {
                view.refresh();
                // updateView();
            }
        }else{
            AlertWindow.errorAlert("Нет правильно выбранного элемента");
        }
    }

    @FXML
    public void  removeInstance() {
        TreeItem obj = (TreeItem)view.getSelectionModel().getSelectedItem();
        if (obj == null) {
            AlertWindow.errorAlert("Нет выбранного элемента");
            return;
        }
        Edition instance = (Edition) obj.getValue();
        if (instance.getClass().equals(Instance.class)) {
            try{
                BookMapper mapper = ctx.getBean("bookMapper",BookMapper.class);
                mapper.removeInstance(((Instance) instance).getNumberInstance());
                obj.getParent().getChildren().remove(obj);
                view.refresh();
                commit();
            }catch (Exception ex){
                AlertWindow.checkException(ex.getMessage());
            }
        }else{
            AlertWindow.errorAlert("Нет правильно выбранного элемента");
        }
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
