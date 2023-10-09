package home.dr.back.repository.jdbcRepository;

import home.dr.back.model.Department;
import home.dr.back.repository.DepartmentRepository;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JDBCDepartmentRepository implements DepartmentRepository {
    private final JdbcTemplate jdbcTemplate;

    public JDBCDepartmentRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(Department department) {
        return 0;
    }

    @Override
    public int update(Department department) {
        return 0;
    }

    @Override
    public Department findById(Long id) {
        return null;
    }

    @Override
    public int deleteById(Long id) {
        return 0;
    }

    @Override
    public List<Department> findAll() {
        return null;
    }

    @Override
    public Department findByName(String name) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM department WHERE name=?",
                    BeanPropertyRowMapper.newInstance(Department.class), name);
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

    @Override
    public int deleteAll() {
        return 0;
    }
}
