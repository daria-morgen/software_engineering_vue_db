package home.dr.back.repository.jdbcRepository;

import home.dr.back.model.Client;
import home.dr.back.repository.ClientRepository;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JDBCClientRepository implements ClientRepository {

    private final JdbcTemplate jdbcTemplate;

    public JDBCClientRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(Client client) {
        return jdbcTemplate.update("INSERT INTO client (name, tel) VALUES(?,?)",
                new Object[]{client.getName(), client.getTel()});
    }

    @Override
    public int update(Client client) {
        return jdbcTemplate.update("UPDATE client SET name=?, tel=? WHERE id=?",
                new Object[]{client.getName(), client.getTel(), client.getId()});
    }

    @Override
    public Client findById(Long id) {
        try {
            Client client = jdbcTemplate.queryForObject("SELECT * FROM client WHERE id=?",
                    BeanPropertyRowMapper.newInstance(Client.class), id);

            return client;
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

    @Override
    public int deleteById(Long id) {
        return jdbcTemplate.update("DELETE FROM client WHERE id=?", id);
    }

    @Override
    public List<Client> findAll() {
        return jdbcTemplate.query("SELECT * from client", BeanPropertyRowMapper.newInstance(Client.class));
    }

    @Override
    public Client findByName(String name) {
        try {
            Client client = jdbcTemplate.queryForObject("SELECT * FROM client WHERE name=?",
                    BeanPropertyRowMapper.newInstance(Client.class), name);

            return client;
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

    @Override
    public int deleteAll() {
        return jdbcTemplate.update("DELETE from client");
    }
}
