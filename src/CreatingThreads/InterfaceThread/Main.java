package CreatingThreads.InterfaceThread;

public class Main {
    public static void main(String[] args) {
        TaskUsingRunnable taskUsingRunnable = new TaskUsingRunnable();

        System.out.println("Main method: " + Thread.currentThread().getName());

        Thread t = new Thread(taskUsingRunnable);
        t.start();
    }
}
