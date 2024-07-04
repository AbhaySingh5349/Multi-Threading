package DiningPhilosophers;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Fork {
    private int index;
    private boolean isAvailable;
    private Lock lock;

    public Fork(Integer index) {
        this.index = index;
        this.isAvailable = true;
        this.lock = new ReentrantLock(true); // fairness in locking i.e 1st person to
    }

    public void markForkAvailable(){
        this.isAvailable = true;
    }

    public void markForkUnavailable(){
        this.isAvailable = false;
    }

    public Lock getLock() {
        return lock;
    }
}
