package OddEven.MethodTwo;

public class Main {
    public static void main(String[] args) {
        PrintState printState = new PrintState(Turn.ODD, 1);

        PrintSeries oddSeries = new PrintSeries(printState, Turn.ODD, Turn.EVEN);
        PrintSeries evenSeries = new PrintSeries(printState, Turn.EVEN, Turn.ODD);

        Thread t1 = new Thread(oddSeries);
        Thread t2 = new Thread(evenSeries);

        t1.start();
        t2.start();
    }
}
