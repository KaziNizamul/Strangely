package com.strangely.backend.UnitTest.DAO;

import static org.junit.jupiter.api.Assertions.*;

import com.strangely.backend.DataAccess.User.Implementation.UserDAO;
import com.strangely.backend.Model.Auth.Entities.ID;
import com.strangely.backend.Model.User.Entities.User;
import com.strangely.backend.Repository.User.UserRepository;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import jakarta.persistence.EntityManager;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class userDAOTest {

    @Mock
    private EntityManager entityManager;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserDAO userDAO;

    @BeforeEach
    void setUp() {
        // If there are any setup tasks, you can perform them here.
    }

    @Test
    void testFindByEmail() {
        // Mocking the TypedQuery and its result
        TypedQuery<User> query = mock(TypedQuery.class);
        List<User> userList = Arrays.asList(User.builder().email("test@example.com").build());
        when(entityManager.createQuery(anyString(), eq(User.class))).thenReturn(query);
        when(query.getResultList()).thenReturn(userList);

        // Calling the method
        Optional<User> result = userDAO.findBy("test@example.com", ID.Email);

        // Verifying that the query was created and executed
        verify(entityManager).createQuery(anyString(), eq(User.class));
        verify(query).getResultList();

        // Verifying that the result is present and matches the expected result
        assertTrue(result.isPresent());
        assertEquals(userList.get(0), result.get());
    }

    @Test
    void testFindByUsername() {
        // Mocking the TypedQuery and its result
        TypedQuery<User> query = mock(TypedQuery.class);
        List<User> userList = Arrays.asList(User.builder().username("testuser").build());
        when(entityManager.createQuery(anyString(), eq(User.class))).thenReturn(query);
        when(query.getResultList()).thenReturn(userList);

        // Calling the method
        Optional<User> result = userDAO.findBy("testuser", ID.Username);

        // Verifying that the query was created and executed
        verify(entityManager).createQuery(anyString(), eq(User.class));
        verify(query).getResultList();

        // Verifying that the result is present and matches the expected result
        assertTrue(result.isPresent());
        assertEquals(userList.get(0), result.get());
    }

    @Test
    void testFindByResetToken() {
        // Mocking the TypedQuery and its result
        TypedQuery<User> query = mock(TypedQuery.class);
        List<User> userList = Arrays.asList(User.builder().resetToken("testToken").build());
        when(entityManager.createQuery(anyString(), eq(User.class))).thenReturn(query);
        when(query.getResultList()).thenReturn(userList);

        // Calling the method
        Optional<User> result = userDAO.findBy("testToken", ID.ResetToken);

        // Verifying that the query was created and executed
        verify(entityManager).createQuery(anyString(), eq(User.class));
        verify(query).getResultList();

        // Verifying that the result is present and matches the expected result
        assertTrue(result.isPresent());
        assertEquals(userList.get(0), result.get());
    }
    @Test
    void testInsert() {
        // Mocking the EntityManager.merge() method
        User user = User.builder().username("testuser").build();
        when(entityManager.merge(user)).thenReturn(user);

        // Calling the method
        User result = userDAO.Insert(user);

        // Verifying that the EntityManager.merge() method was called
        verify(entityManager).merge(user);

        // Verifying that the result matches the expected result
        assertEquals(user, result);
    }

    @Test
    void testDelete() {
        // Mocking the EntityManager.find() and EntityManager.remove() methods
        User user = User.builder().id(1).build();
        when(entityManager.find(User.class, 1)).thenReturn(user);

        // Calling the method
        userDAO.Delete(1);

        // Verifying that the EntityManager.find() and EntityManager.remove() methods were called
        verify(entityManager).find(User.class, 1);
        verify(entityManager).remove(user);
    }

    @Test
    void testFind() {
        // Mocking the EntityManager.find() method
        User user = User.builder().id(1).build();
        when(entityManager.find(User.class, 1)).thenReturn(user);

        // Calling the method
        User result = userDAO.find(1);

        // Verifying that the EntityManager.find() method was called
        verify(entityManager).find(User.class, 1);

        // Verifying that the result matches the expected result
        assertEquals(user, result);
    }
}
