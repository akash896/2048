package Game;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import conection.MongoConnection;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.Scanner;

public class BeginGame {

    public static void beginGame(Game2048 ob, RegisterLogin credentials){
        Scanner scanner = new Scanner(System.in);
        int i=1;
        while(i==1) {
            System.out.println("enter the move or enter 1 to SAVE and 3 to LOAD ");
            int ch = scanner.nextInt();
            switch (ch) {
                case 4:
                    ob = ob.left(ob);
                    ob.display(ob);
                    ob.checkFull(ob);
                    break;
                case 6:
                    ob = ob.right(ob);
                    ob.display(ob);
                    ob.checkFull(ob);
                    break;
                case 8:
                    ob = ob.up(ob);
                    ob.display(ob);
                    ob.checkFull(ob);
                    break;
                case 2:
                    ob = ob.down(ob);
                    ob.display(ob);
                    ob.checkFull(ob);
                    break;
                case 1: ob.saveGame(ob, credentials.username, credentials.password);

                    break;
                case 3: ob = ob.loadGame(ob, credentials.username);
                break;
                default:
                    System.out.println("Wrong input ");
                    System.exit(0);
            }
        }
    }


}
