package com.zensar.df.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidException {

	private static final long serialVersionUID = 1L;
    private String message;
      public InvalidException() {
          this.message = "";
      }
      
      public InvalidException(String message) {
          this.message = message;
      }



    @Override
      public String toString() {
          return "Resource Not Found " + message;
      }
}
