package conection;

import com.mongodb.DB;
import com.mongodb.client.MongoDatabase;

public interface Connect {

    public DB createConnection();

}
