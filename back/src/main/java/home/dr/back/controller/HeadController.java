package home.dr.back.controller;

import home.dr.back.model.Client;
import home.dr.back.model.Product;
import home.dr.back.service.Realization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class HeadController {
    private static final Logger LOG = LoggerFactory.getLogger(HeadController.class);

    private final Realization realization;

    public HeadController(Realization realization) {
        this.realization = realization;
    }


    /**
     * - находить товар по его названию, показывать отдел, где его можно
     * приобрести;
     *
     * @param name
     * @return
     */

    @GetMapping("/productByName/{name}")
    public String getProductByName(@PathVariable("name") String name) {
        return realization.findProductByName(name);
    }

    /**
     * - показывать ассортимент выбранного отдела;
     *
     * @param name
     * @return
     */
    @GetMapping("/productsByDepartment/{name}")
    public String getProductsByDepartment(@PathVariable("name") String name) {
        return realization.findProductsByDepartment(name);
    }

    @PostMapping("/newProduct")
    public ResponseEntity<String> createProduct(@RequestBody Product product) {
        return realization.createProduct(product);
    }

    @GetMapping("/products")
    public String getAllProducts() {
        return realization.getAllProducts();
    }

    @GetMapping("/departments")
    public String getAllDepartments() {
        return realization.getAllDepartments();
    }


    @PostMapping("/newClient")
    public ResponseEntity<String> createClient(@RequestBody Client client) {
        return realization.createClient(client);
    }

    @GetMapping("/clients")
    public String getAllClients() {
        return realization.getAllClients();
    }

    @GetMapping("/clientByName/{name}")
    public String getAllClientByName(@PathVariable("name") String name) {
        return realization.getClientByName(name);
    }


}
