package cpusched;

import java.util.*;

public class TimelineEntry {
    public double start;
    public double end;
    public String pid; // "IDLE" allowed

    public TimelineEntry(double s, double e, String pid) {
        this.start = s; this.end = e; this.pid = pid;
    }

    @Override
    public String toString() {
        return String.format("[ %.0f ] - - %s - - [ %.0f ]", start, pid, end);
    }
}

public class SchedulerResult {
    public List<TimelineEntry> timeline = new ArrayList<>();
    public Map<String, ProcessInfo> processes = new LinkedHashMap<>();
    public int contextSwitches = 0;
}

//Aleyna Zengin