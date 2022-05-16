package com.spacejaam.itservicesportal.repository;

import com.spacejaam.itservicesportal.bean.client.Client;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 */
@Repository(value = "clientRepository")
public interface ClientRepository extends CrudRepository<Client, Long> {
    @Query(value = "SELECT * FROM Client WHERE email = :email")
    Client getClientByEmail(@Param("email") String email);
}
