package com.example.demo1;
import java.io.File;

public class DirectoryBypass {

    private ExecutorHandler executorHandler = new ExecutorHandler();

    public DirectoryBypass(File startFile) {
        DirOutPut.getOutputDir().recreateDir();
        executorHandler.execute(startFile);
        bypassing(startFile);

    }

    public void bypassing(File dir) {
        File[] children = dir.listFiles();
        for (File child : children) {
            if (child.isDirectory()) {
                executorHandler.execute(child);
                bypassing(child);
            }
        }
    }
}
