package ui;

import java.io.FileNotFoundException;

// starts the application
public class Main {
    public static void main(String[] args) {
        new ProteinCounterGUI();
        System.out.println("end");
        // try {
        //     new ProteinCounterApp();
        // } catch (FileNotFoundException e) {
        //     System.out.println("Unable to run application: file not found");
        // }
    }
}
