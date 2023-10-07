package home.dr.back.repository;

import home.dr.back.model.Client;
import home.dr.back.model.Product;
import home.dr.back.model.Purchase;

import java.util.List;

public interface PurchaseRepository {

    int save(Purchase purchase);

    int deleteById(Long id);

    List<Purchase> findAll();

    List<Purchase> findByClient(Client client);

    List<Purchase> findByProduct(Product product);

    int deleteAll();
}
