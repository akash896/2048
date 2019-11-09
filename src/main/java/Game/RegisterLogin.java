package Game;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.*;
import conection.MongoConnection;
import java.util.Scanner;

public class RegisterLogin {

    String username;
    String password;
    DBCollection coll;
    public RegisterLogin(){
        this.username = "";
        this.password = "";

    }

    public void register() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the username ");
        String username = sc.next();

        DBCollection coll = new MongoConnection().loadCollection("login");
        BasicDBObject query = new BasicDBObject();
        query.put("username", username);
        DBCursor cursor = coll.find(query);
        //DBCursor cursor = coll.find();
        /*int index = 1;
        while (cursor.hasNext()) {
            System.out.println("Inserted Document: " + index);
            System.out.println(cursor.next());
            index++;
        }*/
        if(cursor.size()!=0) {
            System.out.println("Enter some other username as this username is already in use ");
            register();
        }
        else{
            System.out.println("Enter the password ");
            String password = sc.next();
            String progress ="";
            try {
                ObjectMapper mapper = new ObjectMapper();
                progress = mapper.writeValueAsString(new Game2048(4));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            DBObject dbObject = new BasicDBObject("username", username).append("password", password).append("matrix", progress);
            coll.insert(dbObject);
            System.out.println("New player Registration Done ");
        }
        System.out.println("\n Now Login using your username and password ");
    }

    public RegisterLogin login(RegisterLogin credentials){
        DBCollection coll = new MongoConnection().loadCollection("login");
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the username ");
        String username = sc.next();
        BasicDBObject query = new BasicDBObject();
        query.put("username", username);
        credentials.username = username;
        DBCursor cursor = coll.find(query);
        if(cursor.size()==1) {
            System.out.println("Enter the password ");
            String password = sc.next();
            credentials.password = password;
            DBObject value = cursor.next();
            String p = value.get("password").toString();
            if(p.equals(password)) {
                System.out.println("User successfully logged in ");
                return credentials;
            }
            else
                login(credentials);
        }
        else{
            System.out.println("Invalid username ");
            login(credentials);
        }
        return credentials;
    }
}

