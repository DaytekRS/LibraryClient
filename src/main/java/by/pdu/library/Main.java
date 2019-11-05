package by.pdu.library;

import by.pdu.library.mapper.LanguageMapper;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        /*Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();*/

        SqlSessionFactory sqlSessionFactory;
        LanguageMapper languageMapper;
        Reader reader = null;
        try {
            reader = Resources.getResourceAsReader("mybatis-config.xml"); //Читаем файл с настройками подключения и настройками MyBatis
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            languageMapper = sqlSessionFactory.openSession().getMapper(LanguageMapper.class);
            System.out.println(languageMapper.getLanguageById(1));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
