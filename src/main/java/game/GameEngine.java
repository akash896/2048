package game;

import storage.MongoDatabaseHandler;

public class GameEngine implements UserOperations, GameOperations {
    private GameModel gameModel;
    private MongoDatabaseHandler databaseHandler;

    public GameEngine(GameModel gameModel, MongoDatabaseHandler databaseHandler) {
        this.gameModel = gameModel;
        this.databaseHandler = databaseHandler;
    }

    @Override
    public boolean register(String userName, String password) {
        return false;
    }

    @Override
    public boolean unregister(String userName, String password) {
        return false;
    }

    @Override
    public boolean login(String userName, String password) {
        return false;
    }

    @Override
    public boolean up() {
        return false;
    }

    @Override
    public boolean left() {
        return false;
    }

    @Override
    public boolean right() {
        return false;
    }

    @Override
    public boolean down() {
        return false;
    }

    @Override
    public boolean load() {
        return false;
    }

    @Override
    public boolean save() {
        return false;
    }

    @Override
    public boolean quit() {
        return false;
    }

    @Override
    public void displayGrid() {

    }

    @Override
    public boolean win() {
        return false;
    }

    @Override
    public boolean checkFull() {
        return false;
    }

}
