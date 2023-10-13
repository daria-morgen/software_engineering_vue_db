package home.dr.back.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import home.dr.back.model.Client;
import home.dr.back.model.Product;
import home.dr.back.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class Realization {
    private static final Logger LOG = LoggerFactory.getLogger(Realization.class);


    private final ProductRepository productRepository;

    private final DepartmentRepository departmentRepository;

    private final ClientRepository clientRepository;

    private final ProductTypeRepository productTypeRepository;

    private final PurchaseRepository purchaseRepository;


    public Realization(ProductRepository productRepository,
                       DepartmentRepository departmentRepository,
                       ClientRepository clientRepository,
                       ProductTypeRepository productTypeRepository,
                       PurchaseRepository purchaseRepository) {
        this.productRepository = productRepository;
        this.departmentRepository = departmentRepository;
        this.clientRepository = clientRepository;
        this.productTypeRepository = productTypeRepository;
        this.purchaseRepository = purchaseRepository;
    }

    /**
     * - находить товар по его названию, показывать отдел, где его можно
     * приобрести;
     *
     * @param name
     * @return
     */
    public String findProductByName(String name) {
        try {
            return new ObjectMapper().writeValueAsString(productRepository.findByName(name));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * - показывать ассортимент выбранного отдела;
     *
     * @param name
     * @return
     */
    public String findProductsByDepartment(String name) {
        final var department = departmentRepository.findByName(name);

        final var byDepartment = productRepository.findByDepartment(department);

        try {
            return new ObjectMapper().writeValueAsString(byDepartment);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * - показывать товарооборот по выбранным отделам (сумму продаж),
     * например, в виде диаграммы;
     *
     * @return
     */
    public String getPurchasesByDepartment() {
        return null;
    }

    /**
     * - рассчитывать стоимость покупки, учитывая скидки;
     *
     * @return
     */
    public String getDiscountedprice() {
        return null;
    }

    /**
     * - показывать наличие выбранного размера (цвета, фасона) для
     * обуви и одежды.
     *
     * @return
     */
    public String getProductBeColor() {
        return null;
    }

    public ResponseEntity<String> createProduct(Product product) {
        try {
            LOG.info("Product post: " + product);
            productRepository.save(product);
            return new ResponseEntity<>("Client was created successfully.", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public String getAllProducts() {
        try {
            return new ObjectMapper().writeValueAsString(productRepository.findAll());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    public String getAllDepartments() {
        try {
            return new ObjectMapper().writeValueAsString(departmentRepository.findAll());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    public ResponseEntity<String> createClient(@RequestBody Client client) {
        try {
            LOG.info("Client post: " + client);
            clientRepository.save(client);
            return new ResponseEntity<>("Client was created successfully.", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public String getAllClients() {
        try {
            return new ObjectMapper().writeValueAsString(clientRepository.findAll());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    public String getClientByName(String name) {
        try {
            return new ObjectMapper().writeValueAsString(clientRepository.findByName(name));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }


}
