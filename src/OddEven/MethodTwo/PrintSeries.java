package OddEven.MethodTwo;

public class PrintSeries implements Runnable{
    public final PrintState state;
    public Turn turn;
    public Turn nextTurn;

    public PrintSeries(PrintState state, Turn turn, Turn nextTurn){
        this.state = state;
        this.turn = turn;
        this.nextTurn = nextTurn;
    }

    @Override
    public void run() {
        while (state.curNum < 10){
            synchronized (state){
                if(!state.curTurn.equals(turn)){
                    try {
                        state.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

                System.out.println("num " + state.curNum + " by " + Thread.currentThread().getName());

                state.curTurn = nextTurn;
                state.curNum += 1;
                state.notifyAll();
            }
        }
    }
}
