package cs3700;

public class Incrementer {

    int counter;
    int n;

    public Incrementer(int n) {
        counter = 2;
        this.n = n;
    }

    public Incrementer() {

    }

    public void increment() {
        counter = counter +1;
    }

    public int getCounter() {
        return counter;
    }

    public int getN() {
        return n;
    }
}
