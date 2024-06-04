package com.example.goodToKnow;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.goodToKnow.entity.User;
import com.example.goodToKnow.errors.UserNotFoundException;
import com.example.goodToKnow.mapper.in.UserIn;
import com.example.goodToKnow.mapper.out.UserOut;
import com.example.goodToKnow.repository.UserRepository;
import com.example.goodToKnow.service.UserService;
import java.util.Optional;
import static org.mockito.Mockito.when;

public class UserServiceTest {

  @Mock
  private UserRepository userRepository;

  @InjectMocks
  private UserService userService;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testDeleteUser() {
    User user = createUser();

    when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

    userService.delete(user.getId());

    verify(userRepository, times(1)).findById(user.getId());
    verify(userRepository, times(1)).deleteById(user.getId());
  }

  @Test
  public void testDeleteUserNotFound() {
    Long userId = 1L;
    when(userRepository.findById(userId)).thenReturn(Optional.empty());

    assertThrows(UserNotFoundException.class, () -> userService.delete(userId));

    verify(userRepository, times(1)).findById(userId);
    verify(userRepository, never()).deleteById(anyLong());
  }

  @Test
  public void testSaveUser() {
    UserIn userIn = createUserIn();
    User savedUser = createUser();

    when(userRepository.save(any(User.class))).thenReturn(savedUser);

    UserOut result = userService.saveUser(userIn);

    assertNotNull(result);
    assertEquals(savedUser.getId(), result.getId());
    assertEquals(savedUser.getUserName(), result.getUserName());
    assertEquals(savedUser.getEmail(), result.getEmail());
    verify(userRepository, times(1)).save(any(User.class));

  }

  private User createUser() {
    User user = new User();
    user.setUserName("Miguita");
    user.setPassword("123456");
    user.setEmail("miguita@de.pan");
    user.setId((long) 1);

    return user;
  }

  private UserIn createUserIn() {
    UserIn userIn = new UserIn("Miguita", "123456", "miguita@de.pan");
    return userIn;
  }
}
