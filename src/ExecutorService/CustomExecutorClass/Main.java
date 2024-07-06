package ExecutorService.CustomExecutorClass;

import CreatingThreads.InterfaceThread.TaskUsingRunnable;

import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        ExecutorService es = new ThreadPoolExecutor(2, 4, 30, TimeUnit.SECONDS, new LinkedBlockingQueue<>());

        TaskUsingRunnable taskUsingRunnable = new TaskUsingRunnable();

        for(int i=0;i<10;i++){
            try{
                es.submit(taskUsingRunnable);
            }catch (Exception e){
                System.out.println("Exception " + e.getMessage());
            }
        }

        try {
            es.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        es.shutdown();

        System.out.println("In main func: " + Thread.currentThread().getName());
    }
}
