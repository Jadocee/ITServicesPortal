package com.spacejaam.itservicesportal.repository;

import com.spacejaam.itservicesportal.bean.client.Client;

import java.util.List;
import java.util.Optional;


@Deprecated
public interface ClientDAO {
    Optional<Client> findByUsername(String username);

    int insertClient(Client client);

    List<Client> getClientsByName(String name);
}
