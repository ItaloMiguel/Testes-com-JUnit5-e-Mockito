package br.com.api.service.impl;

import java.util.List;
import java.util.Optional;

import br.com.api.domain.dto.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api.domain.User;
import br.com.api.repository.UserRepository;
import br.com.api.service.UserService;
import br.com.api.service.exceptions.MyObjectNotFoundException;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    private ModelMapper mapper;

    @Override
    public User findById(Integer id) {
        Optional<User> obj = repository.findById(id);
        return obj.orElseThrow(() -> new MyObjectNotFoundException("Objeto do id " + id + " nao encontrado! Tipo: " + User.class.getName()));
    }

    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public User create(UserDto obj) {
        return repository.save(mapper.map(obj, User.class));
    }
}
