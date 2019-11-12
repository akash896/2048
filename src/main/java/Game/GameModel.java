package Game;

import java.io.Serializable;
import java.util.Arrays;

public class GameModel implements Serializable {
    private int matrix[][];
    private int n;

    public GameModel() {
    }

    public GameModel(int[][] matrix, int n) {
        this.matrix = matrix;
        this.n = n;
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


    @Override
    public String toString() {
        return "GameModel{" +
                "matrix=" + Arrays.toString(matrix) +
                ", n=" + n +
                '}';
    }
    }
