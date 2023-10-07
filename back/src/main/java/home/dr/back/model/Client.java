package home.dr.back.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Клиент
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client {

    private long id;

    private String name;

    private long tel;

    public Client(String name, long tel) {
    }
}
