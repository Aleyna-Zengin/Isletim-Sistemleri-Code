package cpusched;

public class ProcessInfo implements Comparable<ProcessInfo> {
    public String id;
    public double arrival;
    public double burst;
    public int priority;

    // runtime fields
    public double remaining;
    public double startTime = -1;
    public double finishTime = -1;
    public double waitingTime = 0;

    public ProcessInfo(String id, double arrival, double burst, int priority) {
        this.id = id;
        this.arrival = arrival;
        this.burst = burst;
        this.priority = priority;
        this.remaining = burst;
    }

    @Override
    public int compareTo(ProcessInfo o) {
        int c = Double.compare(this.arrival, o.arrival);
        if (c != 0) return c;
        return this.id.compareTo(o.id);
    }

    public ProcessInfo copy() {
        ProcessInfo p = new ProcessInfo(id, arrival, burst, priority);
        return p;
    }
}


//Aleyna Zengin