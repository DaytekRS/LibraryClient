package by.pdu.library.windows;

public abstract class SupportWindow extends Window{

    protected void close(int code){
        stage.setUserData(code);
        commit();
        stage.close();
    }
}
