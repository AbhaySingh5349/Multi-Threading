package DiningPhilosophers;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Fork f1 = new Fork(0);
        Fork f2 = new Fork(1);
        Fork f3 = new Fork(2);
        Fork f4 = new Fork(3);
        Fork f5 = new Fork(4);

        List<Philosopher> philosophers = new ArrayList<>();

        philosophers.add(new Philosopher(f2,f1, 0));
        philosophers.add(new Philosopher(f3,f2,1));
        philosophers.add(new Philosopher(f4,f3,2));
        philosophers.add(new Philosopher(f5,f4,3));
        philosophers.add(new Philosopher(f1,f3,4));

        for(Philosopher p : philosophers){
            Thread t = new Thread(p);
            t.start();
        }
    }
}
