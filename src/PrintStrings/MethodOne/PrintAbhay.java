package PrintStrings.MethodOne;

public class PrintAbhay implements Runnable{
    public final PrintState state;

    public PrintAbhay(PrintState state){
        this.state = state;
    }

    @Override
    public void run() {
        synchronized (state){
            while(!state.nextTurn.equals(Turn.ABHAY)){
                try {
                    state.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            System.out.println("inside Abhay " + Thread.currentThread().getName());

        }
    }
}
