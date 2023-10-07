package home.dr.back.repository.jdbcRepository;

import home.dr.back.model.Client;
import home.dr.back.model.Product;
import home.dr.back.model.Purchase;
import home.dr.back.repository.PurchaseRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JDBCPurchaseRepository implements PurchaseRepository {
    private final JdbcTemplate jdbcTemplate;

    public JDBCPurchaseRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(Purchase purchase) {
        return 0;
    }

    @Override
    public int deleteById(Long id) {
        return 0;
    }

    @Override
    public List<Purchase> findAll() {
        return null;
    }

    @Override
    public List<Purchase> findByClient(Client client) {
        return null;
    }

    @Override
    public List<Purchase> findByProduct(Product product) {
        return null;
    }

    @Override
    public int deleteAll() {
        return 0;
    }
}
