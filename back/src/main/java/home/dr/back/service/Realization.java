package home.dr.back.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import home.dr.back.controller.HeadController;
import home.dr.back.model.Client;
import home.dr.back.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

    public String getAllProducts() {
        try {
            return new ObjectMapper().writeValueAsString(productRepository.findAll());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    public ResponseEntity<String> createClient(@RequestBody Client client) {
        try {
            LOG.info("Client post: "+ client);
            clientRepository.save(client);
            return new ResponseEntity<>("Client was created successfully.", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
