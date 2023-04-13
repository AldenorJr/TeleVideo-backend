package br.com.nexus.televideo.controller;

import br.com.nexus.televideo.DTO.Login;
import br.com.nexus.televideo.domain.User;
import br.com.nexus.televideo.repository.UserRepository;
import br.com.nexus.televideo.service.TokenService;
import br.com.nexus.televideo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
    }

    @GetMapping("/findByEmail")
    public ResponseEntity<User> findUserByEmail (@RequestBody String email) {
        return new ResponseEntity<>(userService.findUserByEmail(email), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<Void> efetuarLogin(@RequestBody Login login, HttpServletResponse response) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(login.email(), login.password());
        Authentication authenticate = this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        User user = (User) authenticate.getPrincipal();
        String tokem = tokenService.gerarToken(user);

        Cookie cookie = new Cookie("jwt", tokem);
        cookie.setMaxAge(604800);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        response.addCookie(cookie);

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PostMapping("/delete")
    public ResponseEntity<Void> delete(@PathVariable String email) {
        userService.deleteUser(userService.findUserByEmail(email).getID());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
