package by.pdu.library.windows.menu.adminMenu.readingRoom;

import by.pdu.library.domain.ReadingRoom;
import by.pdu.library.mapper.ReadingRoomMapper;
import by.pdu.library.utils.AlertWindow;
import by.pdu.library.utils.support.ApplicationContext;
import by.pdu.library.utils.support.LoadFXML;
import by.pdu.library.windows.Window;
import by.pdu.library.windows.menu.adminMenu.AdminWindow;
import by.pdu.library.windows.menu.adminMenu.readingRoom.update.UpdateController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ReadingRoomController extends AdminWindow {
    @FXML
    private TableView readingRoomTable;

    @Override
    public void setApplicationContext(ApplicationContext ctx) {
        super.setApplicationContext(ctx);
        init();
    }


    @FXML
    private void addReadingRoom() {
        Stage stage = modalWindow("windows/menu/adminMenu/readingRoom/add/add.fxml","Добавить читальный зал",295,160);
        Object data = stage.getUserData();
        if (data != null && (Integer) data == Window.CLICK_ADD) update();
    }

    @FXML
    private void removeReadingRoom() {
        Object obj = readingRoomTable.getSelectionModel().getSelectedItem();
        if (obj == null) {
            AlertWindow.ErrorAlert("Нет выбранного элемента");
            return;
        }
        ReadingRoom readingRoom = (ReadingRoom) obj;
        ReadingRoomMapper mapper = ctx.getBean("readingRoomMapper", ReadingRoomMapper.class);
        mapper.removeReadingRoom(readingRoom.getId());
        commit();
        update();
    }

    @FXML
    private void updateReadingRoom() {
        Object obj = readingRoomTable.getSelectionModel().getSelectedItem();
        if (obj == null) {
            AlertWindow.ErrorAlert("Нет выбранного элемента");
            return;
        }
        ReadingRoom readingRoom = (ReadingRoom) obj;
        LoadFXML loader = ctx.getBean("loader", LoadFXML.class);
        Stage stage = new Stage();

        UpdateController controller = (UpdateController) loader.loadModal("windows/menu/adminMenu/readingRoom/update/update.fxml",
                "Редактировать читальный зал",
                stage,
                this.stage,
                275,
                130);

        controller.setReadingRoom(readingRoom);
        stage.showAndWait();
        Object data = stage.getUserData();
        if (data != null && (Integer) data == Window.CLICK_EDIT)
            update();
    }

    private void update(){
        System.out.println("update");
        ReadingRoomMapper readingRoomMapper = ctx.getBean("readingRoomMapper", ReadingRoomMapper.class);
        ObservableList<ReadingRoom> readingRooms = FXCollections.observableArrayList(readingRoomMapper.getReadingRoom());
        readingRoomTable.setItems(readingRooms);
        readingRoomTable.refresh();
    }

    private void init(){
        update();
        ObservableList<TableColumn> columns = readingRoomTable.getColumns();
        columns.get(0).setCellValueFactory(new PropertyValueFactory<ReadingRoom, Integer>("id"));
        columns.get(1).setCellValueFactory(new PropertyValueFactory<ReadingRoom, String>("name"));
        columns.get(2).setCellValueFactory(new PropertyValueFactory<ReadingRoom, String>("address"));
    }
}
