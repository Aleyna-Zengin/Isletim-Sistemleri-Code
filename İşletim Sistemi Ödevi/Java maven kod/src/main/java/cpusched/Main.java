package cpusched;

import java.nio.file.*;
import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        if (args.length < 2) {
            System.out.println("Usage: java -cp target/classes cpusched.Main <input.csv> <output-prefix> [quantum]"); 
            System.out.println("Example: mvn compile && java -cp target/classes cpusched.Main src/main/resources/case1.csv results/case1 4");
            return;
        }
        String csv = args[0];
        String outp = args[1];
        double quantum = args.length >=3 ? Double.parseDouble(args[2]) : 4.0;
        List<ProcessInfo> list = CSVLoader.load(csv);

        List<Scheduler> scheds = new ArrayList<>();
        scheds.add(new FCFS());
        scheds.add(new SJFPreemptive());
        scheds.add(new SJFNonPreemptive());
        scheds.add(new RoundRobin(quantum));
        scheds.add(new PriorityPreemptive());
        scheds.add(new PriorityNonPreemptive());

        // create output dir
        Path outDir = Paths.get(outp);
        if (!Files.exists(outDir)) Files.createDirectories(outDir);

        // run each scheduler sequentially and write results
        for (Scheduler s : scheds) {
            SchedulerResult res = s.run(list);
            String prefix = outp + "/" + s.getName().replace(' ','_').replace('/','_');
            ResultWriter.write(prefix, res);
            System.out.println("Wrote results for: " + s.getName());
        }
    }
}


//Aleyna Zengin