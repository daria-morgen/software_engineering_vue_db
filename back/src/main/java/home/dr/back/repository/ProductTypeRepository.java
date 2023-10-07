package home.dr.back.repository;

import home.dr.back.model.ProductType;

import java.util.List;

public interface ProductTypeRepository {

    int save(ProductType productTypeType);

    int update(ProductType productType);

    ProductType findById(Long id);

    int deleteById(Long id);

    List<ProductType> findAll();

    List<ProductType> findByName(String name);

    int deleteAll();
}
