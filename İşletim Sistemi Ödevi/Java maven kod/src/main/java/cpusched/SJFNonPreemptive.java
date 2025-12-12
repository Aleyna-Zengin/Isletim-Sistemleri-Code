package cpusched;

import java.util.*;

public class SJFNonPreemptive implements Scheduler {
    @Override public String getName(){return "SJF-NonPreemptive";}
    @Override
    public SchedulerResult run(List<ProcessInfo> input) {
        List<ProcessInfo> procs = new ArrayList<>();
        for (ProcessInfo p : input) procs.add(p.copy());
        procs.sort(Comparator.comparingDouble(a->a.arrival));
        SchedulerResult res = new SchedulerResult();
        double time = 0;
        PriorityQueue<ProcessInfo> pq = new PriorityQueue<>(Comparator.comparingDouble(a->a.burst));
        int idx = 0;
        while (idx < procs.size() || !pq.isEmpty()) {
            while (idx < procs.size() && procs.get(idx).arrival <= time) {
                pq.add(procs.get(idx)); idx++;
            }
            if (pq.isEmpty()) {
                if (idx < procs.size()) {
                    double nextArr = procs.get(idx).arrival;
                    res.timeline.add(new TimelineEntry(time, nextArr, "IDLE"));
                    time = nextArr;
                    continue;
                }
                break;
            }
            ProcessInfo p = pq.poll();
            p.startTime = time;
            time += p.burst;
            p.finishTime = time;
            p.waitingTime = p.startTime - p.arrival;
            res.timeline.add(new TimelineEntry(p.startTime, p.finishTime, p.id));
            res.processes.put(p.id, p);
        }
        res.contextSwitches = Math.max(0, res.processes.size() - 1);
        return res;
    }
}


//Aleyna Zengin