package cli.controller;

import com.mongodb.DBCollection;
import game.GameEngine;
import game.GameModel;
import storage.MongoConnectionHandler;
import storage.MongoDatabaseHandler;

import java.util.Scanner;

public class CLIController {

    public void beginGame() {
        DBCollection collection = MongoConnectionHandler.getDBCollection("");
        MongoDatabaseHandler mongoDatabaseHandler = new MongoDatabaseHandler(collection);
        GameModel gameModel = new GameModel();
        GameEngine engine = new GameEngine(gameModel, mongoDatabaseHandler);

        Scanner sc = new Scanner(System.in);
        int n;
        boolean status = false;
        boolean loginStatus = false;
        while (!loginStatus) {
            System.out.println("enter 1 to register and 2 for existing user ");
            int userChoice = sc.nextInt();
            switch (userChoice) {
                case 1:
                    while (!status) {
                        System.out.print("Enter the username: ");
                        String userName = sc.next();
                        System.out.print("Enter the password: ");
                        String password = sc.next();
                        status = engine.register(userName, password);
                    }
                case 2:
                    System.out.print("Enter the username: ");
                    String userName = sc.next();
                    System.out.print("Enter the password: ");
                    String password = sc.next();
                    loginStatus = engine.login(userName, password);
                    if (loginStatus) {
                        System.out.print("Enter the dimension: ");
                        n = sc.nextInt();
                        gameModel.initialiseGameModel(n);
                        gameCLIController(engine);
                    }
            }
        }
    }


    private void gameCLIController(GameEngine engine) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("enter the move or enter 1 to SAVE and 3 to LOAD ");
            int ch = sc.nextInt();
            switch (ch) {
                case 4:
                    engine.left();
                    engine.displayGrid();
                    engine.checkFull();
                    break;
                case 6:
                    engine.right();
                    engine.displayGrid();
                    engine.checkFull();
                    break;
                case 8:
                    engine.down();
                    engine.displayGrid();
                    engine.checkFull();
                    break;
                case 2:
                    engine.up();
                    engine.displayGrid();
                    engine.checkFull();
                    break;
                case 1:
                    engine.save();
                    break;
                case 3:
                    engine.load();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Wrong input ");
            }
        }
    }
}
