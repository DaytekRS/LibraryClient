package by.pdu.library.windows.menu.employeMenu.users;

import by.pdu.library.domain.Card;
import by.pdu.library.mapper.CardMapper;
import by.pdu.library.windows.Window;
import by.pdu.library.windows.menu.employeMenu.TabController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.sql.Date;

public class UsersController extends TabController {
    private TableView tableView;

    public UsersController(TableView view) {
        super(view);
        this.tableView = view;
        ObservableList<TableColumn> columns = tableView.getColumns();
        columns.get(0).setCellValueFactory(new PropertyValueFactory<Card, Long>("id"));
        columns.get(1).setCellValueFactory(new PropertyValueFactory<Card, String>("FIO"));
        columns.get(2).setCellValueFactory(new PropertyValueFactory<Card, Date>("validDate"));
        columns.get(3).setCellValueFactory(new PropertyValueFactory<Card, String>("type"));
    }

    @Override
    public void updateView() {
        CardMapper mapper = ctx.getBean("cardMapper", CardMapper.class);
        ObservableList<Card> cards = FXCollections.observableArrayList(mapper.getStudentCard());
        cards.addAll(mapper.getTeacherCard());
        cards.addAll(mapper.getGraduateStudentCard());
        cards.addAll(mapper.getOtherCard());
        tableView.setItems(cards);
        tableView.refresh();
    }

    @Override
    public void update() {

    }

    @Override
    public void add() {
        Stage stage = modalWindow("windows/menu/employeMenu/users/add/add.fxml", "Добавить пользователя", 440, 350);
        Object data = stage.getUserData();
        if (data != null && (Integer) data == Window.CLICK_ADD) updateView();
    }

    @Override
    public void remove() {

    }
}
