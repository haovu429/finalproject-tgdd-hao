package com.fsoft.finalproject.exception.handle;

import com.fsoft.finalproject.dto.ResponseObject;
import com.fsoft.finalproject.exception.CustomException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalException {

  @ExceptionHandler(RuntimeException.class)
  @ResponseBody
  public ResponseObject handleException(RuntimeException e) {
    ResponseObject responseObject = new ResponseObject("400", 200, e.getMessage());
    return responseObject;
  }

  //Hao test deploy

  @ExceptionHandler(CustomException.class)
  @ResponseBody
  public ResponseObject handleException(CustomException e) {
    ResponseObject responseObject = new ResponseObject("400", 200, e.getMessage());
    return responseObject;
  }
}
