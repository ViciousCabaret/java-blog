package wsb.studenci.blog.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import wsb.studenci.blog.model.User;
import wsb.studenci.blog.model.request.auth.RegisterPostRequest;
import wsb.studenci.blog.model.request.auth.LoginPostRequest;
import wsb.studenci.blog.model.response.auth.LoginResponse;
import wsb.studenci.blog.model.response.auth.RegisterResponse;
import wsb.studenci.blog.service.AccessTokenService;
import wsb.studenci.blog.service.AuthenticationService;
import wsb.studenci.blog.service.LoginService;
import wsb.studenci.blog.service.RegisterService;
import wsb.studenci.blog.service.annotation.authentication.RequireAuthentication;

import java.util.HashMap;

@Controller
@RequestMapping(path = "/auth")
public class AuthenticationController extends AbstractController
{
    private final RegisterService registerService;
    private final LoginService loginService;
    private final AccessTokenService accessTokenService;

    public AuthenticationController(
        AuthenticationService authenticationService,
        RegisterService registerService,
        AccessTokenService accessTokenService,
        LoginService loginService
    ) {
        super(authenticationService);
        this.registerService = registerService;
        this.loginService = loginService;
        this.accessTokenService = accessTokenService;
    }

    @PostMapping(path = "/login")
    @ResponseBody
    public ResponseEntity<LoginResponse> login(@RequestBody LoginPostRequest loginPostRequest)
    {
        User user = this.loginService.login(
            loginPostRequest.getLogin(),
            loginPostRequest.getPassword()
        );

        String token = this.accessTokenService.create(user, new HashMap<>());

        LoginResponse response = new LoginResponse(
            token,
            token
        );

        return new ResponseEntity<>(
            response,
            HttpStatus.OK
        );
    }

    @PostMapping(path = "/register")
    @ResponseBody
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterPostRequest registerPostRequest)
    {
        User user = this.registerService.register(
            registerPostRequest.getLogin(),
            registerPostRequest.getPassword()
        );

        String token = this.accessTokenService.create(user, new HashMap<>());

        RegisterResponse response = new RegisterResponse(token);

        return new ResponseEntity<>(
            response,
            HttpStatus.OK
        );
    }
}
