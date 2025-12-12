package cpusched;

import java.util.*;

public class RoundRobin implements Scheduler {
    private double quantum;
    public RoundRobin(double quantum) { this.quantum = quantum; }
    @Override public String getName(){return "RoundRobin(q="+quantum+")";}
    @Override
    public SchedulerResult run(List<ProcessInfo> input) {
        List<ProcessInfo> procs = new ArrayList<>();
        for (ProcessInfo p : input) procs.add(p.copy());
        procs.sort(Comparator.comparingDouble(a->a.arrival));
        SchedulerResult res = new SchedulerResult();
        double time = 0;
        Queue<ProcessInfo> q = new LinkedList<>();
        int idx=0;
        while (idx < procs.size() || !q.isEmpty()) {
            while (idx < procs.size() && procs.get(idx).arrival <= time) { q.add(procs.get(idx)); idx++; }
            if (q.isEmpty()) {
                if (idx < procs.size()) {
                    double nextArr = procs.get(idx).arrival;
                    res.timeline.add(new TimelineEntry(time, nextArr, "IDLE"));
                    time = nextArr; continue;
                }
                break;
            }
            ProcessInfo p = q.poll();
            if (p.startTime < 0) p.startTime = time;
            double run = Math.min(quantum, p.remaining);
            res.timeline.add(new TimelineEntry(time, time+run, p.id));
            time += run; p.remaining -= run;
            while (idx < procs.size() && procs.get(idx).arrival <= time) { q.add(procs.get(idx)); idx++; }
            if (p.remaining > 0) { q.add(p); }
            else { p.finishTime = time; p.waitingTime = p.finishTime - p.arrival - p.burst; res.processes.put(p.id, p); }
            res.contextSwitches++;
        }
        return res;
    }
}


//Aleyna Zengin