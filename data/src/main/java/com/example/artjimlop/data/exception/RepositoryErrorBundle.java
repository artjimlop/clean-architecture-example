/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 * @author Fernando Cejas (the android10 coder)
 */
package com.example.artjimlop.data.exception;

import com.example.exception.ErrorBundle;

public class RepositoryErrorBundle implements ErrorBundle {

  private final Exception exception;

  public RepositoryErrorBundle(Exception exception) {
    this.exception = exception;
  }

  public Exception getException() {
    return exception;
  }

  public String getErrorMessage() {
    String message = "";
    if (this.exception != null) {
      this.exception.getMessage();
    }
    return message;
  }
}
