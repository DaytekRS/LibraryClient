package by.pdu.library.utils.support;

import by.pdu.library.utils.AlertWindow;
import javafx.scene.control.TextInputControl;

import java.util.List;

public class StringSupport {
    public static String NUM_REGEX   = ".*[0-9].*";

    public static String convertName(String name){
        name = replaceSpaces(name);
        return Character.toUpperCase(name.charAt(0)) + name.toLowerCase().substring(1);
    }

    public static String replaceSpaces(String text){
        return text.replaceAll("\\s+"," ");
    }

    public static boolean containsDigital(String text){
        return text.matches(StringSupport.NUM_REGEX);
    }

    public static boolean stringCheck(List<TextInputControl> fields){
        for (TextInputControl text:fields){
            String name = StringSupport.replaceSpaces(text.getText());

            if (name.equals("")){
                AlertWindow.ErrorAlert("Поле "+text.getPromptText()+" не может быть пустым");
                return false;
            }

            if (StringSupport.containsDigital(name)){
                AlertWindow.ErrorAlert("Поле "+text.getPromptText()+" не может содеражать цифры");
                return false;
            }
        }
        return true;
    }
}
