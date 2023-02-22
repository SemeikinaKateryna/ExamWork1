package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Souvenir {
    int vendorCode;
    String name;
    String paymentDetails;
    Date dateOfIssue;
    double price;
    String currency;
    public Souvenir(String name, String paymentDetails, Date dateOfIssue,
                    double price, String currency) {
        this.vendorCode = (int) (Math.random()*100 + 1);
        this.name = name;
        this.paymentDetails = paymentDetails;
        this.dateOfIssue = dateOfIssue;
        this.price = price;
        this.currency = currency;
    }
}
