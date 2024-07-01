package PrintStrings.MethodOne;

public class PrintI implements Runnable {
    public final PrintState state;

    public PrintI(PrintState state){
        this.state = state;
    }

    @Override
    public void run() {
        synchronized (state){
            while (!state.nextTurn.equals(Turn.I)){
                try {
                    state.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("inside printI " + Thread.currentThread().getName());

            state.nextTurn = Turn.AM;
            state.notifyAll();
        }
    }
}
