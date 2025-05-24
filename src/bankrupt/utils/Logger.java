package bankrupt.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    private static final boolean VERBOSE = false; // Altere para true para ver logs detalhados
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    
    public static void log(String message) {
        if (VERBOSE) {
            String timestamp = LocalDateTime.now().format(formatter);
            System.out.println("[" + timestamp + "] " + message);
        }
    }
    
    public static void logAlways(String message) {
        String timestamp = LocalDateTime.now().format(formatter);
        System.out.println("[" + timestamp + "] " + message);
    }
}