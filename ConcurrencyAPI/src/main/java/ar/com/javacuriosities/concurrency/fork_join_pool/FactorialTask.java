package ar.com.javacuriosities.concurrency.fork_join_pool;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.IntStream;

public class FactorialTask extends RecursiveTask<BigInteger> {
    private static final int THRESHOLD = 20;
    private int start;
    private int n;

    public FactorialTask(int n) {
        this(1, n);
    }

    public FactorialTask(int start, int n) {
        this.start = start;
        this.n = n;
    }
    @Override
    protected BigInteger compute() {
        if ((n - start) >= THRESHOLD) {
            return invokeAll(createSubtasks())
                    .stream()
                    .map(ForkJoinTask::join)
                    .reduce(BigInteger.ONE, BigInteger::multiply);
        } else {
            return calculate(start, n);
        }
    }

    private Collection<FactorialTask> createSubtasks() {
        List<FactorialTask> dividedTasks = new ArrayList<>();
        int mid = (start + n) / 2;
        dividedTasks.add(new FactorialTask(start, mid));
        dividedTasks.add(new FactorialTask(mid + 1, n));
        return dividedTasks;
    }

    private BigInteger calculate(int start, int n) {
        return IntStream.rangeClosed(start, n)
                .mapToObj(BigInteger::valueOf)
                .reduce(BigInteger.ONE, BigInteger::multiply);
    }
}