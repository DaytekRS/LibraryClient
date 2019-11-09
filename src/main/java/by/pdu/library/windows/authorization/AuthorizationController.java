package by.pdu.library.windows.authorization;

import by.pdu.library.Main;
import by.pdu.library.mapper.EmployeMapper;
import by.pdu.library.mapper.LanguageMapper;
import by.pdu.library.mapper.ReadingRoomMapper;
import by.pdu.library.utils.AlertWindow;
import by.pdu.library.windows.Window;
import by.pdu.library.windows.menu.MenuController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.util.Properties;


public class AuthorizationController extends Window {

    @FXML
    TextField login;
    @FXML
    TextField password;

    public void loadMappers(SqlSessionFactory sqlSessionFactory) {
        EmployeMapper empl = sqlSessionFactory.openSession().getMapper(EmployeMapper.class);
        System.out.println(empl.getEmploye());
        LanguageMapper languageMapper = sqlSessionFactory.openSession().getMapper(LanguageMapper.class);
        ctx.inject(LanguageMapper.class, "languageMapper", languageMapper);
    }

    @FXML
    public void click() {
        SqlSessionFactory sqlSessionFactory;
        try {
            Properties properties = new Properties();
            System.out.println(login.getText());
            properties.setProperty("username", login.getText());
            properties.setProperty("password", password.getText());
            Reader inputStream = Resources.getResourceAsReader("mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream, properties);
            sqlSessionFactory.openSession().getConnection();

            ctx.inject(SqlSessionFactory.class, "sqlSessionFactory", sqlSessionFactory);
            loadMappers(sqlSessionFactory);
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("windows/menu/menu.fxml"));
            Parent root = loader.load();
            MenuController controller = loader.getController();
            controller.setApplicationContext(ctx);
            ((Stage) login.getScene().getWindow()).setTitle("Меню");
            ((Stage) login.getScene().getWindow()).setScene(new Scene(root, 300, 275));

        } catch (IOException e) {
            AlertWindow.ErrorAlert("Не найден конфигурационный файл");
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
            AlertWindow.ErrorAlert("Введен неверный логин или пароль. Повторите попытку");
        }
    }
}
