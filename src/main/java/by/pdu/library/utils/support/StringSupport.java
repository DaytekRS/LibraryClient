package by.pdu.library.utils.support;

public class StringSupport {
    public static String NUM_REGEX   = ".*[0-9].*";

    public static String convertName(String name){
        return Character.toUpperCase(name.charAt(0)) + name.toLowerCase().substring(1);
    }

    public static String replaceSpaces(String text){
        return text.replaceAll("\\s+","");
    }

    public static boolean containsDigital(String text){
        return text.matches(StringSupport.NUM_REGEX);
    }
}
