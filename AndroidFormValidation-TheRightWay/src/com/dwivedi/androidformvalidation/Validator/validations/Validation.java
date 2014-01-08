package com.dwivedi.androidformvalidation.Validator.validations;

 
public interface Validation {

    String getErrorMessage();

    boolean isValid(String text);

}