package com.spacejaam.itservicesportal.service;

import com.spacejaam.itservicesportal.bean.client.Client;
import com.spacejaam.itservicesportal.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service(value = "authenticationService")
public class ClientDetailService implements UserDetailsService {

  private final ClientRepository clientRepository;
  private final PasswordEncoder passwordEncoder;

  @Autowired
  public ClientDetailService(ClientRepository clientRepository, PasswordEncoder passwordEncoder) {
    this.clientRepository = clientRepository;
    this.passwordEncoder = passwordEncoder;
  }


  public void authenticate(String email, String password) {
//    clientRepository.get
  }


  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    final Client client = clientRepository.getClientByEmail(username);
    System.out.println(client.getFirstName());
    if (client == null) {
      throw new UsernameNotFoundException(username);
    }
    System.out.println(client.getUsername());
    return client;
//    return User.builder()
//        .username(client.getUsername())
//        .password(client.getPassword())
//        .authorities("USER")
//        .roles(Role.USER.toString())
//        .build();
  }
}
