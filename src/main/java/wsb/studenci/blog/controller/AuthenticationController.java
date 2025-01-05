package wsb.studenci.blog.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import wsb.studenci.blog.model.User;
import wsb.studenci.blog.model.request.auth.ChangePasswordPostRequest;
import wsb.studenci.blog.model.request.auth.RegisterPostRequest;
import wsb.studenci.blog.model.request.auth.LoginPostRequest;
import wsb.studenci.blog.model.response.auth.LoginResponse;
import wsb.studenci.blog.service.*;

import java.util.HashMap;
import java.util.Objects;

@Controller
@RequestMapping(path = "/auth")
public class AuthenticationController extends AbstractController
{
    private final RegisterService registerService;
    private final LoginService loginService;
    private final AccessTokenService accessTokenService;
    private final PasswordChangeService passwordChangeService;

    public AuthenticationController(
        AuthenticationService authenticationService,
        RegisterService registerService,
        AccessTokenService accessTokenService,
        LoginService loginService,
        PasswordChangeService passwordChangeService
    ) {
        super(authenticationService);
        this.registerService = registerService;
        this.loginService = loginService;
        this.accessTokenService = accessTokenService;
        this.passwordChangeService = passwordChangeService;
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
            token
        );

        return new ResponseEntity<>(
            response,
            HttpStatus.OK
        );
    }

    @PostMapping(path = "/register")
    @ResponseBody
    public ResponseEntity<Void> register(@RequestBody RegisterPostRequest registerPostRequest)
    {
        this.registerService.register(
            registerPostRequest.getLogin(),
            registerPostRequest.getPassword()
        );

        return ResponseEntity.noContent().build();
    }

    @PostMapping(path = "/change-password")
    @ResponseBody
    public ResponseEntity<Void> changePassword(@RequestBody ChangePasswordPostRequest changePasswordPostRequest)
    {
        if (!Objects.equals(changePasswordPostRequest.getNewPasswordConfirmation(), changePasswordPostRequest.getNewPassword())) {
            return ResponseEntity.badRequest().build();
        }

        this.passwordChangeService.changePassword(
            this.authenticationService.authenticate(),
            changePasswordPostRequest.getOldPassword(),
            changePasswordPostRequest.getNewPassword()
        );

        return ResponseEntity.ok().build();
    }
}
