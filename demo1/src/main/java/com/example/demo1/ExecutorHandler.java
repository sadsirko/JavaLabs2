package com.example.demo1;
import java.io.File;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ExecutorHandler {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5,
                10, 30L, TimeUnit.SECONDS,
                new LinkedBlockingDeque<Runnable>());

        public void execute(File directory) {
            threadPoolExecutor.execute(new CSharpFileSearch(directory));
        }
}
