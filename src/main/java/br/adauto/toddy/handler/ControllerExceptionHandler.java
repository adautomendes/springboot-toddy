package br.adauto.toddy.handler;

import java.net.URI;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.HandlerMethod;

import br.adauto.toddy.exception.ToddyWrongSizeException;

@RestControllerAdvice
@ResponseBody
public class ControllerExceptionHandler
{
    @ExceptionHandler(ToddyWrongSizeException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ProblemDetail toddyWrongSizeException(
        ToddyWrongSizeException toddyWrongSizeException, HandlerMethod handlerMethod,
        HttpServletRequest request)
    {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST,
                                                                       toddyWrongSizeException.getMessage());
        problemDetail.setTitle("Wrong size for toddy");
        problemDetail.setType(URI.create("http"));
        problemDetail.setInstance(URI.create(request.getRequestURI()));
        return problemDetail;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ProblemDetail methodArgumentNotValidException(
        MethodArgumentNotValidException methodArgumentNotValidException,
        HandlerMethod handlerMethod,
        HttpServletRequest request)
    {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST,
                                                                       methodArgumentNotValidException.getMessage());
        problemDetail.setTitle("Payload format is invalid");
        problemDetail.setType(URI.create("http"));
        problemDetail.setInstance(URI.create(request.getRequestURI()));
        return problemDetail;
    }
}
