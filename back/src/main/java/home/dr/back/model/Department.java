package home.dr.back.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Отдел
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Department {

    private long id;

    private String name;
}
