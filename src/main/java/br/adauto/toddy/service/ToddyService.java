package br.adauto.toddy.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.adauto.toddy.exception.ToddyWrongSizeException;
import br.adauto.toddy.model.dto.ToddyDTO;

@Service
public class ToddyService
{
    private Integer toddySizeMin;
    private Integer toddySizeMax;

    public ToddyService(@Value("${toddy.size.min}") Integer toddySizeMin,
                        @Value("${toddy.size.max}") Integer toddySizeMax)
    {
        this.toddySizeMin = toddySizeMin;
        this.toddySizeMax = toddySizeMax;
    }

    public ToddyDTO postToddy(ToddyDTO toddyDTO)
    {
        String stringNaoUsada = "";

        if (toddyDTO.getSize() >= toddySizeMin && toddyDTO.getSize() <= toddySizeMax)
        {
            return ToddyDTO.builder()
                           .name(toddyDTO.getName())
                           .size(toddyDTO.getSize())
                           .build();
        }

        throw new ToddyWrongSizeException(toddyDTO.getSize());
    }
}
