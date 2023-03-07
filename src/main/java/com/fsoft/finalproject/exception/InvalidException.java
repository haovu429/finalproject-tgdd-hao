package com.fsoft.finalproject.exception;

public class InvalidException extends RuntimeException {
  public InvalidException() {}

  public InvalidException(String message) {
    super(message);
  }
}
