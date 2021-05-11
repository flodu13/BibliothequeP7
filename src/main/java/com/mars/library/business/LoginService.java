package com.mars.library.business;

import com.mars.library.config.JwtTokenUtil;
import com.mars.library.controller.dto.LoginDto;
import com.mars.library.model.Utilisateur;
import com.mars.library.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class LoginService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

   /* @Autowired
    private AuthenticationManager authenticationManager;*/

    @Autowired
    private JwtTokenUtil jwtTokenUtil;


   /* private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }*/
    public Optional<String> createAuthenticationToken(LoginDto authenticationRequest) throws Exception {


        final Optional<Utilisateur> userDetails = utilisateurRepository.findByNom(authenticationRequest.getNom());
        if (userDetails.isPresent() && passwordEncoder.matches(authenticationRequest.getMotDePasse(), userDetails.get().getMotDePasse())) {
            final String token = jwtTokenUtil.generateToken(userDetails.get());
            return Optional.of(token);
        }
        return Optional.empty();
    }

}
