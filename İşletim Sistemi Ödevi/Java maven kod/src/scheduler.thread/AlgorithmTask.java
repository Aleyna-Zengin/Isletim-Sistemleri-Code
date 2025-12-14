package scheduler.thread;

public class AlgorithmTask implements Runnable {

    private final String algorithmName;
    private final String caseFilePath;

    public AlgorithmTask(String algorithmName, String caseFilePath) {
        this.algorithmName = algorithmName;
        this.caseFilePath = caseFilePath;
    }

    @Override
    public void run() {

        System.out.println(
            "[" + Thread.currentThread().getName() + "] "
            + algorithmName + " STARTED (" + caseFilePath + ")"
        );

        switch (algorithmName) {
            case "FCFS":
                FCFS.run(caseFilePath);
                break;

            case "SJF_PREEMPTIVE":
                SJFPreemptive.run(caseFilePath);
                break;

            case "SJF_NON_PREEMPTIVE":
                SJFNPreemptive.run(caseFilePath);
                break;

            case "RR":
                RoundRobin.run(caseFilePath, 4);
                break;

            case "PRIORITY_PREEMPTIVE":
                PriorityPreemptive.run(caseFilePath);
                break;

            case "PRIORITY_NON_PREEMPTIVE":
                PriorityNonPreemptive.run(caseFilePath);
                break;
        }

        System.out.println(
            "[" + Thread.currentThread().getName() + "] "
            + algorithmName + " FINISHED"
        );
    }
}

//Aleyna Zengin