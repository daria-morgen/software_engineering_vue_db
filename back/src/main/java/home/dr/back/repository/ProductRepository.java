package home.dr.back.repository;

import home.dr.back.model.Department;
import home.dr.back.model.Product;

import java.util.List;

public interface ProductRepository {

    int save(Product product);

    int update(Product product);

    Product findById(Long id);

    int deleteById(Long id);

    List<Product> findAll();

    List<Product> findByName(String name);

    List<Product> findByDepartment(Department department);

    int deleteAll();
}
