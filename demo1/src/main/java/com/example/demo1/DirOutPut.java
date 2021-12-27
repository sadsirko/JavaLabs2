package com.example.demo1;

import java.io.File;

public class DirOutPut {
    private static DirOutPut outPutDir = new DirOutPut();
    public File outputDir = new File("output_dir");

    public static DirOutPut getOutputDir() {
        return outPutDir;
    }

    private DirOutPut() {
    }

    public File recreateDir() {

        if (outputDir.exists()) {
            for (File tmp : outputDir.listFiles())
                tmp.delete();
            boolean result = outputDir.delete();
            if (result) System.out.println(" output dir is Cleaned");

        }

        outputDir.mkdir();
        return outputDir;
    }
}
