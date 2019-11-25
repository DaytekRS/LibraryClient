package by.pdu.library.windows.menu.employeMenu.language;

import by.pdu.library.domain.Language;
import by.pdu.library.mapper.LanguageMapper;
import by.pdu.library.utils.AlertWindow;
import by.pdu.library.utils.support.LoadFXML;
import by.pdu.library.windows.Window;
import by.pdu.library.windows.menu.employeMenu.TabController;
import by.pdu.library.windows.menu.employeMenu.language.update.UpdateController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class LanguageController extends TabController {
    TableView tableView;

    public LanguageController(TableView view) {
        super(view);
        this.tableView = view;
        ObservableList<TableColumn> columns = tableView.getColumns();
        columns.get(0).setCellValueFactory(new PropertyValueFactory<Language, Long>("id"));
        columns.get(1).setCellValueFactory(new PropertyValueFactory<Language, String>("name"));
    }

    @Override
    public void updateView() {
        LanguageMapper mapper = ctx.getBean("languageMapper", LanguageMapper.class);
        ObservableList<Language> languages = FXCollections.observableArrayList(mapper.getLanguage());
        tableView.setItems(languages);
        tableView.refresh();
    }

    @Override
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
    }
}
