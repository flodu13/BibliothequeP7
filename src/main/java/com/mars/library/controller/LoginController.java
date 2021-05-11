package com.mars.library.controller;

import com.mars.library.business.LoginService;
import com.mars.library.config.JwtTokenUtil;
import com.mars.library.controller.dto.LoginDto;
import com.mars.library.repository.UtilisateurRepository;
import com.mars.library.model.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;


    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<String> createAuthenticationToken(@RequestBody LoginDto authenticationRequest) throws Exception {


        final Optional<String> optToken = loginService.createAuthenticationToken(authenticationRequest);
        if (optToken.isPresent()) {

            return ResponseEntity.ok(optToken.get());
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }


}
