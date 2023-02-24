package repositories;

import entity.Fabricator;

import java.io.File;
import java.util.Set;

/**
 * Interface FabricatorRepository describes basic functions of working with an object Fabricator.
 * Also, this class contains file, where class objects are read and written from.
 **/

public interface FabricatorRepository {
    File FABRICATORS = new File("Fabricators.txt");

    Set<Fabricator> read();
    boolean add(Fabricator fabricator);

    /**The function of Fabricator's name search added to convenient work.*/
    Fabricator getByName(String name);

    /**Editing and deleting an object occurs by name.*/
    boolean update(String name, String newName, String newCountry, String newPaymentDetails);
    boolean delete(String name);
}
