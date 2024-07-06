package ExecutorService.ParallelExecutionExample;

public class Indigo implements Runnable{
    @Override
    public void run() {
        try {
            Thread.sleep(1500);
            System.out.println("Indigo response " + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
