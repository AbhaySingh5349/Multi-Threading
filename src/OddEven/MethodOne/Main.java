package OddEven.MethodOne;

public class Main {
    public static void main(String[] args) {
        PrintState state = new PrintState(1);

        PrintOdd odd = new PrintOdd(state);
        PrintEven even = new PrintEven(state);

        Thread t1 = new Thread(odd);
        Thread t2 = new Thread(even);

        t1.start();
        t2.start();
    }
}
