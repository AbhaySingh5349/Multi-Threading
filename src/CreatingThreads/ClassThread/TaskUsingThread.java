package CreatingThreads.ClassThread;

// create class that extends Thread class
// override "run" method
// write logic in "run" method that needs to be executed
// create object of class
// execute thread by invoking ".start()"

public class TaskUsingThread extends Thread{
    @Override
    public void run() {
        System.out.println("Executing thread class: " + Thread.currentThread().getName());
    }
}
