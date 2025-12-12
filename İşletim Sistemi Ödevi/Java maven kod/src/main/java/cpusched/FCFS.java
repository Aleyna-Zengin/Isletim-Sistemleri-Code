package cpusched;

import java.util.*;

public class FCFS implements Scheduler {
    @Override
    public String getName() { return "FCFS"; }

    @Override
    public SchedulerResult run(List<ProcessInfo> input) {
        List<ProcessInfo> procs = new ArrayList<>();
        for (ProcessInfo p : input) procs.add(p.copy());
        Collections.sort(procs, Comparator.comparingDouble(a -> a.arrival));
        SchedulerResult res = new SchedulerResult();
        double time = 0;
        for (ProcessInfo p : procs) {
            if (time < p.arrival) {
                res.timeline.add(new TimelineEntry(time, p.arrival, "IDLE"));
                time = p.arrival;
            }
            p.startTime = time;
            time += p.burst;
            p.finishTime = time;
            p.waitingTime = p.startTime - p.arrival;
            res.timeline.add(new TimelineEntry(p.startTime, p.finishTime, p.id));
            res.processes.put(p.id, p);
        }
        res.contextSwitches = Math.max(0, procs.size() - 1);
        return res;
    }
}

//Aleyna Zengin
