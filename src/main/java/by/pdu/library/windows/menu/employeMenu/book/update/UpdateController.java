package by.pdu.library.windows.menu.employeMenu.book.update;

import by.pdu.library.domain.*;
import by.pdu.library.mapper.BookMapper;
import by.pdu.library.utils.AlertWindow;
import by.pdu.library.windows.menu.employeMenu.book.BookInit;
import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;

public class UpdateController extends BookInit {
    private Edition book;

    @FXML
    private void update() {
        try {
            BookMapper mapper = ctx.getBean("bookMapper", BookMapper.class);
            String name = nameField.getText();
            Float price = Float.parseFloat(priceField.getText());
            Long year = Long.parseLong(yearField.getText());
            String image = imageField.getText();
            String tabName = choosePane.getSelectionModel().getSelectedItem().getText();
            Long id = book.getId();
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
                    mapper.removeAllAuthors(id);
                    mapper.updateBook(id, year, image, name, language.getId(), price, publishingHouse.getId(), descriptionArea.getText(), catalog.getId());
                    for (Object author : authorPerTable.getItems()) {
                        mapper.insertAuthor(id, ((Author) author).getId());
                    }
                    Book book = new Book(id, Integer.parseInt(yearField.getText()), image, name, language, price, publishingHouse, descriptionArea.getText(), catalog, authorPerTable.getItems());
                    close(book);
                    break;
                case "Периодическое издание":
                    mapper.removeAllArticle(id);
                    mapper.updatePeriodic(id, year, image, name, language.getId(), price, publishingHouse.getId(), Long.parseLong(numberField.getText()), Long.parseLong(monthField.getText()));
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


    public void setEdition(Edition book) {
        this.book = book;
        nameField.setText(book.getName());
        imageField.setText(book.getImage());
        yearField.setText(book.getYear().toString());
        priceField.setText(book.getPrice().toString());
        languageBox.getSelectionModel().select(book.getLanguage());
        publishingBox.getSelectionModel().select(book.getPublishingHouse());
        if (book.getClass().equals(Book.class)) {
            descriptionArea.setText(((Book) book).getDescription());
            selectItem(catalogTree.getRoot());
            for (Author author : ((Book) book).getAuthors()) {
                authorTable.getItems().remove(author);
                authorPerTable.getItems().add(author);
            }
            choosePane.getSelectionModel().select(0);
        } else {
            Periodic periodic = (Periodic) book;
            System.out.println(periodic.getNumber().toString());
            numberField.setText(periodic.getNumber().toString());
            monthField.setText(periodic.getMonth().toString());
            for (Article article : periodic.getArticles()) {
                articleTable.getItems().remove(article);
                periodicTable.getItems().add(article);
            }
            choosePane.getSelectionModel().select(1);
        }
    }

    private void selectItem(TreeItem<Catalog> items) {
        if (items.getValue().getId().equals(((Book) book).getCatalog().getId())) {
            catalogTree.getSelectionModel().select(items);
        } else {
            for (TreeItem<Catalog> item : items.getChildren()) {
                selectItem(item);
            }
        }
    }
}
