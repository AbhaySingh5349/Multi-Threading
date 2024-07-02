package ZeroOddEven;

public class Main {
    public static void main(String[] args) {
        PrintState printState = new PrintState(Turn.ZERO);

        PrintSeries zero = new PrintSeries(printState, Turn.ZERO, Turn.ODD, 0, 0, 1);
        PrintSeries odd = new PrintSeries(printState, Turn.ODD, Turn.EVEN, 1, 2, 1);
        PrintSeries even = new PrintSeries(printState, Turn.EVEN, Turn.ZERO, 2, 2, 1);

        Thread t1 = new Thread(zero);
        Thread t2 = new Thread(odd);
        Thread t3 = new Thread(even);

        t1.start();
        t2.start();
        t3.start();
    }
}
