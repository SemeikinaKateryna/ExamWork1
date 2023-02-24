package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class Fabricator describes fabricator
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Fabricator {
    private String name;
    private String country;
    private String paymentDetails;
}
