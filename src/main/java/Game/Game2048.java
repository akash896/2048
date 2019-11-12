package Game;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.*;
import conection.MongoConnection;

import java.io.IOException;
import java.io.Serializable;
import java.util.Random;

public class Game2048 implements Serializable {
    GameModel grid;

    public Game2048(GameModel grid) {
        this.grid = grid;
    }

    public Game2048(int n){
        int A[][] = new int[n][n];
        for(int i=0;i<n;i++)
            for(int j=0;j<n;j++)
                A[i][j] = 0;
        this.grid = new GameModel(A, n);
    }

   /* public Game2048(int[][] matrix, int n, int status) {
        this.matrix = matrix;
        this.n = n;
        this.status = status;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public int getStatus() {
        return status;
    }*/


    /*public Game2048(int n) {
        matrix = new int[n][n];
        this.n = n;
        this.status = 0;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                matrix[i][j] = 0;
    }*/

    public GameModel left(GameModel ob) {
        int n = ob.getN();
        int matrix[][] = ob.getMatrix();
        for (int row = 0; row < n; row++) {
            int s = 0, f = 0;
            for (int col = 0; col <= n - 1; col++) {
                if (matrix[row][col] != 0) {
                    int temp = matrix[row][f];
                    matrix[row][f] = matrix[row][s];
                    matrix[row][s] = temp;
                    s++;
                    f++;
                } else {
                    f++;
                }
            }

            for (int col = 0; col < n - 1; col++) {
                if (matrix[row][col] == matrix[row][col + 1] && matrix[row][col] != 0) {
                    matrix[row][col] = 2 * matrix[row][col];
                    for (s = col + 1; s < n - 1; s++)
                        matrix[row][s] = matrix[row][s + 1];
                    matrix[row][n - 1] = 0;
                }
            }
        }

        Random r = new Random();
        int f = 0;
        while (f == 0) {
            int x = r.nextInt(n);
            int y = r.nextInt(n);
            if (matrix[x][y] == 0) {
                matrix[x][y] = 2;
                f = 1;
                break;
            }
        }
        ob.setMatrix(matrix);
        return ob;
    } //left() ends

    public GameModel right(GameModel ob) {
        int n = ob.getN();
        int matrix[][] = ob.getMatrix();
        for (int row = 0; row<n; row++) {
            int s = n - 1, f = n - 1;
            for (int col = n - 1; col >= 0; col--) {
                if (matrix[row][col] != 0) {
                    int temp = matrix[row][f];
                    matrix[row][f] = matrix[row][s];
                    matrix[row][s] = temp;
                    s--;
                    f--;
                } else {
                    f--;
                }
            }

            for (int col = n - 1; col > 0; col--) {
                if (matrix[row][col] == matrix[row][col - 1] && matrix[row][col] != 0) {
                    matrix[row][col] = 2 * matrix[row][col];
                    for (s = col - 1; s >= 1; s--)
                        matrix[row][s] = matrix[row][s - 1];
                    matrix[row][0] = 0;
                }
            }
        }
        Random r = new Random();
        int f = 0;
        while (f == 0) {
            int x = r.nextInt(n);
            int y = r.nextInt(n);
            if (matrix[x][y] == 0) {
                matrix[x][y] = 2;
                break;
            }
        }
        ob.setMatrix(matrix);
        return ob;
    } // right() ends

    public GameModel up(GameModel ob) {
        int n = ob.getN();
        int matrix[][] = ob.getMatrix();
        for (int col = 0; col < n; col++) {
            int s = 0, f = 0;
            for (int row = 0; row < n; row++) {
                if (matrix[row][col] != 0) {
                    int temp = matrix[f][col];
                    matrix[f][col] = matrix[s][col];
                    matrix[s][col] = temp;
                    s++;
                    f++;
                } else {
                    f++;
                }
            }

            for (int row = 0; row < n - 1; row++) {
                if (matrix[row][col] == matrix[row + 1][col]) {
                    matrix[row][col] = 2 * matrix[row][col];
                    for (s = row + 1; s < n - 1; s++)
                        matrix[s][col] = matrix[s + 1][col];
                    matrix[n - 1][col] = 0;
                }
            }
        }
        Random r = new Random();
        int f = 0;
        while (f == 0) {
            int x = r.nextInt(n);
            int y = r.nextInt(n);
            if (matrix[x][y] == 0) {
                matrix[x][y] = 2;
                f = 1;
                break;
            }
        }
        return ob;
    } // up() ends

    public GameModel down(GameModel ob) {
        int n = ob.getN();
        int matrix[][] = ob.getMatrix();
        for (int col = 0; col < n; col++) {
            int s = n - 1, f = n - 1;
            for (int row = n - 1; row >= 0; row--) {
                if (matrix[row][col] != 0) {
                    int temp = matrix[f][col];
                    matrix[f][col] = matrix[s][col];
                    matrix[s][col] = temp;
                    s--;
                    f--;
                } else {
                    f--;
                }
            }

            for (int row = n - 1; row > 0; row--) {
                if (matrix[row][col] == matrix[row - 1][col] && matrix[row - 1][col] != 0) {
                    matrix[row][col] = 2 * matrix[row][col];
                    for (s = row - 1; s >= 1; s--)
                        matrix[s][col] = matrix[s - 1][col];
                    matrix[0][col] = 0;
                }
            }
        }
        Random r = new Random();
        int f = 0;
        while (f == 0) {
            int x = r.nextInt(n);
            int y = r.nextInt(n);
            if (matrix[x][y] == 0) {
                matrix[x][y] = 2;
                break;
            }
        }
        ob.setMatrix(matrix);
        return ob;
    } // down() ends


    public void checkFull(GameModel ob) {
        int n = ob.getN();
        int matrix[][] = ob.getMatrix();
        int f = 0;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 128) {
                    System.out.println(" YOU WON ....  :)");
                    System.exit(0);
                }
                if (matrix[i][j] == 0) {
                    f = 1;
                    break;
                }
            }
        if (f == 1)
            System.out.println("Play next chance ");
        else {

            if (movePossible(ob) == true)
                System.out.println("Enter next move..");
            else {
                System.out.println("Game Over ");
                System.exit(0);
            }
        }
    }

    public boolean movePossible(GameModel ob) {
        int n = ob.getN();
        int matrix[][] = ob.getMatrix();
        int f = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - 1; j++) {
                if (matrix[i][j] == matrix[i][j + 1]) {
                    f = 1;
                    break;
                }
            }
            if (f == 1) break;
        }

        for (int j = 0; j < n; j++) {
            for (int i = 0; i < n - 1; i++) {
                if (matrix[i][j] == matrix[i + 1][j]) {
                    f = 1;
                    break;
                }
            }
            if (f == 1) break;
        }
        if (f == 1)
            return true;
        else
            return false;
    }

    public void display(GameModel ob) {
        int n = ob.getN();
        int matrix[][] = ob.getMatrix();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++)
                System.out.print(matrix[i][j] + "\t");
            System.out.println();
        }
    }

    public void saveGame(GameModel ob, String username, String password, DBCollection coll) {
        String progress = "";
        try {
            ObjectMapper mapper = new ObjectMapper();
            progress = mapper.writeValueAsString(ob);
            System.out.println(progress);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        BasicDBObject filter = new BasicDBObject();
        filter.put("username", username);
        BasicDBObject newValue = new BasicDBObject();
        newValue.put("username", username);
        newValue.put("password", password);
        newValue.put("matrix", progress);
        coll.update(filter, newValue);
        System.out.println("Game has been saved ");
    }

    public GameModel loadGame(GameModel ob, String username, DBCollection coll) {
        System.out.println("Game is loading ");
        BasicDBObject query = new BasicDBObject();
        query.put("username", username);
        DBCursor cursor = coll.find(query);
        DBObject value = cursor.next();
        String matrix = value.get("matrix").toString();
        //JsonObject jsonObject = new JsonParser().parse(matrix).getAsJsonObject();

        ObjectMapper mapper = new ObjectMapper();
        try {
            ob = mapper.readValue(matrix, GameModel.class);
            System.out.println("Artist = " + ob.toString());
            display(ob);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Games successfully loaded ");
        display(ob);
        return ob;
    }
}// class ends

