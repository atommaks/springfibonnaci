package com.example.springfibonnaci;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.hamcrest.Matchers.containsString;

@SpringBootTest
@AutoConfigureMockMvc
class SpringfibonnaciApplicationTests {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Test for number 0")
    void shouldCheckNumberZero() throws Exception {
        this.mockMvc.perform(
                get("/fibbonaci/0"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("0")));
    }

    @Test
    @DisplayName("Test for number 2")
    void shouldCheckNumberTwo() throws Exception {
        this.mockMvc.perform(
                get("/fibbonaci/2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("1")));
    }

    @Test
    @DisplayName("Test for number 10")
    void shouldCheckNumberTen() throws Exception {
        this.mockMvc.perform(
                get("/fibbonaci/10"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("55")));
    }

    @Test
    @DisplayName("Test for number 100")
    void shouldCheckNumberHundred() throws Exception {
        this.mockMvc.perform(
                get("/fibbonaci/100"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("354224848179261915075")));
    }
}
