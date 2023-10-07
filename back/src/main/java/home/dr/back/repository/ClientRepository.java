package home.dr.back.repository;

import home.dr.back.model.Client;

import java.util.List;

public interface ClientRepository {

    int save(Client client);

    int update(Client client);

    Client findById(Long id);

    int deleteById(Long id);

    List<Client> findAll();

    Client findByName(String name);

    int deleteAll();
}
