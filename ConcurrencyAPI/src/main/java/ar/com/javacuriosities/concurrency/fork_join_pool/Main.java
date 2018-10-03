package ar.com.javacuriosities.concurrency.fork_join_pool;

import java.math.BigInteger;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;

public class Main {

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = (ForkJoinPool) Executors.newWorkStealingPool();

        BigInteger result = forkJoinPool.invoke(new FactorialTask(100));

        System.out.println("Result: " + result);
    }
}
