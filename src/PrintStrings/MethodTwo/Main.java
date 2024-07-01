package PrintStrings.MethodTwo;

public class Main {
    public static void main(String[] args) {
        PrintState printState = new PrintState("i");

        PrintWord w1 = new PrintWord(printState, "i", "am");
        PrintWord w2 = new PrintWord(printState, "am", "abhay");
        PrintWord w3 = new PrintWord(printState, "abhay", "done");

        Thread t1 = new Thread(w1);
        Thread t2 = new Thread(w2);
        Thread t3 = new Thread(w3);

        t1.start();
        t2.start();
        t3.start();
    }
}
