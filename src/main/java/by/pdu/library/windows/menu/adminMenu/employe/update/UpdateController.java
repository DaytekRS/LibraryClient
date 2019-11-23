package by.pdu.library.windows.menu.adminMenu.employe.update;

import by.pdu.library.domain.Employe;
import by.pdu.library.domain.ReadingRoom;
import by.pdu.library.mapper.EmployeMapper;
import by.pdu.library.mapper.ReadingRoomMapper;
import by.pdu.library.utils.AlertWindow;
import by.pdu.library.utils.support.ApplicationContext;
import by.pdu.library.utils.support.StringSupport;
import by.pdu.library.windows.SupportWindow;
import by.pdu.library.windows.Window;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;


public class UpdateController extends SupportWindow {
    private Employe employe;

    @FXML
    private TextField nameField;

    @FXML
    private ComboBox<ReadingRoom> roomBox;

    @FXML
    private void update(){

        String name = StringSupport.replaceSpaces(nameField.getText());

        if (name.equals("")) {
            AlertWindow.errorAlert("Не должно быть пустых полей");
            return;
        }

        EmployeMapper mapper = ctx.getBean("employeMapper", EmployeMapper.class);
        mapper.updateEmploye(employe.getLogin(),name,roomBox.getValue().getId());
        close(Window.CLICK_EDIT);
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
        nameField.setText(employe.getFIO());
        roomBox.getSelectionModel().select(employe.getRoom());
    }

    @Override
    public void setApplicationContext(ApplicationContext ctx) {
        super.setApplicationContext(ctx);
        ReadingRoomMapper mapper = ctx.getBean("readingRoomMapper", ReadingRoomMapper.class);
        ObservableList<ReadingRoom> rooms = FXCollections.observableArrayList(mapper.getReadingRoom());
        roomBox.setItems(rooms);
    }
}
