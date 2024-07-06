package ExecutorService.ParallelExecutionExample;

public class Vistara implements Runnable{
    @Override
    public void run() {
        try {
            Thread.sleep(1500);
            System.out.println("Vistara response " + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
