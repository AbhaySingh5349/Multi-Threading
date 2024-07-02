package OddEvenFibonacci;

public class Main {
    public static void main(String[] args) {
        PrintState printState = new PrintState(Turn.ODD);

        PrintOdd odd = new PrintOdd(printState, Turn.ODD, Turn.EVEN, 1, 1);
        PrintEven even = new PrintEven(printState, Turn.EVEN, Turn.FIBONACCI, 1, 2);
        PrintFibonacci fibonacci = new PrintFibonacci(printState, Turn.FIBONACCI, Turn.ODD, 1, 0, 1);

        Thread t1 = new Thread(odd);
        Thread t2 = new Thread(even);
        Thread t3 = new Thread(fibonacci);

        t1.start();
        t2.start();
        t3.start();
    }
}
