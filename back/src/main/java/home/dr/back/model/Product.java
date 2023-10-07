package home.dr.back.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Товар
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    private long id;

    private String name;

    private String color;

    private String size;

    private double prise;

    private ProductType productType;

    private Department department;

}
