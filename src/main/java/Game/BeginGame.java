package Game;

import com.mongodb.DBCollection;

import java.util.Scanner;

public class BeginGame {

    public static void beginGame(Game2048 ob, RegisterLogin credentials, DBCollection coll){
        Scanner scanner = new Scanner(System.in);
        int i=1;
        while(i==1) {
            System.out.println("enter the move or enter 1 to SAVE and 3 to LOAD ");
            int ch = scanner.nextInt();
            switch (ch) {
                case 4:
                    ob.grid = ob.left(ob.grid);
                    ob.display(ob.grid);
                    ob.checkFull(ob.grid);
                    break;
                case 6:
                    ob.grid = ob.right(ob.grid);
                    ob.display(ob.grid);
                    ob.checkFull(ob.grid);
                    break;
                case 8:
                    ob.grid = ob.up(ob.grid);
                    ob.display(ob.grid);
                    ob.checkFull(ob.grid);
                    break;
                case 2:
                    ob.grid = ob.down(ob.grid);
                    ob.display(ob.grid);
                    ob.checkFull(ob.grid);
                    break;
                case 1: ob.saveGame(ob.grid, credentials.getUsername(), credentials.getPassword(), coll);

                    break;
                case 3: ob.grid = ob.loadGame(ob.grid, credentials.getUsername(), coll);
                break;
                default:
                    System.out.println("Wrong input ");
                    System.exit(0);
            }
        }
    }
}
