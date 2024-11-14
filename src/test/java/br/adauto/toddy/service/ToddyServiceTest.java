package br.adauto.toddy.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import br.adauto.toddy.exception.ToddyWrongSizeException;
import br.adauto.toddy.model.dto.ToddyDTO;

@ExtendWith(MockitoExtension.class)
public class ToddyServiceTest
{
    private ToddyDTO correctToddyDTO;
    private ToddyDTO incorrectToddyDTO;

    @InjectMocks
    private ToddyService toddyService;

    @BeforeEach
    public void init()
    {
        toddyService = new ToddyService(190, 210);

        correctToddyDTO = ToddyDTO.builder()
                                  .name("Correct")
                                  .size(190)
                                  .build();

        incorrectToddyDTO = ToddyDTO.builder()
                                  .name("Incorrect")
                                  .size(2000)
                                  .build();
    }

    @Test
    public void givenPostToddy_whenPostCorrectToddy_thenReturnCorrectToddyDTO()
    {
        ToddyDTO toddyDTO = toddyService.postToddy(correctToddyDTO);

        assertThat(toddyDTO).isEqualTo(correctToddyDTO);
    }

    @Test
    public void givenPostToddy_whenPostIncorrectToddy_thenThrowToddyWrongSizeException()
    {
        Throwable throwable = catchThrowable(() -> toddyService.postToddy(incorrectToddyDTO));

        assertThat(throwable)
            .isInstanceOf(ToddyWrongSizeException.class)
            .hasMessageContaining("Toddy with size 2000 is not allowed");
    }
}
