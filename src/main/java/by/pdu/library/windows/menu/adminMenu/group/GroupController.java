package by.pdu.library.windows.menu.adminMenu.group;

import by.pdu.library.domain.Group;
import by.pdu.library.mapper.GroupMapper;
import by.pdu.library.utils.AlertWindow;
import by.pdu.library.utils.support.ApplicationContext;
import by.pdu.library.utils.support.LoadFXML;
import by.pdu.library.windows.Window;
import by.pdu.library.windows.menu.adminMenu.AdminWindow;
import by.pdu.library.windows.menu.adminMenu.group.update.UpdateController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class GroupController extends AdminWindow {
    @FXML
    private TableView groupTable;

    @Override
    public void setApplicationContext(ApplicationContext ctx) {
        super.setApplicationContext(ctx);
        update();
        ObservableList<TableColumn> columns = groupTable.getColumns();
        columns.get(0).setCellValueFactory(new PropertyValueFactory<Group, String>("name"));
        columns.get(1).setCellValueFactory(new PropertyValueFactory<Group, String>("faculty"));
    }

    @FXML
    private void addGroup() {
        Stage stage = modalWindow("windows/menu/adminMenu/group/add/add.fxml", "Добавить группу", 295, 165);
        Object data = stage.getUserData();
        System.out.println(data);
        if (data != null && (Integer) data == Window.CLICK_ADD) update();
    }

    @FXML
    private void removeGroup() {
        Object obj = groupTable.getSelectionModel().getSelectedItem();
        if (obj == null) {
            AlertWindow.errorAlert("Нет выбранного элемента");
            return;
        }
        Group group = (Group) obj;
        GroupMapper mapper = ctx.getBean("groupMapper", GroupMapper.class);
        mapper.dropGroup(group.getId());
        commit();
        update();
    }

    @FXML
    private void updateGroup() {
        Object obj = groupTable.getSelectionModel().getSelectedItem();
        if (obj == null) {
            AlertWindow.errorAlert("Нет выбранного элемента");
            return;
        }
        Group group = (Group) obj;
        LoadFXML loader = ctx.getBean("loader", LoadFXML.class);
        Stage stage = new Stage();

        UpdateController controller = (UpdateController) loader.loadModal("windows/menu/adminMenu/group/update/update.fxml",
                "Редактировать группу",
                stage,
                this.stage,
                295,
                165);

        controller.setGroup(group);
        stage.showAndWait();
        Object data = stage.getUserData();
        if (data != null && (Integer) data == Window.CLICK_EDIT)
            update();
    }

    private void update() {
        GroupMapper groupMapper = ctx.getBean("groupMapper", GroupMapper.class);
        ObservableList<Group> groups = FXCollections.observableArrayList(groupMapper.getGroup());
        groupTable.setItems(groups);
        groupTable.refresh();
    }


}
