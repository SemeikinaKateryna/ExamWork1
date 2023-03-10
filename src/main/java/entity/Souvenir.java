package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Class Souvenir describes souvenir
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Souvenir {
    private String name;
    private String paymentDetails;
    private LocalDate dateOfIssue;
    private double price;
    private String currency;
}
