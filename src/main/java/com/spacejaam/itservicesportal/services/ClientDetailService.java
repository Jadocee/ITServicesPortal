package com.spacejaam.itservicesportal.services;

import com.spacejaam.itservicesportal.data.ClientDAO;
import com.spacejaam.itservicesportal.models.Client;
import com.spacejaam.itservicesportal.models.ClientPrinciple;
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
        final Client client = clientDAO.getClientByEmail(username);
        if (client == null) {
            throw new UsernameNotFoundException(username);
        }
        return new ClientPrinciple(client);
    }
}
