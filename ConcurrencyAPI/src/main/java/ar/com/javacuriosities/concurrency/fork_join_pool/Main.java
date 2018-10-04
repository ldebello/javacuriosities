package ar.com.javacuriosities.concurrency.fork_join_pool;

import java.math.BigInteger;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;

public class Main {

    public static void main(String[] args) {
        ForkJoinPool pool = (ForkJoinPool) Executors.newWorkStealingPool();

        forkNewTasks(pool);

        forkSplittingTasks(pool);
    }

    public static void forkSplittingTasks(ForkJoinPool pool) {
        BigInteger result = pool.invoke(new FactorialTask(100));

        System.out.println("Result: " + result);
    }

    public static void forkNewTasks(ForkJoinPool pool) {
        pool.invoke(new NewUserTask());
    }
}
