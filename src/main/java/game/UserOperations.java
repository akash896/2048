package game;

public interface UserOperations {
    boolean register(String userName, String password);
    boolean unregister(String userName, String password);
    boolean login(String userName, String password);
    boolean up();
    boolean left();
    boolean right();
    boolean down();
    boolean load();
    boolean save();
    boolean quit();
}
