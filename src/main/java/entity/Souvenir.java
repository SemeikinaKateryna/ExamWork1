package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Souvenir {
    String vendorCode;
    String name;
    String paymentDetails;
    LocalDate dateOfIssue;
    double price;
    String currency;
    public Souvenir(String name, String paymentDetails, LocalDate dateOfIssue,
                    double price, String currency) {
        this.name = name;
        this.vendorCode = this.name.charAt(0) + "" + (int) (Math.random()*100 + 1);

        this.paymentDetails = paymentDetails;
        this.dateOfIssue = dateOfIssue;
        this.price = price;
        this.currency = currency;
    }
}
