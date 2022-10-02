package br.com.api.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api.domain.Users;
import br.com.api.repository.UserRepository;
import br.com.api.service.UserService;
import br.com.api.service.exceptions.MyObjectNotFoundException;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    public Users findById(Integer id) {
        Optional<Users> obj = repository.findById(id);
        return obj.orElseThrow(() -> new MyObjectNotFoundException("Objeto do id " + id + " nao encontrado! Tipo: " + Users.class.getName()));
    }
}
