package com.spacejaam.itservicesportal.service;

import com.spacejaam.itservicesportal.bean.client.Client;
import com.spacejaam.itservicesportal.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service(value = "authenticationService")
public class AuthenticationService implements UserDetailsService {

  private final ClientRepository clientRepository;

  @Autowired
  public AuthenticationService(ClientRepository clientRepository) {
    this.clientRepository = clientRepository;
  }

  public void authenticate(String email, String password) {
//    clientRepository.get
  }


  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    final Client client = clientRepository.getClientByEmail(username);
    if (client == null) {
      throw new UsernameNotFoundException(username);
    }
    return User.builder()
        .username(client.getEmail())
        .password(client.getPassword())
        .authorities("USER")
        .roles("User")
        .build();
  }
}
