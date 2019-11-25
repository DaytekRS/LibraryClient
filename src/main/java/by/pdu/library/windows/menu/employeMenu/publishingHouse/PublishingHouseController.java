package by.pdu.library.windows.menu.employeMenu.publishingHouse;

import by.pdu.library.domain.PublishingHouse;
import by.pdu.library.mapper.PublishingHouseMapper;
import by.pdu.library.utils.AlertWindow;
import by.pdu.library.utils.support.LoadFXML;
import by.pdu.library.windows.Window;
import by.pdu.library.windows.menu.employeMenu.TabController;
import by.pdu.library.windows.menu.employeMenu.publishingHouse.update.UpdateController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class PublishingHouseController extends TabController {
    private TableView tableView;

    public PublishingHouseController(TableView tableView) {
        super(tableView);
        this.tableView = tableView;
    }

    public void add() {
        Stage stage = modalWindow("windows/menu/employeMenu/publishingHouse/add/add.fxml", "Добавить издательство", 275, 130);
        Object data = stage.getUserData();
        if (data != null && (Integer) data == Window.CLICK_ADD) updateView();
    }

    public void remove() {
        Object obj = tableView.getSelectionModel().getSelectedItem();
        if (obj == null) {
            AlertWindow.errorAlert("Нет выбранного элемента");
            return;
        }
        PublishingHouse publishingHouse = (PublishingHouse) obj;
        PublishingHouseMapper mapper = ctx.getBean("publishingHouseMapper", PublishingHouseMapper.class);
        mapper.removePublishingHouse(publishingHouse.getId());
        commit();
        updateView();
    }

    public void update() {
        Object obj = tableView.getSelectionModel().getSelectedItem();
        if (obj == null) {
            AlertWindow.errorAlert("Нет выбранного элемента");
            return;
        }
        PublishingHouse publishingHouse = (PublishingHouse) obj;
        LoadFXML loader = ctx.getBean("loader", LoadFXML.class);
        Stage stage = new Stage();

        UpdateController controller = (UpdateController) loader.loadModal("windows/menu/employeMenu/publishingHouse/update/update.fxml",
                "Редактировать идателя",
                stage,
                this.stage,
                275,
                130);

        controller.setPublishingHouse(publishingHouse);
        stage.showAndWait();
        Object data = stage.getUserData();
        if (data != null && (Integer) data == Window.CLICK_EDIT)
            updateView();
    }

    public void updateView() {
        PublishingHouseMapper mapper = ctx.getBean("publishingHouseMapper", PublishingHouseMapper.class);
        ObservableList<PublishingHouse> publishingHouses = FXCollections.observableArrayList(mapper.getPublishingHouse());
        tableView.setItems(publishingHouses);
        tableView.refresh();
    }
}
