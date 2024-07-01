package PrintStrings.MethodOne;

public class PrintAm implements Runnable{
    public final PrintState state;

    public PrintAm(PrintState state){
        this.state = state;
    }

    @Override
    public void run() {
       synchronized (state){
           while(!state.nextTurn.equals(Turn.AM)){
               try {
                   state.wait();
               } catch (InterruptedException e) {
                   throw new RuntimeException(e);
               }
           }

           System.out.println("inside am " + Thread.currentThread().getName());

           state.nextTurn = Turn.ABHAY;
           state.notifyAll();
       }
    }
}
