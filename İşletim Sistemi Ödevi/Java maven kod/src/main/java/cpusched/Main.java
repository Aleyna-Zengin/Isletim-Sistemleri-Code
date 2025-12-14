package cpusched;

import scheduler.thread.AlgorithmTask;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(6);

        executor.submit(new AlgorithmTask("FCFS", "src/main/resources/case1.csv"));
        executor.submit(new AlgorithmTask("SJF_PREEMPTIVE", "src/main/resources/case1.csv"));
        executor.submit(new AlgorithmTask("SJF_NON_PREEMPTIVE", "src/main/resources/case1.csv"));
        executor.submit(new AlgorithmTask("RR", "src/main/resources/case1.csv"));
        executor.submit(new AlgorithmTask("PRIORITY_PREEMPTIVE", "src/main/resources/case1.csv"));
        executor.submit(new AlgorithmTask("PRIORITY_NON_PREEMPTIVE", "src/main/resources/case1.csv"));

        executor.shutdown();
    }
}

//Aleyna Zengin
