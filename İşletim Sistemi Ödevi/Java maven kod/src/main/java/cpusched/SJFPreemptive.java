package cpusched;

import java.util.*;

public class SJFPreemptive implements Scheduler {
    @Override public String getName(){return "SJF-Preemptive";}
    @Override
    public SchedulerResult run(List<ProcessInfo> input) {
        List<ProcessInfo> procs = new ArrayList<>();
        for (ProcessInfo p : input) procs.add(p.copy());
        procs.sort(Comparator.comparingDouble(a->a.arrival));
        SchedulerResult res = new SchedulerResult();
        double time = 0;
        PriorityQueue<ProcessInfo> pq = new PriorityQueue<>(Comparator.comparingDouble(a->a.remaining));
        int idx = 0;
        ProcessInfo running = null;
        while (idx < procs.size() || !pq.isEmpty() || running != null) {
            while (idx < procs.size() && procs.get(idx).arrival <= time) {
                pq.add(procs.get(idx)); idx++;
            }
            if (running == null && pq.isEmpty()) {
                if (idx < procs.size()) {
                    double nextArr = procs.get(idx).arrival;
                    res.timeline.add(new TimelineEntry(time, nextArr, "IDLE"));
                    time = nextArr;
                    continue;
                }
                break;
            }
            ProcessInfo next = null;
            if (running == null) {
                next = pq.poll();
            } else {
                pq.add(running);
                next = pq.poll();
            }
            // context switch
            if (running==null || !next.id.equals(running.id)) {
                res.contextSwitches++;
            }
            running = next;
            if (running.startTime < 0) running.startTime = time;

            double nextArrival = idx < procs.size() ? procs.get(idx).arrival : Double.POSITIVE_INFINITY;
            double timeToFinish = running.remaining;
            double runUntil = Math.min(time + timeToFinish, nextArrival);
            res.timeline.add(new TimelineEntry(time, runUntil, running.id));
            double ran = runUntil - time;
            running.remaining -= ran;
            time = runUntil;
            if (running.remaining <= 0.000001) {
                running.finishTime = time;
                running.waitingTime = running.finishTime - running.arrival - running.burst;
                res.processes.put(running.id, running);
                running = null;
            }
        }
        return res;
    }
}
