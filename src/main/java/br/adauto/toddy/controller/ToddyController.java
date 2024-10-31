package br.adauto.toddy.controller;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.adauto.toddy.model.dto.ToddyDTO;
import br.adauto.toddy.service.ToddyService;

@RestController
@RequestMapping("/toddy")
public class ToddyController
{
    private ToddyService toddyService;

    @Autowired
    public ToddyController(ToddyService toddyService)
    {
        this.toddyService = toddyService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ToddyDTO postToddy(@Valid @RequestBody ToddyDTO toddyDTO)
    {
        return toddyService.postToddy(toddyDTO);
    }
}
