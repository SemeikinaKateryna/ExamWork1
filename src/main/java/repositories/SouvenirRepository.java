package repositories;

import entity.Souvenir;

import java.io.File;
import java.time.LocalDate;
import java.util.Map;

public interface SouvenirRepository {
    File SOUVENIRS = new File("Souvenirs.txt");

    Map<String, Souvenir> read() ;
    boolean add(Souvenir souvenir);
    boolean update(String vendorCode, String newName, String newPaymentDetails,
                   LocalDate newDateOfIssue, double newPrice, String newCurrency);
    boolean delete(String vendorCode);
}
