package com.example.demo1;
import java.io.File;

public class CSharpFileSearch implements Runnable{

    private File directory;

    public CSharpFileSearch(File directory) {
        this.directory = directory;
    }

    @Override
    public void run() {
        for (File file : directory.listFiles()) {
            if (file.getName().endsWith(".cs"))
                new CSharpFileParser(file).changeIfToFi();
        }
    }
}
