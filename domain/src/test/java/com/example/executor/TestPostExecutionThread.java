package com.example.executor;

public class TestPostExecutionThread implements PostExecutionThread {
  @Override public void post(Runnable runnable) {
    runnable.run();
  }
}
