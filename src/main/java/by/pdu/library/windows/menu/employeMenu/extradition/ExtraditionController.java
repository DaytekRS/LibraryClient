package by.pdu.library.windows.menu.employeMenu.extradition;

import by.pdu.library.domain.*;
import by.pdu.library.mapper.BookMapper;
import by.pdu.library.mapper.ExtraditionMapper;
import by.pdu.library.mapper.LanguageMapper;
import by.pdu.library.utils.AlertWindow;
import by.pdu.library.utils.support.LoadFXML;
import by.pdu.library.windows.Window;
import by.pdu.library.windows.menu.employeMenu.TabController;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

public class ExtraditionController extends TabController {
    TableView tableView;

    public ExtraditionController(TableView view) {
        super(view);
        this.tableView = view;
        ObservableList<TableColumn> columns = tableView.getColumns();
        columns.get(0).setCellValueFactory(new PropertyValueFactory<ExtraditionInstance, Long>("id"));
        columns.get(1).setCellValueFactory((Callback<TableColumn.CellDataFeatures<ExtraditionInstance, Long>, ObservableValue<Long>>) p -> new ReadOnlyObjectWrapper<>(p.getValue().getCard().getId()));
        columns.get(2).setCellValueFactory((Callback<TableColumn.CellDataFeatures<ExtraditionInstance, String>, ObservableValue<String>>) p -> new ReadOnlyObjectWrapper<>(p.getValue().getEmploye().getFIO()));
        columns.get(3).setCellValueFactory((Callback<TableColumn.CellDataFeatures<ExtraditionInstance, String>, ObservableValue<String>>) p -> new ReadOnlyObjectWrapper<>(p.getValue().getEdition().getName()));
    }

    @Override
    public void updateView() {
        ExtraditionMapper extraditionMapper = ctx.getBean("extraditionMapper", ExtraditionMapper.class);
        Employe user = ctx.getBean("user",Employe.class);
        ObservableList<ExtraditionInstance> extraditions = FXCollections.observableArrayList(extraditionMapper.getExtraditionInstance(user.getRoom().getId()));
        tableView.setItems(extraditions);
        tableView.refresh();
    }

    @Override
    public void update() {

    }

    @Override
    public void add() {
        Object obj = tableView.getSelectionModel().getSelectedItem();
        if (obj == null){
            AlertWindow.errorAlert("Выберите выдачу");
            return;
        }
        BookMapper bookMapper = ctx.getBean("bookMapper",BookMapper.class);
        bookMapper.returnInstance(((ExtraditionInstance)obj).getInstanceNumber());
        ExtraditionMapper extraditionMapper = ctx.getBean("extraditionMapper",ExtraditionMapper.class);
        extraditionMapper.returnInstance(((ExtraditionInstance)obj).getId());
        commit();
        updateView();
    }

    @Override
    public void remove() {

    }
}
