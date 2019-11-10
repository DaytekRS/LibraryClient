package by.pdu.library.windows.menu.adminMenu.readingRoom;

import by.pdu.library.domain.ReadingRoom;
import by.pdu.library.mapper.ReadingRoomMapper;
import by.pdu.library.utils.support.ApplicationContext;
import by.pdu.library.windows.Window;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ReadingRoomController extends Window {
    @FXML
    private TableView readingRoomTable;

    @Override
    public void setApplicationContext(ApplicationContext ctx) {
        super.setApplicationContext(ctx);
        ReadingRoomMapper readingRoomMapper = ctx.getBean("readingRoomMapper",ReadingRoomMapper.class);
        ObservableList<ReadingRoom> readingRooms = FXCollections.observableArrayList(readingRoomMapper.getReadingRoom());
        readingRoomTable.setItems(readingRooms);
        ObservableList<TableColumn> columns = readingRoomTable.getColumns();

        columns.get(0).setCellValueFactory(new PropertyValueFactory<ReadingRoom, Integer>("id"));
        columns.get(1).setCellValueFactory(new PropertyValueFactory<ReadingRoom, String>("name"));
        columns.get(2).setCellValueFactory(new PropertyValueFactory<ReadingRoom, String>("address"));

    }
}
