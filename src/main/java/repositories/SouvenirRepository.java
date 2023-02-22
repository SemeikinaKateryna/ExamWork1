package repositories;

import entity.Souvenir;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface SouvenirRepository {
    File SOUVENIRS = new File("Souvenirs.txt");
    List<Souvenir> read();
    boolean add(Souvenir souvenir);
    Souvenir getByVendorCode(int vendorCode);
    boolean update(int vendorCode,String name, String paymentDetails, Date dateOfIssue,
                   double price, String currency);
    boolean delete(int vendorCode);
}
