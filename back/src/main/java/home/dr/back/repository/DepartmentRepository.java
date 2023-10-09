package home.dr.back.repository;

import home.dr.back.model.Department;

import java.util.List;

public interface DepartmentRepository {
    int save(Department department);

    int update(Department department);

    Department findById(Long id);

    int deleteById(Long id);

    List<Department> findAll();

    Department findByName(String name);

    int deleteAll();
}
