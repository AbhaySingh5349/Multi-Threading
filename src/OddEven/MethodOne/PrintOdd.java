package OddEven.MethodOne;

public class PrintOdd implements Runnable{
    private final PrintState state;

    public PrintOdd(PrintState state){
        this.state = state;
    }

    @Override
    public void run() {
        while(state.curNum < 10){
            synchronized (state){
                while(state.curNum % 2 == 0){
                    try {
                        state.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

                System.out.println("Print Odd " + state.curNum + " " + Thread.currentThread().getName());
                state.curNum += 1;

                state.notifyAll();
            }
        }
    }
}
