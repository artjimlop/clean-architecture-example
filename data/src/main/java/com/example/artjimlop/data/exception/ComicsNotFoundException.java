/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 * @author Fernando Cejas (the android10 coder)
 */
package com.example.artjimlop.data.exception;

public class ComicsNotFoundException extends Exception {

  public ComicsNotFoundException() {
    super();
  }

  public ComicsNotFoundException(final String message) {
    super(message);
  }

  public ComicsNotFoundException(final String message, final Throwable cause) {
    super(message, cause);
  }

  public ComicsNotFoundException(final Throwable cause) {
    super(cause);
  }
}
