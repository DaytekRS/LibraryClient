package by.pdu.library.windows.menu.employeMenu;

import by.pdu.library.domain.PublishingHouse;
import by.pdu.library.utils.support.ApplicationContext;
import by.pdu.library.utils.support.ApplicationContextImpl;
import by.pdu.library.windows.Window;
import by.pdu.library.windows.menu.employeMenu.publishingHouse.PublishingHouseController;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class MenuController extends Window {
    @FXML
    TableView publishingHouseTable;

    private ApplicationContextImpl ctxController;

    @FXML
    public void initialize() {
        ctxController = new ApplicationContextImpl();
        ObservableList<TableColumn> columns = publishingHouseTable.getColumns();
        columns.get(0).setCellValueFactory(new PropertyValueFactory<PublishingHouse, Long>("id"));
        columns.get(1).setCellValueFactory(new PropertyValueFactory<PublishingHouse, String>("name"));

        PublishingHouseController publishingHouse = new PublishingHouseController(publishingHouseTable);
        ctxController.inject(PublishingHouseController.class, "publishingHouse", publishingHouse);
    }

    @FXML
    private void addPublishingHouse() {
        ctxController.getBean("publishingHouse", PublishingHouseController.class).add();
    }

    @FXML
    private void removePublishingHouse() {
        ctxController.getBean("publishingHouse", PublishingHouseController.class).remove();
    }

    @FXML
    private void updatePublishingHouse() {
        ctxController.getBean("publishingHouse", PublishingHouseController.class).update();
    }


    @Override
    public void setApplicationContext(ApplicationContext ctx) {
        super.setApplicationContext(ctx);
        for (TabController tab : ctxController.getBeans(TabController.class)) {
            tab.setApplicationContext(ctx);
            tab.updateView();
        }
    }
}
