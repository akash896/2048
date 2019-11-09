package Game;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.*;
import conection.MongoConnection;

import java.io.IOException;
import java.io.Serializable;
import java.util.Random;

public class Game2048 implements Serializable {
    public int matrix[][];
    public int n;
    public int status;

    public Game2048() {
    }

    public Game2048(int[][] matrix, int n, int status) {
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
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Game2048(int n) {
        matrix = new int[n][n];
        this.n = n;
        this.status = 0;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                matrix[i][j] = 0;
    }

    public Game2048 left(Game2048 ob) {

        for (int row = 0; row < ob.n; row++) {
            int s = 0, f = 0;
            for (int col = 0; col <= ob.n - 1; col++) {
                if (ob.matrix[row][col] != 0) {
                    int temp = ob.matrix[row][f];
                    ob.matrix[row][f] = ob.matrix[row][s];
                    ob.matrix[row][s] = temp;
                    s++;
                    f++;
                } else {
                    f++;
                }
            }

            for (int col = 0; col < ob.n - 1; col++) {
                if (ob.matrix[row][col] == ob.matrix[row][col + 1] && ob.matrix[row][col] != 0) {
                    ob.matrix[row][col] = 2 * ob.matrix[row][col];
                    for (s = col + 1; s < ob.n - 1; s++)
                        ob.matrix[row][s] = ob.matrix[row][s + 1];
                    ob.matrix[row][ob.n - 1] = 0;
                }
            }
        }

        Random r = new Random();
        int f = 0;
        while (f == 0) {
            int x = r.nextInt(ob.n);
            int y = r.nextInt(ob.n);
            if (ob.matrix[x][y] == 0) {
                ob.matrix[x][y] = 2;
                f = 1;
                break;
            }
        }
        return ob;
    } //left() ends

    public Game2048 right(Game2048 ob) {
        for (int row = 0; row < ob.n; row++) {
            int s = ob.n - 1, f = ob.n - 1;
            for (int col = ob.n - 1; col >= 0; col--) {
                if (ob.matrix[row][col] != 0) {
                    int temp = ob.matrix[row][f];
                    ob.matrix[row][f] = ob.matrix[row][s];
                    ob.matrix[row][s] = temp;
                    s--;
                    f--;
                } else {
                    f--;
                }
            }

            for (int col = ob.n - 1; col > 0; col--) {
                if (ob.matrix[row][col] == matrix[row][col - 1] && ob.matrix[row][col] != 0) {
                    ob.matrix[row][col] = 2 * ob.matrix[row][col];
                    for (s = col - 1; s >= 1; s--)
                        ob.matrix[row][s] = ob.matrix[row][s - 1];
                    ob.matrix[row][0] = 0;
                }
            }
        }
        Random r = new Random();
        int f = 0;
        while (f == 0) {
            int x = r.nextInt(ob.n);
            int y = r.nextInt(ob.n);
            if (ob.matrix[x][y] == 0) {
                ob.matrix[x][y] = 2;
                break;
            }
        }
        return ob;
    } // right() ends

    public Game2048 up(Game2048 ob) {
        for (int col = 0; col < ob.n; col++) {
            int s = 0, f = 0;
            for (int row = 0; row < ob.n; row++) {
                if (ob.matrix[row][col] != 0) {
                    int temp = ob.matrix[f][col];
                    ob.matrix[f][col] = ob.matrix[s][col];
                    ob.matrix[s][col] = temp;
                    s++;
                    f++;
                } else {
                    f++;
                }
            }

            for (int row = 0; row < ob.n - 1; row++) {
                if (ob.matrix[row][col] == matrix[row + 1][col]) {
                    ob.matrix[row][col] = 2 * ob.matrix[row][col];
                    for (s = row + 1; s < ob.n - 1; s++)
                        ob.matrix[s][col] = ob.matrix[s + 1][col];
                    ob.matrix[ob.n - 1][col] = 0;
                }
            }
        }
        Random r = new Random();
        int f = 0;
        while (f == 0) {
            int x = r.nextInt(ob.n);
            int y = r.nextInt(ob.n);
            if (ob.matrix[x][y] == 0) {
                ob.matrix[x][y] = 2;
                f = 1;
                break;
            }
        }
        return ob;
    } // up() ends

    public Game2048 down(Game2048 ob) {
        for (int col = 0; col < ob.n; col++) {
            int s = ob.n - 1, f = ob.n - 1;
            for (int row = ob.n - 1; row >= 0; row--) {
                if (ob.matrix[row][col] != 0) {
                    int temp = ob.matrix[f][col];
                    ob.matrix[f][col] = ob.matrix[s][col];
                    ob.matrix[s][col] = temp;
                    s--;
                    f--;
                } else {
                    f--;
                }
            }

            for (int row = ob.n - 1; row > 0; row--) {
                if (ob.matrix[row][col] == ob.matrix[row - 1][col] && ob.matrix[row - 1][col] != 0) {
                    ob.matrix[row][col] = 2 * ob.matrix[row][col];
                    for (s = row - 1; s >= 1; s--)
                        ob.matrix[s][col] = ob.matrix[s - 1][col];
                    ob.matrix[0][col] = 0;
                }
            }
        }
        Random r = new Random();
        int f = 0;
        while (f == 0) {
            int x = r.nextInt(ob.n);
            int y = r.nextInt(ob.n);
            if (ob.matrix[x][y] == 0) {
                ob.matrix[x][y] = 2;
                break;
            }
        }
        return ob;
    } // down() ends


    public void checkFull(Game2048 ob) {
        int f = 0;
        for (int i = 0; i < ob.n; i++)
            for (int j = 0; j < ob.n; j++) {
                if (ob.matrix[i][j] == 32) {
                    System.out.println(" YOU WON ....  :)");
                    System.exit(0);
                }
                if (ob.matrix[i][j] == 0) {
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
                ob.status = 1;
                System.out.println("Game Over ");
                System.exit(0);
            }
        }
    }

    public boolean movePossible(Game2048 ob) {
        int f = 0;
        for (int i = 0; i < ob.n; i++) {
            for (int j = 0; j < ob.n - 1; j++) {
                if (ob.matrix[i][j] == ob.matrix[i][j + 1]) {
                    f = 1;
                    break;
                }
            }
            if (f == 1) break;
        }

        for (int j = 0; j < ob.n; j++) {
            for (int i = 0; i < ob.n - 1; i++) {
                if (ob.matrix[i][j] == ob.matrix[i + 1][j]) {
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

    public void display(Game2048 ob) {
        for (int i = 0; i < ob.n; i++) {
            for (int j = 0; j < ob.n; j++)
                System.out.print(ob.matrix[i][j] + "\t");
            System.out.println();
        }
    }

    public void saveGame(Game2048 ob, String username, String password) {
        String progress = "";
        try {
            ObjectMapper mapper = new ObjectMapper();
            progress = mapper.writeValueAsString(ob);
            System.out.println(progress);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        DBCollection coll = new MongoConnection().loadCollection("login");
        BasicDBObject filter = new BasicDBObject();
        filter.put("username", username);
        BasicDBObject newValue = new BasicDBObject();
        newValue.put("username", username);
        newValue.put("password", password);
        newValue.put("matrix", progress);
        coll.update(filter, newValue);

        System.out.println("Game has been saved ");
    }

    public Game2048 loadGame(Game2048 ob, String username) {
        System.out.println("Game is loading ");
        MongoConnection con = new MongoConnection();
        DB db = con.createConnection();
        // Fetching the collection from the mongodb -> testdb.
        DBCollection coll = db.getCollection("login");
        BasicDBObject query = new BasicDBObject();
        query.put("username", username);
        DBCursor cursor = coll.find(query);
        DBObject value = cursor.next();
        String matrix = value.get("matrix").toString();
        //JsonObject jsonObject = new JsonParser().parse(matrix).getAsJsonObject();

        ObjectMapper mapper = new ObjectMapper();
        try {
            ob = mapper.readValue(matrix, Game2048.class);
            System.out.println("Artist = " + ob);
            ob.display(ob);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Games successfully loaded ");
        ob.display(ob);
        return ob;
    }

}// class ends

