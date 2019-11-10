package by.pdu.library.windows.authorization;

import by.pdu.library.Main;
import by.pdu.library.domain.Employe;
import by.pdu.library.mapper.EmployeMapper;
import by.pdu.library.mapper.FineMapper;
import by.pdu.library.mapper.LanguageMapper;
import by.pdu.library.mapper.ReadingRoomMapper;
import by.pdu.library.utils.AlertWindow;
import by.pdu.library.utils.support.LoadFXML;
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
    private TextField login;
    @FXML
    private TextField password;

    private void loadMappers(SqlSessionFactory sqlSessionFactory) {
        LanguageMapper languageMapper = sqlSessionFactory.openSession().getMapper(LanguageMapper.class);
        ctx.inject(LanguageMapper.class, "languageMapper", languageMapper);

        EmployeMapper employeMapper = sqlSessionFactory.openSession().getMapper(EmployeMapper.class);
        ctx.inject(EmployeMapper.class, "employeMapper", employeMapper);

        ReadingRoomMapper readingRoomMapper = sqlSessionFactory.openSession().getMapper(ReadingRoomMapper.class);
        ctx.inject(ReadingRoomMapper.class, "readingRoomMapper", readingRoomMapper);

        FineMapper fineMapper = sqlSessionFactory.openSession().getMapper(FineMapper.class);
        ctx.inject(FineMapper.class, "fineMapper", fineMapper);
    }

    @FXML
    private void click() {
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

            EmployeMapper employeMapper = ctx.getBean("employeMapper",EmployeMapper.class);

            LoadFXML loader = ctx.getBean("loader", LoadFXML.class);
            boolean find=false;
            for (Employe employe : employeMapper.getEmploye()){
                if (employe.getLogin().equals(login.getText())){
                    loader.load("windows/menu/menu.fxml","Меню",((Stage) login.getScene().getWindow()),300,275);
                    find=true;
                    break;
                }
            }

            if(!find){
                loader.load("windows/menu/adminMenu/adminMenu.fxml","Меню",((Stage) login.getScene().getWindow()),300,275);
            }


        } catch (IOException e) {
            AlertWindow.ErrorAlert("Не найден конфигурационный файл");
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
            AlertWindow.ErrorAlert("Введен неверный логин или пароль. Повторите попытку");
        }
    }
}
