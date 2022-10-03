package br.com.api.service.impl;

import java.util.List;
import java.util.Optional;

import br.com.api.domain.dto.UserDto;
import br.com.api.service.exceptions.MyDataIntegratyViolationException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api.domain.User;
import br.com.api.repository.UserRepository;
import br.com.api.service.UserService;
import br.com.api.service.exceptions.MyObjectNotFoundException;

import javax.validation.constraints.NotNull;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
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
        findByEmail(obj);
        return repository.save(mapper.map(obj, User.class));
    }


    private void findByEmail(UserDto obj) {
         Optional<User> user = repository.findByEmail(obj.getEmail());
        if(user.isPresent()) {
            throw new MyDataIntegratyViolationException("E-mail já cadastrado no sistema");
        }

    }
}
