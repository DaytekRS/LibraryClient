package by.pdu.library.windows.menu.adminMenu.group;

import by.pdu.library.domain.Department;
import by.pdu.library.domain.Group;
import by.pdu.library.mapper.GroupMapper;
import by.pdu.library.utils.support.ApplicationContext;
import by.pdu.library.windows.Window;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class GroupController extends Window {
    @FXML
    private TableView groupTable;

    @Override
    public void setApplicationContext(ApplicationContext ctx) {
        super.setApplicationContext(ctx);
        GroupMapper groupMapper = ctx.getBean("groupMapper",GroupMapper.class);
        ObservableList<Group> groups = FXCollections.observableArrayList(groupMapper.getGroup());
        groupTable.setItems(groups);
        ObservableList<TableColumn> columns = groupTable.getColumns();
        columns.get(0).setCellValueFactory(new PropertyValueFactory<Group, String>("name"));
        columns.get(1).setCellValueFactory(new PropertyValueFactory<Group, String>("faculty"));
    }
}
