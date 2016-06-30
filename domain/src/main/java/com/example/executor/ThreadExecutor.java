/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 * @author Fernando Cejas (the android10 coder)
 */
package com.example.executor;

public interface ThreadExecutor {

  void execute(final Runnable runnable);
}
