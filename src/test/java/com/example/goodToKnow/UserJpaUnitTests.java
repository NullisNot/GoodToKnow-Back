package com.example.goodToKnow;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.example.goodToKnow.entity.User;
import com.example.goodToKnow.repository.UserRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(true)
public class UserJpaUnitTests {

  @Autowired
  private UserRepository repository;

  @Autowired
  private TestEntityManager entityManager;

  @Test
  public void shouldCreateUser() {
    User user = createUser();

    User testUser = repository.save(user);

    User existingUser = entityManager.find(User.class, testUser.getId());

    assertEquals(testUser.getEmail(), existingUser.getEmail());

  }

  @Test
  public void shouldDeleteUser() {
    User user = createUser();

    User testUser = repository.save(user);

    User existingUser = entityManager.find(User.class, testUser.getId());

    assertEquals(testUser.getId(), existingUser.getId());

    repository.deleteById(testUser.getId());

    User deletedUser = entityManager.find(User.class, testUser.getId());

    assertNull(deletedUser);
  }

  private User createUser() {
    User user = new User();
    user.setUserName("Miguita");
    user.setPassword("123456");
    user.setEmail("miguita@de.pan");
    user.setId((long) 1);

    return user;
  }
}
