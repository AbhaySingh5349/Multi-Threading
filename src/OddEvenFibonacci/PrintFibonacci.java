package OddEvenFibonacci;

public class PrintFibonacci implements Runnable{
    public final PrintState printState;
    public Turn curTurn;
    public Turn nextTurn;
    public int count;
    public int prePrevNum;
    public int prevNum;

    public PrintFibonacci(PrintState printState, Turn curTurn, Turn nextTurn, int count, int prePrevNum, int prevNum){
        this.printState = printState;
        this.curTurn = curTurn;
        this.nextTurn = nextTurn;
        this.count = count;
        this.prePrevNum = prePrevNum;
        this.prevNum = prevNum;
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

                int num = prePrevNum + prevNum;
                System.out.println("fibonacci " + num + " " + Thread.currentThread().getName());

                this.prePrevNum = this.prevNum;
                this.prevNum = num;
                this.count++;

                printState.turn = nextTurn;
                printState.notifyAll();
            }
        }
    }
}
