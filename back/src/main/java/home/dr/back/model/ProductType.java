package home.dr.back.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Тип товара
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductType {

    private long id;

    private String name;
}
