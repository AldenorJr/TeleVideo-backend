package br.com.nexus.televideo.service;

import br.com.nexus.televideo.domain.User;
import br.com.nexus.televideo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class UserService {

    private final UserRepository userRepository;
    private TokenService tokenService;

    public UserService(UserRepository userRepository, TokenService tokenService) {
        this.userRepository = userRepository;
        this.tokenService = tokenService;
    }

    public User findUserByID(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User findUserByToken(HttpServletRequest request) {
        String token;
        String authorization = request.getHeader("Authorization");
        User user = null;
        if(authorization != null) {
            token = authorization.replace("Bearer ", "");
            String subject = tokenService.getSubject(token);
            user = this.userRepository.findByEmail(subject);
        }
        return user;
    }

    public User findUserByName(String userName) {
        return userRepository.findByUserName(userName);
    }

    public User replace(User user) {
        findUserByID(user.getID());
        return saveUser(user);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.delete(findUserByID(id));
    }

}
