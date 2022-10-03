package br.com.api.service;

import br.com.api.domain.User;
import br.com.api.domain.dto.UserDto;

import java.util.List;

public interface UserService {

    User findById(Integer id);
    List<User> findAll();
    User create(UserDto obj);
    User update(UserDto obj);

    void delete(Integer id);
}
