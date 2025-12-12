package cpusched;

import java.util.*;

public interface Scheduler {
    SchedulerResult run(List<ProcessInfo> processes);
    String getName();
}


//Aleyna Zengin