package CreatingThreads.InterfaceThread;

// create class that implements Runnable interface
// implement "run" method
// write logic in "run" method that needs to be executed
// create object of runnable class and assign it to thread
// execute thread by invoking ".start()"

public class TaskUsingRunnable implements Runnable{
    @Override
    public void run() {
        System.out.println("Executing runnable: " + Thread.currentThread().getName());
    }
}
