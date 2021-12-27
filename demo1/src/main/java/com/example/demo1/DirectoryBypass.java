package com.example.demo1;
import java.io.File;

public class DirectoryBypass {
    private File startFile;
    private ExecutorHandler executorHandler = new ExecutorHandler();

    public DirectoryBypass(File startFile) {
        this.startFile = startFile;
        DirOutPut.getOutputDir().recreateDir();
        bypassing(startFile);

    }

    public void bypassing(File dir) {
        File[] children = dir.listFiles();

        for (File child : children) {
            if (child.isDirectory()) {
                bypassing(child);
                executorHandler.execute(child);
            }
        }
    }
}
