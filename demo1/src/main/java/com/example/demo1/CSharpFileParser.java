package com.example.demo1;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSharpFileParser {
    private File file;
    private File outputDir;

    public CSharpFileParser(File file) {
        this.file = file;
        outputDir = DirOutPut.getOutputDir().outputDir;
        System.out.println("Changed file " + file.getAbsolutePath());
    }


    private List<String> getFileAsStringLines() {
        List<String> stringFile = new ArrayList<>();

        try {
            FileReader reader = new FileReader(file);
            Scanner scanner = new Scanner(reader);
            while (scanner.hasNextLine())
                stringFile.add(scanner.nextLine());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return stringFile;
    }

    public File changeIfToFi() {
        List<String> stringFile = getFileAsStringLines();
        File tmp = new File(outputDir, file.getName());

        try {
            tmp.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileWriter writer = new FileWriter(tmp)) {

            for (String line : stringFile) {
                    line = line.replace("if (", "FI (");
                writer.write(line + "\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }
}
