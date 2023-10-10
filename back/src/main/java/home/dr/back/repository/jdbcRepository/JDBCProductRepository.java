package home.dr.back.repository.jdbcRepository;

import home.dr.back.model.Department;
import home.dr.back.model.Product;
import home.dr.back.model.ProductType;
import home.dr.back.repository.ProductRepository;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JDBCProductRepository implements ProductRepository {

    private final JdbcTemplate jdbcTemplate;

    public JDBCProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static Product mapRow(ResultSet rs, int rowNum) {
        Product product = new Product();
        try {
            product.setId(rs.getInt("id"));
            product.setName(rs.getString("name"));
            product.setColor(rs.getString("color"));
            product.setSize(rs.getString("size"));
            product.setPrice(rs.getDouble("price"));

            final var productType = new ProductType();
            productType.setId(rs.getInt("p_id"));
            productType.setName(rs.getString("p_name"));

            final var department = new Department();
            department.setId(rs.getInt("d_id"));
            department.setName(rs.getString("d_name"));

            product.setProductType(productType);
            product.setDepartment(department);

            return product;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    @Override
    public int save(Product product) {
        return jdbcTemplate.update("INSERT INTO product (name, color,size,price,productTypeId,departmentId) VALUES(?,?,?,?,?,?)",
                new Object[]{product.getName(), product.getColor(), product.getSize(), product.getPrice(), product.getProductType().getId(), product.getDepartment().getId()});
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
        return this.jdbcTemplate.query(
                "select pr.*, p.id as p_id, p.name as p_name, d.id as d_id, d.name as d_name\n" +
                        "from product pr\n" +
                        "         join producttype p on pr.producttypeid = p.id\n" +
                        "         join department d on pr.departmentid = d.id\n" +
                        "order by pr.id;",
                JDBCProductRepository::mapRow);
    }

    @Override
    public List<Product> findByName(String name) {
        return this.jdbcTemplate.query(
                "select pr.*, p.id as p_id, p.name as p_name, d.id as d_id, d.name as d_name\n" +
                        "from product pr\n" +
                        "         join producttype p on pr.producttypeid = p.id\n" +
                        "         join department d on pr.departmentid = d.id WHERE pr.name=?\n" +
                        "order by pr.id;",
                JDBCProductRepository::mapRow, name);
    }

    @Override
    public List<Product> findByDepartment(Department department) {
        try {
            List<Product> productList = jdbcTemplate.query("SELECT * FROM product WHERE departmentId=?",
                    BeanPropertyRowMapper.newInstance(Product.class), department.getId());

            return productList;
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

    @Override
    public int deleteAll() {
        return 0;
    }
}
