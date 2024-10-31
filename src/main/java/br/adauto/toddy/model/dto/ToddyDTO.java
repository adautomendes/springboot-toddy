package br.adauto.toddy.model.dto;

import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ToddyDTO
{
    @NotNull(message = "Name cannot be empty")
    private String name;

    @NotNull(message = "Size cannot be empty")
    private Integer size;
}
