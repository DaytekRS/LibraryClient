package by.pdu.library.windows.menu.employeMenu.author;

import by.pdu.library.domain.Author;
import by.pdu.library.domain.Language;
import by.pdu.library.mapper.AuthorMapper;
import by.pdu.library.utils.AlertWindow;
import by.pdu.library.utils.support.LoadFXML;
import by.pdu.library.windows.Window;
import by.pdu.library.windows.menu.employeMenu.TabController;
import by.pdu.library.windows.menu.employeMenu.author.update.UpdateController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class AuthorController extends TabController {
    TableView tableView;

    public AuthorController(TableView view) {
        super(view);
        this.tableView = view;
        ObservableList<TableColumn> columns = tableView.getColumns();
        columns.get(0).setCellValueFactory(new PropertyValueFactory<Language, Long>("id"));
        columns.get(1).setCellValueFactory(new PropertyValueFactory<Language, String>("name"));
    }

    @Override
    public void updateView() {
        AuthorMapper mapper = ctx.getBean("authorMapper", AuthorMapper.class);
        ObservableList<Author> authors = FXCollections.observableArrayList(mapper.getAuthor());
        tableView.setItems(authors);
        tableView.refresh();
    }

    @Override
    public void update() {
        Object obj = tableView.getSelectionModel().getSelectedItem();
        if (obj == null) {
            AlertWindow.errorAlert("Нет выбранного элемента");
            return;
        }
        Author author = (Author) obj;
        LoadFXML loader = ctx.getBean("loader", LoadFXML.class);
        Stage stage = new Stage();

        UpdateController controller = (UpdateController) loader.loadModal("windows/menu/employeMenu/author/update/update.fxml",
                "Редактировать автора",
                stage,
                this.stage,
                275,
                130);

        controller.setAuthor(author);
        stage.showAndWait();
        Object data = stage.getUserData();
        if (data != null && (Integer) data == Window.CLICK_EDIT)
            updateView();
    }

    @Override
    public void add() {
        Stage stage = modalWindow("windows/menu/employeMenu/author/add/add.fxml", "Добавить автора", 275, 130);
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
        Author author = (Author) obj;
        AuthorMapper mapper = ctx.getBean("authorMapper", AuthorMapper.class);
        mapper.removeAuthor(author.getId());
        commit();
        updateView();
    }
}
