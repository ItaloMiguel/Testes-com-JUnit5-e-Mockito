package br.com.api.config;

import br.com.api.domain.Users;
import br.com.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
@Profile("local")
public class LocalConfig {

    @Autowired
    private UserRepository repository;

    @Bean
    public void startDb() {
        Users u1 = new Users(null, "Italo", "italo@gmail.com", "123");
        Users u2 = new Users(null, "Pedro", "pedro@gmail.com", "1234");

        repository.saveAll(List.of(u1, u2));
    }
}
