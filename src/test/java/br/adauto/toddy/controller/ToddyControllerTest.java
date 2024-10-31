package br.adauto.toddy.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import br.adauto.toddy.model.dto.ToddyDTO;
import br.adauto.toddy.utils.JsonUtils;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class ToddyControllerTest
{
    private MockMvc mockMvc;

    public ToddyControllerTest(@Autowired MockMvc mockMvc)
    {
        this.mockMvc = mockMvc;
    }

    @Test
    public void givenACorrectPostRequest_whenCallPostMethod_shouldReturn201Code() throws Exception
    {
        ToddyDTO toddyDTO = ToddyDTO.builder()
                                    .name("Toddy")
                                    .size(200)
                                    .build();

        MvcResult mvcResult = mockMvc.perform(post("/toddy")
                                                  .contentType(MediaType.APPLICATION_JSON)
                                                  .content(JsonUtils.asJsonString(toddyDTO)))
                                     .andExpect(status().isCreated())
                                     .andDo(print())
                                     .andReturn();

        JSONObject jsonObject = new JSONObject(mvcResult.getResponse().getContentAsString());

        assertThat(jsonObject.getString("name")).isEqualTo("Toddy");
        assertThat(jsonObject.getInt("size")).isEqualTo(200);
    }

    @Test
    public void givenAnIncompletePostRequest_whenCallPostMethod_shouldReturn400Code()
        throws Exception
    {
        ToddyDTO toddyDTO = ToddyDTO.builder()
                                    .size(200)
                                    .build();

        MvcResult mvcResult = mockMvc.perform(post("/toddy")
                                                  .contentType(MediaType.APPLICATION_JSON)
                                                  .content(JsonUtils.asJsonString(toddyDTO)))
                                     .andExpect(status().isBadRequest())
                                     .andDo(print())
                                     .andReturn();

        JSONObject jsonObject = new JSONObject(mvcResult.getResponse().getContentAsString());

        assertThat(jsonObject.getString("title")).isEqualTo("Payload format is invalid");
        assertThat(jsonObject.getString("detail")).contains("Name cannot be empty");
    }

    @Test
    public void givenAnIncorrectPostRequest_whenCallPostMethod_shouldReturn400Code()
        throws Exception
    {
        ToddyDTO toddyDTO = ToddyDTO.builder()
                                    .name("Toddy")
                                    .size(2000)
                                    .build();

        MvcResult mvcResult = mockMvc.perform(post("/toddy")
                                                  .contentType(MediaType.APPLICATION_JSON)
                                                  .content(JsonUtils.asJsonString(toddyDTO)))
                                     .andExpect(status().isBadRequest())
                                     .andDo(print())
                                     .andReturn();

        JSONObject jsonObject = new JSONObject(mvcResult.getResponse().getContentAsString());

        assertThat(jsonObject.getString("title")).isEqualTo("Wrong size for toddy");
        assertThat(jsonObject.getString("detail")).contains("Toddy with size 2000 is not allowed");
    }
}
