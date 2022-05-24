package com.spacejaam.itservicesportal.service;

import com.spacejaam.itservicesportal.client.Client;
import com.spacejaam.itservicesportal.client.ClientDAO;
import com.spacejaam.itservicesportal.client.ClientPrinciple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ClientDetailService implements UserDetailsService {

    private final ClientDAO clientDAO;

    @Autowired
    ClientDetailService(ClientDAO clientDAO) {
        this.clientDAO = clientDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        final Client client = clientRepository.getClientByEmail(username);
        final Client client = clientDAO.getClientByEmail(username);
        if (client == null) {
            throw new UsernameNotFoundException(username);
        }
        return new ClientPrinciple(client);
    }
}
