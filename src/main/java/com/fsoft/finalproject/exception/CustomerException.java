package com.fsoft.finalproject.exception;

public class CustomerException extends RuntimeException {
  public CustomerException() {}

  public CustomerException(String message) {
    super(message);
  }
}
