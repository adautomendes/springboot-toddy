package br.adauto.toddy.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils
{
    public static String asJsonString(final Object obj)
    {
        try
        {
            return new ObjectMapper().writeValueAsString(obj);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
}

