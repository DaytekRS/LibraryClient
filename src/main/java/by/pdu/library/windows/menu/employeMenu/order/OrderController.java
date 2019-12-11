package by.pdu.library.windows.menu.employeMenu.order;

import by.pdu.library.domain.*;
import by.pdu.library.mapper.LanguageMapper;
import by.pdu.library.mapper.OrderMapper;
import by.pdu.library.utils.AlertWindow;
import by.pdu.library.utils.support.LoadFXML;
import by.pdu.library.windows.Window;
import by.pdu.library.windows.menu.employeMenu.TabController;
import by.pdu.library.windows.menu.employeMenu.order.update.UpdateController;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

public class OrderController extends TabController {
    TableView tableView;

    public OrderController(TableView view) {
        super(view);
        this.tableView = view;
        ObservableList<TableColumn> columns = tableView.getColumns();

        columns.get(0).setCellValueFactory(new PropertyValueFactory<Order, Long>("id"));
        columns.get(1).setCellValueFactory((Callback<TableColumn.CellDataFeatures<Order, String>, ObservableValue<String>>) p -> new ReadOnlyObjectWrapper<>(p.getValue().getEdition().getName()));
        columns.get(2).setCellValueFactory((Callback<TableColumn.CellDataFeatures<Order, Long>, ObservableValue<Long>>) p -> new ReadOnlyObjectWrapper<>(p.getValue().getEdition().getId()));
        columns.get(3).setCellValueFactory(new PropertyValueFactory<Order, String>("status"));
    }

    @Override
    public void updateView() {
        OrderMapper orderMapper = ctx.getBean("orderMapper", OrderMapper.class);
        Employe user = ctx.getBean("user", Employe.class);
        ObservableList<Order> orders = FXCollections.observableArrayList(orderMapper.getOrder(user.getRoom().getId()));
        for (Order order: orders){
            if (order.getEmploye().getId()!=null) order.setStatus("Собран");
        }
        tableView.setItems(orders);
        tableView.refresh();
    }

    @Override
    public void add() {
        Object obj = tableView.getSelectionModel().getSelectedItem();
        if (obj!=null) {
            Employe user = ctx.getBean("user",Employe.class);
            OrderMapper orderMapper = ctx.getBean("orderMapper",OrderMapper.class);
            orderMapper.updateEmployeeOrder(((Order)obj).getId(),user.getId());
            commit();
            updateView();
        }
    }

    @Override
    public void update() {
        Object obj = tableView.getSelectionModel().getSelectedItem();
        if (obj!=null) {
            OrderMapper orderMapper = ctx.getBean("orderMapper",OrderMapper.class);
            orderMapper.updateNoAssembledOrder(((Order)obj).getId());
            commit();
            updateView();
        }
    }

    @Override
    public void remove() {
        Object obj = tableView.getSelectionModel().getSelectedItem();
        if (obj!=null) {
            OrderMapper orderMapper = ctx.getBean("orderMapper",OrderMapper.class);
            orderMapper.deleteOrder(((Order)obj).getId());
            commit();
            updateView();
        }
    }

}
