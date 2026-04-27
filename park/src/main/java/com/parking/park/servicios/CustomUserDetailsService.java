package com.parking.park.servicios;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.parking.park.modelos.Usuario;
import com.parking.park.repositorios.UsuarioRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository repo;

    @Override
    public UserDetails loadUserByUsername(String username) {

        Usuario user = repo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("No encontrado"));

        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRol().name())
                .build();
    }
}