package repositories;

import entity.Fabricator;

import java.io.File;
import java.util.Set;

public interface FabricatorRepository {
    File FABRICATORS = new File("Fabricators.txt");

    Set<Fabricator> read();
    boolean add(Fabricator fabricator);
    Fabricator getByName(String name);
    boolean update(String name, String newName, String newCountry, String newPaymentDetails);
    boolean delete(String name);
}
