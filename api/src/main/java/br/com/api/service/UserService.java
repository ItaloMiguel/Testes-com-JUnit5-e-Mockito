package br.com.api.service;

import br.com.api.domain.Users;
import br.com.api.service.exceptions.MyObjectNotFoundException;

public interface UserService {

    Users findById(Integer id);
}
