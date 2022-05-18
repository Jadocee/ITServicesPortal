package com.spacejaam.itservicesportal.service;

import com.spacejaam.itservicesportal.bean.client.Client;
import com.spacejaam.itservicesportal.bean.client.ClientPrinciple;
import com.spacejaam.itservicesportal.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ClientDetailService implements UserDetailsService {

    private final ClientRepository clientRepository;
//  private final ClientDataAccessService clientDataAccessService;
//  private final PasswordEncoder passwordEncoder;

    @Autowired
    public ClientDetailService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
//    this.clientDataAccessService = clientDataAccessService;
//    this.passwordEncoder = passwordEncoder;
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
        return new ClientPrinciple(client);
    }
}
