package br.com.nexus.televideo.repository;

import br.com.nexus.televideo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
    User findByUserName(String userName);
}
