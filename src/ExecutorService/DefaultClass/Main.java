package ExecutorService.DefaultClass;

import CreatingThreads.InterfaceThread.TaskUsingRunnable;

import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
//        TaskUsingRunnable taskUsingRunnable = new TaskUsingRunnable();

//        ExecutorService es1 = Executors.newFixedThreadPool(4); // max 4 threads will serve requests
//        ExecutorService es2 = Executors.newCachedThreadPool();
//
//        for(int i=0;i<50;i++){
//            es2.submit(taskUsingRunnable);
//        }

        Runnable task = new Runnable() {
            @Override
            public void run() {
                System.out.println("Runnable task from main: " + Thread.currentThread().getName());
            }
        };

        ScheduledExecutorService es3 = Executors.newScheduledThreadPool(1);

        es3.shutdownNow();

        es3.submit(task);
        es3.schedule(task, 1, TimeUnit.SECONDS);

        System.out.println("done in main thread: " + Thread.currentThread().getName());

        es3.shutdown();

//        es3.shutdownNow();
    }
}
