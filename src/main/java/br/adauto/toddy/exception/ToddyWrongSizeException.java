package br.adauto.toddy.exception;

public class ToddyWrongSizeException extends RuntimeException
{
    public ToddyWrongSizeException(Integer size)
    {
        super(String.format("Toddy with size %d is not allowed", size));
    }
}
