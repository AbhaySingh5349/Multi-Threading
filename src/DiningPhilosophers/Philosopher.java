package DiningPhilosophers;

import java.time.LocalTime;

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
        int eatingRounds = 3;
        while (eatingRounds-- > 0){
            think();

            // since if any exception occurs after we acquired lock, we should not hold lock infinitely
            try {
                leftFork.pickUp();; // wait till it acquires lock

                // if available, acquires lock else return false and do not wait
                if(rightFork.pickUpTry()){
                    try{
                        eat(); // guarantee that at most 1 philosopher holds both forks at a time
                    }finally {
                        rightFork.putDown();
                    }
                }else{
                    System.out.println("Philosopher " + index + " couldn't pick up right fork " + rightFork.getIndex());
                }
            }finally {
                // Always releases left fork (immediately if unable to acquire "right" else release after executing "eat")
                leftFork.putDown();
            }
        }

        System.out.println("Philosopher " + index + " is done eating");
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
        System.out.println(LocalTime.now() + " → Philosopher " + index + " started eating");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(LocalTime.now() + " → Philosopher " + index + " finished eating");
    }
}
