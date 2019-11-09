package conection;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class MongoConnection implements Connect {

      public DB createConnection(){
          MongoClient mongodb = new MongoClient("localhost", 27017);

          // if database doesn't exists, MongoDB will create it for us
          @SuppressWarnings("deprecation")
          DB db = mongodb.getDB("testdb");
          System.out.println("Connection to MongoDB database successfully");
          return db;
      }

      public DBCollection loadCollection(String collectionName){
          MongoConnection con = new MongoConnection();
          DB db = con.createConnection();
          // loading the collection from database
          DBCollection coll = db.getCollection(collectionName);
          return coll;
      }


}
