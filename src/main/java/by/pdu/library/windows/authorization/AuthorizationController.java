package by.pdu.library.windows.authorization;

import by.pdu.library.domain.Book;
import by.pdu.library.domain.Employe;
import by.pdu.library.mapper.*;
import by.pdu.library.utils.AlertWindow;
import by.pdu.library.utils.support.LoadFXML;
import by.pdu.library.windows.Window;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
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
        SqlSession session = sqlSessionFactory.openSession();

        ctx.inject(SqlSession.class, "session", session);
        LanguageMapper languageMapper = session.getMapper(LanguageMapper.class);
        ctx.inject(LanguageMapper.class, "languageMapper", languageMapper);

        EmployeMapper employeMapper = session.getMapper(EmployeMapper.class);
        ctx.inject(EmployeMapper.class, "employeMapper", employeMapper);

        ReadingRoomMapper readingRoomMapper = session.getMapper(ReadingRoomMapper.class);
        ctx.inject(ReadingRoomMapper.class, "readingRoomMapper", readingRoomMapper);

        FineMapper fineMapper = session.getMapper(FineMapper.class);
        ctx.inject(FineMapper.class, "fineMapper", fineMapper);

        FacultyMapper facultyMapper = session.getMapper(FacultyMapper.class);
        ctx.inject(FacultyMapper.class, "facultyMapper", facultyMapper);

        DepartmentMapper departmentMapper = session.getMapper(DepartmentMapper.class);
        ctx.inject(DepartmentMapper.class, "departmentMapper", departmentMapper);

        GradeMapper gradeMapper = session.getMapper(GradeMapper.class);
        ctx.inject(GradeMapper.class, "gradeMapper", gradeMapper);

        GroupMapper groupMapper = session.getMapper(GroupMapper.class);
        ctx.inject(GroupMapper.class, "groupMapper", groupMapper);

        PublishingHouseMapper publishingHouseMapper = session.getMapper(PublishingHouseMapper.class);
        ctx.inject(PublishingHouseMapper.class, "publishingHouseMapper", publishingHouseMapper);

        AuthorMapper authorMapper = session.getMapper(AuthorMapper.class);
        ctx.inject(AuthorMapper.class, "authorMapper", authorMapper);

        CatalogMapper catalogMapper = session.getMapper(CatalogMapper.class);
        ctx.inject(CatalogMapper.class, "catalogMapper", catalogMapper);

        CardMapper cardMapper = session.getMapper(CardMapper.class);
        ctx.inject(CardMapper.class, "cardMapper", cardMapper);

        ArticleMapper articleMapper = session.getMapper(ArticleMapper.class);
        ctx.inject(ArticleMapper.class, "articleMapper", articleMapper);

        BookMapper bookMapper = session.getMapper(BookMapper.class);
        ctx.inject(BookMapper.class, "bookMapper", bookMapper);

        UserMapper userMapper = session.getMapper(UserMapper.class);
        ctx.inject(UserMapper.class, "userMapper", userMapper);

        OrderMapper orderMapper = session.getMapper(OrderMapper.class);
        ctx.inject(OrderMapper.class, "orderMapper", orderMapper);
    }

    @FXML
    private void click() {
        SqlSessionFactory sqlSessionFactory;
        try {
            Properties properties = new Properties();
            properties.setProperty("username", login.getText());
            properties.setProperty("password", password.getText());
            Reader inputStream = Resources.getResourceAsReader("mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream, properties);
            sqlSessionFactory.openSession().getConnection();

            ctx.inject(SqlSessionFactory.class, "sqlSessionFactory", sqlSessionFactory);
            loadMappers(sqlSessionFactory);

            EmployeMapper employeMapper = ctx.getBean("employeMapper", EmployeMapper.class);

            LoadFXML loader = ctx.getBean("loader", LoadFXML.class);

            Employe user = employeMapper.getEmployeByLogin(login.getText());
            ctx.inject(Employe.class, "user", user);

            String role = employeMapper.getRole();
            if (role == null) {
                loader.load("windows/menu/adminMenu/adminMenu.fxml", "Меню", stage, 360, 410);
            } else if (role.equals("EMPLOYE_LIBRARY")) {
                loader.load("windows/menu/employeMenu/menu.fxml", "Меню", stage, 805, 510);
            }

        } catch (IOException e) {
            AlertWindow.errorAlert("Не найден конфигурационный файл");
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
            AlertWindow.errorAlert("Введен неверный логин или пароль. Повторите попытку");
        }
    }

}
