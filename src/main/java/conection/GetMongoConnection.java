package conection;

import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class GetMongoConnection {
       public static void main(String[] args) {
           MongoConnection con = new MongoConnection();
           DB db = con.createConnection();
           /**
            * Creating and Inserting Records in MongoDB.
            */
           DBObject dbObject = new BasicDBObject("title", "MongoDBTutorials").append("description", "No-SQL Database")
                   .append("likes", 100000).append("url", "https://www.mongodb.com/").append("Tutorial", "Eduonix");

           db.createCollection("MyCollection1", dbObject);

           /**
            * Selecting Records from MongoDB
            */
           DBCollection coll = db.getCollection("login");
           coll.insert(dbObject);
           //System.out.println("Collection has selected successfully");
           DBCursor cursor = coll.find();
           int index = 1;

           while (cursor.hasNext()) {
               System.out.println("Inserted Document: " + index);
               System.out.println(cursor.next());
               index++;
           }
     }
}

