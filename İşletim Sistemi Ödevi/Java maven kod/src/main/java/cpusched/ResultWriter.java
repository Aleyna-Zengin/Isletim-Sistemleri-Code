package cpusched;

import java.io.*;
import java.util.*;

public class ResultWriter {
    public static void write(String outPrefix, SchedulerResult res) throws IOException {
        String fn = outPrefix + "_timeline.txt";
        try (PrintWriter pw = new PrintWriter(new FileWriter(fn))) {
            for (TimelineEntry e : res.timeline) pw.println(e.toString());
        }
        String stats = outPrefix + "_stats.txt";
        try (PrintWriter pw = new PrintWriter(new FileWriter(stats))) {
            Collection<ProcessInfo> list = res.processes.values();
            double totalWaiting = 0; double totalTurn = 0; double maxWait = 0; double maxTurn = 0;
            for (ProcessInfo p : list) {
                double turn = p.finishTime - p.arrival;
                totalWaiting += p.waitingTime; totalTurn += turn;
                maxWait = Math.max(maxWait, p.waitingTime);
                maxTurn = Math.max(maxTurn, turn);
            }
            int n = list.size();
            pw.printf("Processes: %d\n", n);
            pw.printf("Avg Waiting: %.4f\n", n>0 ? totalWaiting/n : 0);
            pw.printf("Max Waiting: %.4f\n", maxWait);
            pw.printf("Avg Turnaround: %.4f\n", n>0 ? totalTurn/n : 0);
            pw.printf("Max Turnaround: %.4f\n", maxTurn);
            pw.printf("Context Switches: %d\n", res.contextSwitches);

            int[] Ts = {50,100,150,200};
            for (int T : Ts) {
                int cnt = 0;
                for (ProcessInfo p : list) if (p.finishTime <= T) cnt++;
                pw.printf("Throughput T=%d: %d\n", T, cnt);
            }

            double lastFinish = 0; for (TimelineEntry e: res.timeline) lastFinish = Math.max(lastFinish, e.end);
            double busy = 0; for (TimelineEntry e: res.timeline) if (!e.pid.equals("IDLE")) busy += (e.end - e.start);
            double total = Math.max(lastFinish, 1.0);
            double efficiency = busy / total;
            double csOverhead = res.contextSwitches * 0.001;
            double effWithCS = busy / (total + csOverhead);
            pw.printf("CPU Busy Time: %.4f\n", busy);
            pw.printf("CPU Total Time: %.4f\n", total);
            pw.printf("Avg CPU Efficiency (no CS): %.6f\n", efficiency);
            pw.printf("Avg CPU Efficiency (with CS overhead 0.001/unit): %.6f\n", effWithCS);
        }
    }
}


//Aleyna Zengin