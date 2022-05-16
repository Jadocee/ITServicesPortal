package com.spacejaam.itservicesportal.repository;

import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 */
//@Repository
//    implements ClientDAO
@Deprecated
public class ClientDataAccessService {
    private final JdbcTemplate jdbcTemplate;

    //    @Autowired
    public ClientDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


//    @Override
//    public Optional<Client> findByUsername(String username) {
//        final String sql = "SELECT [ID], email, [password], firstname, lastname, contactnum, [role] FROM clients WHERE email = ?";
//        return jdbcTemplate.query(sql, new Object[]{username}, new int[]{Types.VARCHAR}, new ClientRowMapper())
//                .stream().findFirst();
//    }
//
//    @Override
//    public int insertClient(Client client) {
//        final String sql = "INSERT INTO clients (email, [password], firstName, lastName, contactNum, role) VALUES (?, ?, ?, ?, ?, ?)";
//        return jdbcTemplate.update(sql, client.getUsername(), client.getPassword(), client.getFirstName(), client.getLastName(), client.getContactNum(), client.getRole());
//    }
//
//    @Override
//    public List<Client> getClientsByName(String name) {
//        final String sql = "SELECT * FROM clients WHERE email = ?";
//        return jdbcTemplate.query(sql, new Object[]{name}, new int[]{Types.VARCHAR}, new ClientRowMapper());
//    }
}
