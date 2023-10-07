package home.dr.back.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import home.dr.back.model.Client;
import home.dr.back.repository.ClientRepository;
import home.dr.back.service.Realization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HeadController {
    private static final Logger LOG = LoggerFactory.getLogger(HeadController.class);

    private final Realization realization;

    public HeadController(Realization realization) {
        this.realization = realization;
    }


    @GetMapping("/products")
    public String getAllProducts() {
        return realization.getAllProducts();
    }

    @PostMapping("/newClient")
    public ResponseEntity<String> createClient(@RequestBody Client client) {

    }

}
