package by.pdu.library.windows.menu.adminMenu.fine;

import by.pdu.library.domain.Faculty;
import by.pdu.library.domain.Fine;
import by.pdu.library.mapper.FineMapper;
import by.pdu.library.utils.AlertWindow;
import by.pdu.library.utils.support.ApplicationContext;
import by.pdu.library.utils.support.LoadFXML;
import by.pdu.library.windows.Window;
import by.pdu.library.windows.menu.adminMenu.AdminWindow;
import by.pdu.library.windows.menu.adminMenu.fine.update.UpdateController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class FineController extends AdminWindow {
    @FXML
    private TableView fineTable;


    @Override
    public void setApplicationContext(ApplicationContext ctx) {
        super.setApplicationContext(ctx);
        ObservableList<TableColumn> columns = fineTable.getColumns();
        columns.get(0).setCellValueFactory(new PropertyValueFactory<Fine, String>("name"));
        columns.get(1).setCellValueFactory(new PropertyValueFactory<Fine, Float>("price"));
        update();
    }

    @FXML
    private void addFine() {
        Stage stage = modalWindow("windows/menu/adminMenu/fine/add/add.fxml", "Добавить штраф", 270, 165);
        Object data = stage.getUserData();
        if (data != null && (Integer) data == Window.CLICK_ADD) update();
    }

    @FXML
    private void removeFine() {
        Object obj = fineTable.getSelectionModel().getSelectedItem();
        if (obj == null) {
            AlertWindow.ErrorAlert("Нет выбранного элемента");
            return;
        }
        Fine fine = (Fine) obj;
        FineMapper mapper = ctx.getBean("fineMapper", FineMapper.class);
        mapper.removeFine(fine.getId());
        commit();
        update();
    }

    @FXML
    private void updateFine() {
        Object obj = fineTable.getSelectionModel().getSelectedItem();
        if (obj == null) {
            AlertWindow.ErrorAlert("Нет выбранного элемента");
            return;
        }
        Fine fine = (Fine) obj;
        LoadFXML loader = ctx.getBean("loader", LoadFXML.class);
        Stage stage = new Stage();

        UpdateController controller = (UpdateController) loader.loadModal("windows/menu/adminMenu/fine/update/update.fxml",
                "Редактировать штраф",
                stage,
                this.stage,
                270,
                165);

        controller.setFine(fine);
        stage.showAndWait();
        Object data = stage.getUserData();
        if (data != null && (Integer) data == Window.CLICK_EDIT)
            update();
    }

    private void update() {
        FineMapper fineMapper = ctx.getBean("fineMapper", FineMapper.class);
        ObservableList<Fine> fines = FXCollections.observableArrayList(fineMapper.getFine());
        fineTable.setItems(fines);
        fineTable.refresh();
    }

}
