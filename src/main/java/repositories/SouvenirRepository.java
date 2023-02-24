package repositories;

import entity.Souvenir;

import java.io.File;
import java.time.LocalDate;
import java.util.Map;

/**
 * Interface SouvenirRepository describes basic functions of working with an object Souvenir.
 * Also, this class contains file, where class objects are read and written from.
 */

public interface SouvenirRepository {
    File SOUVENIRS = new File("Souvenirs.txt");

    /**Vendor code is a key of the Souvenir's map*/
    Map<String, Souvenir> read() ;
    boolean add(Souvenir souvenir);
    boolean update(String vendorCode, String newName, String newPaymentDetails,
                   LocalDate newDateOfIssue, double newPrice, String newCurrency);
    boolean delete(String paymentDetails);
}
