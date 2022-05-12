package org.triathlongirls.doranssam.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.triathlongirls.doranssam.BaseAuthApiTest;
import org.triathlongirls.doranssam.dto.LoginRequestDto;
import org.triathlongirls.doranssam.dto.SignupRequestDto;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
@Slf4j
class AuthApiControllerTest extends BaseAuthApiTest {

    @Order(1)
    @Test
    public void auth1_회원가입_성공() throws Exception {
        SignupRequestDto signupRequestDto = new SignupRequestDto("user", "password", "password", "nickname");
        this.mockMvc.perform(post("/auth/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(signupRequestDto)))
                .andDo(print())

                .andExpect(status().isOk());
    }

    @Order(2)
    @Test
    public void auth2_사용자_로그인_성공() throws Exception {

        LoginRequestDto loginRequestDto = new LoginRequestDto("user", "password");

        this.mockMvc.perform(post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginRequestDto)))
                .andDo(print())

                .andExpect(status().isOk());
    }

    @Test
    public void auth3_사용자_로그인_실패_존재하지_않은_사용자() throws Exception {
        LoginRequestDto loginRequestDto = new LoginRequestDto("unknown", "password");
        this.mockMvc.perform(post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginRequestDto)))
                .andExpect(status().isUnauthorized());
    }
}
