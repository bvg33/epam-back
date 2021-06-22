package com.epam.tr.service;

import com.epam.tr.dao.UserDaoImpl;
import com.epam.tr.entities.AppUser;
import com.epam.tr.exceptions.InvalidCredentialsException;
import com.epam.tr.service.logic.validator.UserValidator;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doAnswer;
import static org.powermock.api.mockito.PowerMockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private UserDaoImpl dao = Mockito.mock(UserDaoImpl.class);
    @Mock
    private UserValidator validator = Mockito.mock(UserValidator.class);
    @InjectMocks
    private UserServiceImpl service = new UserServiceImpl(dao, validator);

    private List<AppUser> users = Arrays.asList(new AppUser("login1", "password1"),
            new AppUser("login2", "password2"));

    @Test
    public void testGetAllUsers() {
        List<AppUser> expected = new ArrayList<>(users);
        when(dao.getAll()).thenReturn(users);

        List<AppUser> actual = service.getAllUsers();

        assertEquals(expected, actual);
    }

    @Test
    public void testCreate() throws InvalidCredentialsException {
        List<AppUser> actual = new ArrayList<>(users);
        AppUser newUser = new AppUser("login3", "password3");
        List<AppUser> expected = Arrays.asList(new AppUser("login1", "password1"),
                new AppUser("login2", "password2"),
                new AppUser("login3", "password3"));

        doAnswer(invocation -> actual.add(newUser)).when(dao).insert(newUser);
        when(validator.isValid(Mockito.anyObject())).thenReturn(true);

        service.create(newUser);

        assertEquals(actual, expected);
    }

    @Test(expected = InvalidCredentialsException.class)
    public void testCreateNegative() throws InvalidCredentialsException {
        AppUser newUser = new AppUser(null, null);

        service.create(newUser);
    }

    @Test
    public void testDelete() throws InvalidCredentialsException {
        List<AppUser> actual = new ArrayList<>(users);
        List<AppUser> expected = Arrays.asList(new AppUser("login2", "password2"));
        doAnswer(invocation -> actual.remove(0)).when(dao).delete(Mockito.anyObject());
        when(validator.isValid(Mockito.anyObject())).thenReturn(true);

        service.delete(new AppUser("login1", "password1"));

        assertEquals(expected, actual);
    }

    @Test(expected = InvalidCredentialsException.class)
    public void testDeleteNegative() throws InvalidCredentialsException {
        service.delete(new AppUser(null, "password1"));
    }

}
