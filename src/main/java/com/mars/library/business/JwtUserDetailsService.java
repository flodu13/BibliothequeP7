package com.mars.library.business;

import com.mars.library.repository.UtilisateurRepository;
import com.mars.library.model.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<Utilisateur> opt = utilisateurRepository.findByNom(s);
        if (opt.isPresent()) {
            return opt.get();
        }
     throw new UsernameNotFoundException(s);
    }
}
