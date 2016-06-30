package com.example.artjimlop.data.executor;

import com.example.executor.ThreadExecutor;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class JobExecutor implements ThreadExecutor {

  private static final int INITIAL_POOL_SIZE = 3;
  private static final int MAX_POOL_SIZE = 5;
  private static final int KEEP_ALIVE_TIME = 10;
  private static final TimeUnit KEEP_ALIVE_TIME_UNIT = TimeUnit.SECONDS;
  private final ThreadPoolExecutor threadPoolExecutor;

  @Inject
  public JobExecutor() {
    BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>();
    this.threadPoolExecutor = new ThreadPoolExecutor(INITIAL_POOL_SIZE, MAX_POOL_SIZE,
        KEEP_ALIVE_TIME, KEEP_ALIVE_TIME_UNIT, workQueue);
  }

  @Override
  public void execute(Runnable runnable) {
    if (runnable == null) {
      throw new IllegalArgumentException("Runnable to execute cannot be null");
    }
    this.threadPoolExecutor.execute(runnable);
  }
}
