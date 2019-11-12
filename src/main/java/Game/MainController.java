package Game;

import cli.controller.CLIController;

import java.io.IOException;
import java.util.Scanner;

public class MainController {

    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        int n;
        String username = "";
        System.out.println("enter 1 to register and 2 for existing user ");
        int userChoice = sc.nextInt();
        RegisterLogin credentials = new RegisterLogin();
        switch (userChoice) {
            case 1:
                credentials.register();

            case 2:
                credentials = credentials.login(credentials);
                System.out.println("enter the dimension");
                n = sc.nextInt();
                Game2048 ob = new Game2048(n);
                new CLIController().beginGame(ob, credentials);
                break;
        }
    }
}

