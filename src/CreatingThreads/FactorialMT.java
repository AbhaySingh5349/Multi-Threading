package CreatingThreads;

import java.math.BigInteger;

public class FactorialMT implements Runnable{
    private final int num;

    public FactorialMT(int num){
        this.num = num;
    }

    @Override
    public void run() {
        BigInteger fact = BigInteger.ONE;
        for(int i=1;i<=num;i++){
            fact = fact.multiply(BigInteger.valueOf(i));
        }

        System.out.println(Thread.currentThread().getName() + " for factorial of " + num + " is: " + fact);
    }
}
