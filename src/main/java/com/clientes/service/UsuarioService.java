package com.clientes.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.clientes.controller.exception.UsuarioCadastradoException;
import com.clientes.model.entity.Role;
import com.clientes.repository.RoleRepository;
import com.clientes.repository.UserRepository;

@Service
public class UsuarioService implements UserDetailsService  {

    @Autowired
    private UserRepository repository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private RoleRepository roleRepository;
    
    public com.clientes.model.entity.User salvar(com.clientes.model.entity.User usuario){
    	
        var basicRole = roleRepository.findByName(Role.Values.BASIC.name());
    	
        boolean exists = repository.existsByUsername(usuario.getUsername());
        
        if(exists){
            throw new UsuarioCadastradoException(usuario.getUsername());
        }
        
        var user = new com.clientes.model.entity.User();
        user.setUsername(usuario.getUsername());
        user.setPassword(passwordEncoder.encode(usuario.getPassword()));
        user.setRoles(Set.of(basicRole));
        return repository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername( String username ) throws UsernameNotFoundException {
    	com.clientes.model.entity.User usuario = repository
                            .findByUsername(username)
                            .orElseThrow(() -> new UsernameNotFoundException("Login não encontrado.") );

        return User
                .builder()
                .username(usuario.getUsername())
                .password(usuario.getPassword())
                .roles("USER")
                .build()
                ;
    }
}