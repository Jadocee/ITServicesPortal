package com.spacejaam.itservicesportal.repository;

import com.spacejaam.itservicesportal.bean.client.Client;

/**
 *
 */
public interface IClientDataAccessService {
    Client findByUsername(String username);

    void save(Client client);
//    Client findByUsername(String username);
//    void save(Client client);
}
