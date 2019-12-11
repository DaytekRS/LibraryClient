package by.pdu.library.windows.menu.employeMenu.book.takeInstance;

import by.pdu.library.domain.Book;
import by.pdu.library.domain.Card;
import by.pdu.library.domain.Employe;
import by.pdu.library.domain.Instance;
import by.pdu.library.mapper.BookMapper;
import by.pdu.library.mapper.CardMapper;
import by.pdu.library.mapper.ExtraditionMapper;
import by.pdu.library.utils.AlertWindow;
import by.pdu.library.utils.support.ApplicationContext;
import by.pdu.library.windows.SupportWindow;
import by.pdu.library.windows.Window;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Date;
import java.util.Comparator;

public class TakeController extends SupportWindow {
    private Instance instance;

    @FXML
    private DatePicker datePicker;

    @FXML
    private TableView tableView;

    @FXML
    public void initialize() {
        ObservableList<TableColumn> columns = tableView.getColumns();
        columns.get(0).setCellValueFactory(new PropertyValueFactory<Card, Long>("id"));
        columns.get(1).setCellValueFactory(new PropertyValueFactory<Card, String>("FIO"));
        columns.get(2).setCellValueFactory(new PropertyValueFactory<Card, Date>("validDate"));
        columns.get(3).setCellValueFactory(new PropertyValueFactory<Card, String>("type"));
    }

    @FXML
    private void take() {
        Card card = (Card) tableView.getSelectionModel().getSelectedItem();
        if (card == null) {
            AlertWindow.errorAlert("Не выбран пользователь");
            return;
        }
        ExtraditionMapper extraditionMapper = ctx.getBean("extraditionMapper", ExtraditionMapper.class);
        BookMapper bookMapper = ctx.getBean("bookMapper", BookMapper.class);
        Employe user = ctx.getBean("user", Employe.class);
        System.out.println(instance.getId());
        extraditionMapper.takeInstance(user.getId(), datePicker.getEditor().getText(),card.getId(), instance.getNumberInstance());
        bookMapper.takeInstance(instance.getNumberInstance());
        close(Window.CLICK_ADD);
    }

    @Override
    public void setApplicationContext(ApplicationContext ctx) {
        super.setApplicationContext(ctx);
        CardMapper mapper = ctx.getBean("cardMapper", CardMapper.class);
        ObservableList<Card> cards = FXCollections.observableArrayList(mapper.getStudentCard());
        cards.addAll(mapper.getTeacherCard());
        cards.addAll(mapper.getGraduateStudentCard());
        cards.addAll(mapper.getOtherCard());
        cards.sort(Comparator.comparing(Card::getId));
        tableView.setItems(cards);
        tableView.refresh();
    }

    public void setInstance(Instance instance) {
        this.instance = instance;
    }
}
