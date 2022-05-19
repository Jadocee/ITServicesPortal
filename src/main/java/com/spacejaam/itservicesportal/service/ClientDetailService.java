package com.spacejaam.itservicesportal.service;

import com.spacejaam.itservicesportal.dao.client.ClientDAO;
import com.spacejaam.itservicesportal.model.client.Client;
import com.spacejaam.itservicesportal.model.client.ClientPrinciple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ClientDetailService implements UserDetailsService {

//    private final ClientRepository clientRepository;
//
//    @Autowired
//    public ClientDetailService(ClientRepository clientRepository) {
//        this.clientRepository = clientRepository;
//    }

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
