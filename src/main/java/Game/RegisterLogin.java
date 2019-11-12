package Game;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.*;
import conection.MongoConnection;
import java.util.Scanner;

public class RegisterLogin {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    DBCollection coll;
    public RegisterLogin(){
        this.username = "";
        this.password = "";
    }

    public void register(DBCollection coll) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the username ");
        setUsername(sc.next());
        //DBCollection coll = new MongoConnection().loadCollection("login");
        BasicDBObject query = new BasicDBObject();
        query.put("username", getUsername());
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
            register(coll);
        }
        else{
            System.out.println("Enter the password ");
            setPassword(sc.next());
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

    public RegisterLogin login(RegisterLogin credentials, DBCollection coll){
      //  DBCollection coll = new MongoConnection().loadCollection("login");
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the username ");
        setUsername(sc.next());
        BasicDBObject query = new BasicDBObject();
        query.put("username", getUsername());
        DBCursor cursor = coll.find(query);
        if(cursor.size()==1) {
            System.out.println("Enter the password ");
            setPassword(sc.next());
            DBObject value = cursor.next();
            String p = value.get("password").toString();
            if(p.equals(getPassword())) {
                System.out.println("User successfully logged in ");
                return credentials;
            }
            else
                login(credentials, coll);
        }
        else{
            System.out.println("Invalid username ");
            login(credentials, coll);
        }
        return credentials;
    }
}

