package ZeroOddEven;

public class PrintSeries implements Runnable{
    public final PrintState printState;
    public Turn curTurn;
    public Turn nextTurn;
    public int num;
    public int increment;
    public int count;
    
    public PrintSeries(PrintState printState, Turn curTurn, Turn nextTurn, int num, int increment, int count){
        this.printState = printState;
        this.curTurn = curTurn;
        this.nextTurn = nextTurn;
        this.num = num;
        this.increment = increment;
        this.count = count;
    }


    @Override
    public void run() {
        while (this.count <= 5){
            synchronized (printState){
                while (!printState.turn.equals(curTurn)){
                    try {
                        printState.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

                System.out.println("num " + num + " " + Thread.currentThread().getName());

                this.num += increment;
                this.count++;

                printState.turn = nextTurn;
                printState.notifyAll();
            }
        }
    }
}
