package DiningPhilosophers;

public class Philosopher implements Runnable{
    private final Fork leftFork;
    private final Fork rightFork;
    private final Integer index;

    public Philosopher(Fork leftFork, Fork rightFork, Integer index){
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        this.index = index;
    }

    @Override
    public void run() {
        while (true){
            think();

            // since if any exception occurs after we acquired lock, we should not hold lock infinitely
            try {
                leftFork.getLock().lock(); // wait till it acquires lock

                // if available, acquires lock else return false and do not wait
                if(rightFork.getLock().tryLock()){
                    try{
                        eat();
                    }finally {
                        rightFork.getLock().unlock();
                    }
                }
            }finally {
                leftFork.getLock().unlock();
            }
        }
    }

    public void think(){
        System.out.println("Philosopher " + index + " is thinking");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void eat(){
        System.out.println("Philosopher " + index + " started eating");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Philosopher " + index + " finished eating");
    }
}
