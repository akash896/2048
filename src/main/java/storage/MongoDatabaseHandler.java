package storage;

import com.mongodb.DBCollection;
import game.GameModel;

public class MongoDatabaseHandler implements DataBaseHandler {
    private DBCollection collection;

    public MongoDatabaseHandler(DBCollection collection) {
        this.collection = collection;
    }

    public boolean save(GameModel gameModel) {
        return false;
    }

    public GameModel get(String userName) {
        return null;
    }

    public boolean delete(String userName) {
        return false;
    }
}
