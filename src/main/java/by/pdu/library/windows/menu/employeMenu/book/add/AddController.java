package by.pdu.library.windows.menu.employeMenu.book.add;

import by.pdu.library.domain.*;
import by.pdu.library.mapper.BookMapper;
import by.pdu.library.utils.AlertWindow;
import by.pdu.library.windows.menu.employeMenu.book.BookInit;
import javafx.fxml.FXML;

public class AddController extends BookInit {
    @FXML
    private void add() {
        try {
            BookMapper mapper = ctx.getBean("bookMapper", BookMapper.class);
            String name = nameField.getText();
            Float price = Float.parseFloat(priceField.getText());
            Long year = Long.parseLong(yearField.getText());
            String image = imageField.getText();
            String tabName = choosePane.getSelectionModel().getSelectedItem().getText();
            Long id = mapper.nextId();
            Language language = (Language) languageBox.getValue();
            PublishingHouse publishingHouse = (PublishingHouse) publishingBox.getValue();
            if (language == null) {
                AlertWindow.errorAlert("Выберите язык");
                return;
            }
            if (publishingHouse == null) {
                AlertWindow.errorAlert("Выберите издательство");
                return;
            }
            switch (tabName) {
                case "Книга":
                    if (catalogTree.getSelectionModel().getSelectedItem() == null) {
                        AlertWindow.errorAlert("Выберите каталог");
                        return;
                    }
                    Catalog catalog = catalogTree.getSelectionModel().getSelectedItem().getValue();
                    if (catalog.equals(rootCatalog)) {
                        AlertWindow.errorAlert("Нельзя выбирать корневой каталог");
                        return;
                    }
                    mapper.insertBook(id, year, image, name, language.getId(), price, publishingHouse.getId(), descriptionArea.getText(), catalog.getId());
                    for (Object author : authorPerTable.getItems()) {
                        mapper.insertAuthor(id, ((Author) author).getId());
                    }
                    Book book = new Book(id, Integer.parseInt(yearField.getText()), image, name, language, price, publishingHouse, descriptionArea.getText(), catalog, authorPerTable.getItems());
                    close(book);
                    break;
                case "Периодическое издание":
                    mapper.insertPeriodic(id, year, image, name, language.getId(), price, publishingHouse.getId(), Long.parseLong(numberField.getText()), Long.parseLong(monthField.getText()));
                    for (Object article : periodicTable.getItems()) {
                        mapper.insertArticle(id, ((Article) article).getId());
                    }
                    Periodic periodic = new Periodic(id, Integer.parseInt(yearField.getText()), image, name, language, price, publishingHouse,
                            Long.parseLong(numberField.getText()), Integer.parseInt(monthField.getText()), periodicTable.getItems());
                    close(periodic);
                    break;
            }
        } catch (Exception ex) {
            AlertWindow.checkException(ex.getMessage());
            ex.printStackTrace();
        }
    }


}
