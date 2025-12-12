package cpusched;

import java.io.*;
import java.util.*;

public class CSVLoader {
    // Expected CSV headers: id,arrival,burst,priority (priority can be high/normal/low)
    public static List<ProcessInfo> load(String path) throws IOException {
        List<ProcessInfo> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            boolean first = true;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                if (first && line.toLowerCase().contains("process_id") && line.toLowerCase().contains("arrival")) {
                    first = false; // skip header
                    continue;
                }
                String[] parts = line.split(",");
                if (parts.length < 3) continue;
                String id = parts[0].trim();
                double arrival = Double.parseDouble(parts[1].trim());
                double burst = Double.parseDouble(parts[2].trim());
                int priority = 1; // default normal
                if (parts.length >= 4) {
                    String p = parts[3].trim().toLowerCase();
                    if (p.startsWith("h")) priority = 0;
                    else if (p.startsWith("n")) priority = 1;
                    else priority = 2;
                }
                list.add(new ProcessInfo(id, arrival, burst, priority));
            }
        }
        return list;
    }
}

//Aleyna Zengin//
