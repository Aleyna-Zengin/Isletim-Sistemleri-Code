package cpusched;

import java.util.*;

public class PriorityPreemptive implements Scheduler {
    @Override public String getName(){return "Priority-Preemptive";}
    @Override
    public SchedulerResult run(List<ProcessInfo> input) {
        List<ProcessInfo> procs = new ArrayList<>();
        for (ProcessInfo p : input) procs.add(p.copy());
        procs.sort(Comparator.comparingDouble(a->a.arrival));
        SchedulerResult res = new SchedulerResult();
        double time = 0;
        PriorityQueue<ProcessInfo> pq = new PriorityQueue<>(Comparator.comparingInt(a->a.priority));
        int idx = 0;
        ProcessInfo running = null;
        while (idx < procs.size() || !pq.isEmpty() || running != null) {
            while (idx < procs.size() && procs.get(idx).arrival <= time) { pq.add(procs.get(idx)); idx++; }
            if (running == null && pq.isEmpty()) {
                if (idx < procs.size()) { double na = procs.get(idx).arrival; res.timeline.add(new TimelineEntry(time, na, "IDLE")); time = na; continue; }
                break;
            }
            ProcessInfo next;
            if (running == null) next = pq.poll(); else { pq.add(running); next = pq.poll(); }
            res.contextSwitches++;
            running = next;
            if (running.startTime < 0) running.startTime = time;
            double nextArrival = idx < procs.size() ? procs.get(idx).arrival : Double.POSITIVE_INFINITY;
            double runUntil = Math.min(time + running.remaining, nextArrival);
            res.timeline.add(new TimelineEntry(time, runUntil, running.id));
            double ran = runUntil - time;
            running.remaining -= ran;
            time = runUntil;
            if (running.remaining <= 0.000001) { running.finishTime = time; running.waitingTime = running.finishTime - running.arrival - running.burst; res.processes.put(running.id, running); running = null; }
        }
        return res;
    }
}


//Aleyna Zengin