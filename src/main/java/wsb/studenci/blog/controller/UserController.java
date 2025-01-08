package wsb.studenci.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import wsb.studenci.blog.model.User;
import wsb.studenci.blog.model.request.user.CreateUserRequest;
import wsb.studenci.blog.model.request.user.EditUserRequest;
import wsb.studenci.blog.repository.UserRepository;
import wsb.studenci.blog.service.AuthenticationService;
import wsb.studenci.blog.service.annotation.authentication.RequireAuthentication;


@Controller
@RequestMapping(path="/users")
public class UserController
{
    private final UserRepository userRepository;
    private final AuthenticationService authenticationService;

    public UserController(UserRepository userRepository, AuthenticationService authenticationService)
    {
        this.userRepository = userRepository;
        this.authenticationService = authenticationService;
    }

    @RequireAuthentication
    @PostMapping
    @ResponseBody
    public ResponseEntity<User> edit(@RequestBody EditUserRequest request)
    {
        User user = this.authenticationService.authenticate();
        user.setName(request.getName());
        user.setSurname(request.getSurname());
        user.setBio(request.getBio());

        this.userRepository.save(user);

        return ResponseEntity.ok(user);
    }

//    @PostMapping
//    @ResponseBody
//    public ResponseEntity<User> create(@RequestBody CreateUserRequest request) {
//        User user = new User(
//            request.getLogin(),
//            request.
//            new byte[1]
//        );
//        user.setName(request.getName());
//        user.setLogin(request.getLogin());
//        userRepository.save(user);
//
//        return new ResponseEntity<>(
//                user,
//                HttpStatus.CREATED
//        );
//    }
//
//    @GetMapping
//    @ResponseBody
//    public ResponseEntity<Iterable<User>> index() {
//        return new ResponseEntity<>(
//                userRepository.findAll(),
//                HttpStatus.OK
//        );
//    }
}