package home.dr.back.repository.jdbcRepository;

import home.dr.back.model.ProductType;
import home.dr.back.repository.ProductTypeRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JDBCProductTypeRepository implements ProductTypeRepository {
    private final JdbcTemplate jdbcTemplate;

    public JDBCProductTypeRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(ProductType productTypeType) {
        return 0;
    }

    @Override
    public int update(ProductType productType) {
        return 0;
    }

    @Override
    public ProductType findById(Long id) {
        return null;
    }

    @Override
    public int deleteById(Long id) {
        return 0;
    }

    @Override
    public List<ProductType> findAll() {
        List<ProductType> productTypes = jdbcTemplate.query("SELECT * FROM producttype",
                BeanPropertyRowMapper.newInstance(ProductType.class));

        return productTypes;
    }

    @Override
    public ProductType findByName(String name) {
        return null;
    }

    @Override
    public int deleteAll() {
        return 0;
    }
}
