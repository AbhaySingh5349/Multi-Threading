package DiningPhilosophers;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Fork {
    private int index;
    private Lock lock;

    public Fork(Integer index) {
        this.index = index;
        this.lock = new ReentrantLock(true); // fairness in locking i.e 1st person to (Avoids starvation for threads)
    }

    public int getIndex() {
        return index;
    }

    public Lock getLock() {
        return lock;
    }

    public boolean pickUpTry() {
        return lock.tryLock();
    }

    public void pickUp() {
        lock.lock();
    }

    public void putDown() {
        lock.unlock();
    }
}
