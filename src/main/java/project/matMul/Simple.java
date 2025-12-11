package project.matMul;

import project.Rnd;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Simple implements IMatrix{

    private final int size;
    private final double[][] a;
    private final double[][] b;
    private final double[][] c;

    public Simple(Rnd rnd, int size) {
        this.size = size;
        this.a = new double[size][];
        this.b = new double[size][];
        this.c = new double[size][size];
        IntStream.range(0, size).parallel().forEach(r -> {
            this.a[r] = rnd.fill(size);
            this.b[r] = rnd.fill(size);
        });
    }

    @Override
    public void multiply() {
        if(size > 8000) return;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                double s = 0;
                for (int k = 0; k < size; k++) {
                    s += a[i][k] * b[k][j];
                }
                c[i][j] = s;
            }
        }
    }

    @Override
    public void clearC(){
        for(int i = 0; i < size; i++){
            Arrays.fill(c[i], 0.0);
        }
    }

    @Override
    public double peek() {
        return c[0][0];
    }
}