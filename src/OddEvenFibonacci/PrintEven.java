package OddEvenFibonacci;

public class PrintEven implements Runnable{
    public final PrintState printState;
    public Turn curTurn;
    public Turn nextTurn;
    public int count;
    public int num;

    public PrintEven(PrintState printState, Turn curTurn, Turn nextTurn, int count, int num){
        this.printState = printState;
        this.curTurn = curTurn;
        this.nextTurn = nextTurn;
        this.count = count;
        this.num = num;
    }

    @Override
    public void run() {
        while (count <= 5){
            synchronized (printState){
                while(!printState.turn.equals(curTurn)){
                    try {
                        printState.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

                System.out.println("even " + num + " " + Thread.currentThread().getName());

                this.num += 2;
                this.count++;

                printState.turn = nextTurn;
                printState.notifyAll();
            }
        }
    }
}
