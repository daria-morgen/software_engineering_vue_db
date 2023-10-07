package home.dr.back.model;

import lombok.*;

/**
 * Покупка
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Purchase {

    private long id;

    private Client client;

    private Product product;

}
