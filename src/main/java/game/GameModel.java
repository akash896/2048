package game;

import java.io.Serializable;
import java.util.Arrays;

public class GameModel implements Serializable {
    private int matrix[][];
    private int n;
    private int status;

    public GameModel() {
    }

    public GameModel(int[][] matrix, int n, int status) {
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

    @Override
    public String toString() {
        return "GameModel{" +
                "matrix=" + Arrays.toString(matrix) +
                ", n=" + n +
                ", status=" + status +
                '}';
    }
}
