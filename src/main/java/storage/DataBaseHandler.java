package storage;

import game.GameModel;

public interface DataBaseHandler {
    boolean save(GameModel gameModel);
    GameModel get(String userName);
    boolean delete(String userName);
}
