package Game;

import com.mongodb.DBCollection;
import conection.GetMongoConnection;

import java.io.IOException;
import java.util.Scanner;

public class MainController {

    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        int n;
        String username = "";
        System.out.println("enter 1 to register and 2 for existing user ");
        int userChoice = sc.nextInt();
        GetMongoConnection con = new GetMongoConnection();
        con.creteCollection("login", "testdb");
        DBCollection coll = con.getColl();
        RegisterLogin credentials = new RegisterLogin();
        switch (userChoice) {
            case 1:
                credentials.register(coll);

            case 2:
                credentials = credentials.login(credentials, coll);
                System.out.println("enter the dimension");
                n = sc.nextInt();
                Game2048 ob = new Game2048(n);

                new BeginGame().beginGame(ob, credentials, coll);
                break;
            default:
                System.out.println("Wrong input ");
        }
    }
}

