package home.dr.back.repository.jdbcRepository;

import home.dr.back.model.Department;
import home.dr.back.model.Product;
import home.dr.back.repository.ProductRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JDBCProductRepository implements ProductRepository {

    private final JdbcTemplate jdbcTemplate;

    public JDBCProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(Product product) {
        return 0;
    }

    @Override
    public int update(Product product) {
        return 0;
    }

    @Override
    public Product findById(Long id) {
        return null;
    }

    @Override
    public int deleteById(Long id) {
        return 0;
    }

    @Override
    public List<Product> findAll() {
        return jdbcTemplate.query("SELECT * from product", BeanPropertyRowMapper.newInstance(Product.class));
    }

    @Override
    public List<Product> findByName(String name) {
        return null;
    }

    @Override
    public List<Product> findByDepartment(Department department) {
        return null;
    }

    @Override
    public int deleteAll() {
        return 0;
    }
}
