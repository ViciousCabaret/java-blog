package wsb.studenci.blog.controller;

import wsb.studenci.blog.service.AuthenticationService;

abstract public class AbstractController
{
    protected final AuthenticationService authenticationService;

    public AbstractController(
        AuthenticationService authenticationService
    ) {
        this.authenticationService = authenticationService;
    }
}
