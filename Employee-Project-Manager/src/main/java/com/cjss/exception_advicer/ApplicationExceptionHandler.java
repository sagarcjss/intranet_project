package com.cjss.exception_advicer;

import com.cjss.exceptions.DataNotFoundException;
import com.cjss.exceptions.EmployeeNotFoundException;
import com.cjss.exceptions.PermissionDeniedToAccessException;
import com.cjss.exceptions.ProjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    // Handling Validation Exception
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> validationHandler(MethodArgumentNotValidException exception) {
        Map<String, String> errorMap = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(error -> errorMap.put(error.getField(),
                error.getDefaultMessage()));
        return errorMap;
    }

    // Handling Date Format Exception
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DateTimeParseException.class)
    public String dateFormatException(DateTimeParseException exception){
        return "please Enter date YY-MM-DD format only";
    }

    // If Employee is Not Found Throwing EmployeeNotFoundException
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(EmployeeNotFoundException.class)
    public String employeeNotFoundException(EmployeeNotFoundException exception){
        return exception.getMessage();
    }


    // If Project is Not Found Throwing ProjectNotFoundException
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ProjectNotFoundException.class)
    public String projectNotFoundException(ProjectNotFoundException exception){
        return exception.getMessage();
    }

    // If Employee wants to access restricted  area the throwing PermissionDeniedToAccessException
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(PermissionDeniedToAccessException.class)
    public String permissionDeniedException(PermissionDeniedToAccessException exception){
        return exception.getMessage();
    }

    // If entered data is not found throwing DataNotFoundException
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DataNotFoundException.class)
    public String dataNotFoundException(DataNotFoundException exception){
        return exception.getMessage();
    }
}
