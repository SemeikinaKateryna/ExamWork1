package repositories;

import entity.Souvenir;

import java.io.File;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface SouvenirRepository {
    File SOUVENIRS = new File("Souvenirs.txt");

    List<Souvenir> read();
    boolean add(Souvenir souvenir);
    Souvenir getByVendorCode(String vendorCode);
    boolean update(String vendorCode,String name, String paymentDetails, LocalDate dateOfIssue,
                   double price, String currency);
    boolean delete(String vendorCode);
}
