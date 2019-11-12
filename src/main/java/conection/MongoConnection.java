package conection;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class MongoConnection implements Connect {
private DB db;
      public void createConnection(String databaseName){
          MongoClient mongodb = new MongoClient("localhost", 27017);
          db = mongodb.getDB(databaseName);
          System.out.println("Connection to MongoDB database successfully");
      }

    public DB getDb() {
        return db;
    }

    /*public DBCollection loadCollection(String collectionName,){
          MongoConnection con = new MongoConnection();
          DB db = con.createConnection();
          // loading the collection from database
          DBCollection coll = db.getCollection(collectionName);
          return coll;
      }*/
}
