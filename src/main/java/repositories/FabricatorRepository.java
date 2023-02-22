package repositories;

import entity.Fabricator;

import java.io.File;
import java.util.List;

public interface FabricatorRepository {
    File FABRICATORS = new File("Fabricators.txt");

    List<Fabricator> read();
    boolean add(Fabricator fabricator);
    Fabricator getByName(String name);
    boolean update(String name, String newName, String country, String paymentDetails);
    boolean delete(String name);
}
