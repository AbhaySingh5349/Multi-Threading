package PrintStrings.MethodOne;

public class Main {
    public static void main(String[] args) {
        PrintState printState = new PrintState(Turn.I);

        PrintI printI = new PrintI(printState);
        PrintAm printAm = new PrintAm(printState);
        PrintAbhay printAbhay = new PrintAbhay(printState);

        Thread t1 = new Thread(printI);
        Thread t2 = new Thread(printAm);
        Thread t3 = new Thread(printAbhay);

        t1.start();
        t2.start();
        t3.start();

        /*
        try {
            // Start the first thread and wait for it to finish
            t1.start();
            t1.join();

            // Start the second thread and wait for it to finish
            t2.start();
            t2.join();

            // Start the third thread and wait for it to finish
            t3.start();
            t3.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        */
    }
}
