package OddEven.MethodOne;

public class PrintEven implements Runnable{
    private final PrintState state;

    public PrintEven(PrintState state){
        this.state = state;
    }

    @Override
    public void run() {
        while(state.curNum < 10){
            synchronized (state){
                while(state.curNum % 2 == 1){
                    try {
                        state.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

                System.out.println("Print Even " + state.curNum);
                state.curNum += 1;

                state.notifyAll();
            }
        }
    }
}
