package by.pdu.library.utils.support;

import java.util.ArrayList;

public interface ApplicationContext {

    <T> T getBean(String name, Class<T> clazz);

    void inject(final Class clazz, final String name, final Object obj);

    <T> ArrayList<T> getBeans(final Class<T> clazz);

}
