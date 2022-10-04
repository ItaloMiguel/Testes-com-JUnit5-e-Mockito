package br.com.api.service.impl;

import br.com.api.domain.User;
import br.com.api.domain.dto.UserDto;
import br.com.api.repository.UserRepository;
import br.com.api.service.exceptions.MyDataIntegratyViolationException;
import br.com.api.service.exceptions.MyObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserServiceImplTest {

    public static final Integer ID = 1;
    public static final String NAME = "Italo";
    public static final String EMAIL = "italo@gmail.com";
    public static final String PASSWORD = "123";
    public static final int INDEX = 2;

    @InjectMocks
    private UserServiceImpl service;

    @Mock
    private UserRepository repository;

    @Mock
    private ModelMapper mapper;

    private User user;
    private UserDto userDto;
    private Optional<User> optionalUser;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    @Test
    void whenFindByIdThenReturnAnUserInstance() {
        when(repository.findById(anyInt())).thenReturn(optionalUser);

        User reponse = service.findById(ID);

        assertNotNull(reponse);
        assertEquals(User.class, reponse.getClass());
        assertEquals(ID, reponse.getId());
        assertEquals(NAME, reponse.getName());
        assertEquals(EMAIL, reponse.getEmail());
    }

    @Test
    void whenFindByIdThenReturnAnObjectNotFoundException() {
        when(repository.findById(anyInt())).thenThrow(new MyObjectNotFoundException("Objeto não encontrado"));

        try {
            service.findById(ID);
        } catch (Exception ex) {
            assertEquals(MyObjectNotFoundException.class, ex.getClass());
            assertEquals("Objeto não encontrado", ex.getMessage());
        }
    }

    @Test
    void whenFindAllThenReturnAnListOfUser() {
        when(repository.findAll()).thenReturn(List.of(user));

        List<User> response = service.findAll();

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(User.class, response.get(0).getClass());

        assertEquals(ID, response.get(0).getId());
        assertEquals(NAME, response.get(0).getName());
        assertEquals(EMAIL, response.get(0).getEmail());
        assertEquals(PASSWORD, response.get(0).getPassword());
    }

    @Test
    void whenCreateThenReturnSuccess() {
        when(repository.save(any())).thenReturn(user);

        User reponse = service.create(userDto);
        assertNotNull(reponse);
        assertEquals(User.class, reponse.getClass());
        assertEquals(ID, reponse.getId());
        assertEquals(NAME, reponse.getName());
        assertEquals(EMAIL, reponse.getEmail());
        assertEquals(PASSWORD, reponse.getPassword());
    }

    @Test
    void whenCreateThenReturnAMyDataIntegratyViolationException() {
        when(repository.findByEmail(anyString())).thenReturn(optionalUser);

        try {
            optionalUser.get().setId(2);
            service.create(userDto);
        } catch (Exception ex) {
            assertEquals(MyDataIntegratyViolationException.class, ex.getClass());
            assertEquals("E-mail já cadastrado no sistema", ex.getMessage());
        }
    }

    @Test
    void whenUpdateThenReturnSucess() {
        when(repository.save(any())).thenReturn(user);

        User response = service.update(userDto);

        assertNotNull(response);
        assertEquals(User.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(PASSWORD, response.getPassword());
    }

    @Test
    void whenUpdateThenReturnAMyDataIntegratyViolationException() {
        when(repository.findByEmail(anyString())).thenReturn(optionalUser);

        try {
            optionalUser.get().setId(INDEX);
            service.create(userDto);
        } catch (Exception ex) {
            assertEquals(MyDataIntegratyViolationException.class, ex.getClass());
        }

    }

    @Test
    void deleteWithSuccess() {
        when(repository.findById(anyInt())).thenReturn(optionalUser);
        doNothing().when(repository).deleteById(anyInt());
        service.delete(ID);
        verify(repository, times(1)).deleteById(anyInt());
    }

    @Test
    void deleteWithObjectNotFoundException() {
        when(repository.findById(anyInt())).thenThrow(new MyObjectNotFoundException("Objeto não encontrado"));

        try {
            service.delete(ID);
        } catch (Exception ex) {
            assertEquals(MyObjectNotFoundException.class, ex.getClass());
            assertEquals("Objeto não encontrado", ex.getMessage());
        }
    }

    private void startUser() {
        user = new User(ID, NAME, EMAIL, PASSWORD);
        userDto = new UserDto(ID, NAME, EMAIL, PASSWORD);
        optionalUser = Optional.of(new User(ID, NAME, EMAIL, PASSWORD));
    }
}