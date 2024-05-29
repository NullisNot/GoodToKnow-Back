package com.example.goodToKnow;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import com.example.goodToKnow.controller.UserController;
import com.example.goodToKnow.entity.User;
import com.example.goodToKnow.errors.UserNotFoundException;
import com.example.goodToKnow.mapper.in.UserIn;
import com.example.goodToKnow.repository.UserRepository;
import com.example.goodToKnow.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(UserController.class)
public class UserControllerUnitTest {

  @MockBean
  private UserRepository userRepository;

  @MockBean
  private UserService userService;

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  void shouldCreateUser() throws Exception {
    UserIn userIn = new UserIn("admin", "admin521", "admin@ad.min");

    mockMvc.perform(post("/api/v1/adminUsers").contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(userIn)))
        .andExpect(status().isCreated());
  }

  @Test
  void shouldDeleteUserById() throws Exception {
    User user = new User("TestUser", "TestPassword", "test@email.com");
    long userId = 5;
    user.setId(userId);

    Optional<User> responseUser = Optional.of((User) user);

    assertThat(responseUser).isPresent();
    assertThat(responseUser.get().getId()).isEqualTo(user.getId());
    assertThat(user.getId()).isEqualTo(userId);

    when(userRepository.findById(userId)).thenReturn(responseUser);
    String url = String.format("/api/v1/adminUsers/%s", userId);
    mockMvc.perform(delete(url)).andExpect(status().isOk());
  }

  @Test
  void shouldNotDeleteUserById() throws Exception {
    long userId = 505;

    when(userRepository.findById(userId)).thenReturn(Optional.empty());
    doThrow(new UserNotFoundException()).when(userService).delete(userId);

    String url = String.format("/api/v1/adminUsers/%s", userId);
    mockMvc.perform(delete(url)).andExpect(status().isNotFound());
  }
}
