package br.com.api.service;

import br.com.api.domain.User;

public interface UserService {

    User findById(Integer id);
}
