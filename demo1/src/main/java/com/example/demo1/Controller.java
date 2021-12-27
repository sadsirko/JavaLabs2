package com.example.demo1;
import java.io.File;
import java.util.Scanner;

public class Controller {
    public void start() {
        DirectoryBypass bypassing;
        while (true) {

            System.out.println("Enter path to dir where IF -> FI change");
            Scanner scanner = new Scanner(System.in);
            File directory = new File(scanner.nextLine());

            if (!directory.exists())
                System.out.println("Entered unobtainable dir ");

            else {
                bypassing = new DirectoryBypass(directory);
                System.out.println("All changed files are successfully saved in output_dir");
            }
        }
    }

}
