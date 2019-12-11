package by.pdu.library.windows.menu.employeMenu.book.takeOrder;

import by.pdu.library.domain.Card;
import by.pdu.library.domain.Employe;
import by.pdu.library.domain.Instance;
import by.pdu.library.domain.Order;
import by.pdu.library.mapper.BookMapper;
import by.pdu.library.mapper.ExtraditionMapper;
import by.pdu.library.mapper.OrderMapper;
import by.pdu.library.utils.AlertWindow;
import by.pdu.library.utils.support.ApplicationContext;
import by.pdu.library.windows.SupportWindow;
import by.pdu.library.windows.Window;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TakeOrderController extends SupportWindow {

    @FXML
    private ComboBox orderBox;

   @FXML
   private DatePicker datePicker;

    @FXML
    private TableView tableView;

    @FXML
    public void initialize() {
        ObservableList<TableColumn> columns = tableView.getColumns();
        columns.get(0).setCellValueFactory(new PropertyValueFactory<Instance, Long>("numberInstance"));
    }

    @FXML
    private void take() {
        Instance instance = (Instance) tableView.getSelectionModel().getSelectedItem();
        if (instance == null) {
            AlertWindow.errorAlert("Не выбран экземпляр");
            return;
        }
        ExtraditionMapper extraditionMapper = ctx.getBean("extraditionMapper", ExtraditionMapper.class);
        BookMapper bookMapper = ctx.getBean("bookMapper", BookMapper.class);
        OrderMapper orderMapper = ctx.getBean("orderMapper",OrderMapper.class);
        Employe user = ctx.getBean("user", Employe.class);
        Order order = (Order)orderBox.getValue();

        extraditionMapper.takeInstance(user.getId(),datePicker.getEditor().getText(),order.getCardId(),instance.getNumberInstance());
        bookMapper.takeInstance(instance.getNumberInstance());
        orderMapper.extraditionOrder(order.getId());
        close(Window.CLICK_ADD);
    }

    @FXML
    private void selectOrder() {
        BookMapper bookMapper = ctx.getBean("bookMapper", BookMapper.class);
        Employe user = ctx.getBean("user", Employe.class);
        Order order = (Order) orderBox.getValue();
        ObservableList<Instance> instances = FXCollections.observableArrayList(bookMapper.getInstance(order.getEdition().getId(), user.getRoom().getId()));
        tableView.setItems(instances);
    }

    @Override
    public void setApplicationContext(ApplicationContext ctx) {
        super.setApplicationContext(ctx);
        Employe user = ctx.getBean("user", Employe.class);
        OrderMapper orderMapper = ctx.getBean("orderMapper", OrderMapper.class);
        orderMapper.getOrder(user.getRoom().getId());
        ObservableList<Order> orders = FXCollections.observableArrayList(orderMapper.getOrder(user.getRoom().getId()));
        orders.removeIf(order -> order.getEmploye().getId() == null);
        orderBox.setItems(orders);
    }

}
