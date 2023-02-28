package repositories;

import entity.Fabricator;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.*;

/**
 * Class FabricatorRepositoryImpl implements all functions of class FabricatorRepository.
 */

public class FabricatorRepositoryImpl implements FabricatorRepository{
    @Override
    public @NotNull Set<Fabricator> read(){
        Set<Fabricator> fabricators = new HashSet<>();
            try (BufferedReader br = new BufferedReader(new FileReader(FABRICATORS))) {
                var line = "";
                while ((line = br.readLine()) != null) {
                    String[] newLines = line.split("-");
                    Fabricator temp = new Fabricator();
                    temp.setName(newLines[0]);
                    temp.setCountry(newLines[1]);
                    temp.setPaymentDetails(newLines[2]);
                    fabricators.add(temp);
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            return fabricators;
    }

    @Override
    public boolean add(Fabricator fabricator){
        try( FileWriter writer = new FileWriter(FABRICATORS, true)){
            Set<Fabricator> fabricators = read();
            if(fabricators.add(fabricator)){
                writer.write("\n"
                        + fabricator.getName() + "-"
                        + fabricator.getCountry() + "-"
                        + fabricator.getPaymentDetails());
                return true;
            }else{
                return false;
            }
        } catch (IOException e){
            e.printStackTrace();
            return false;
        }
    }


    public Optional<Fabricator> getByName(String name){
        Optional<Fabricator> fabricator = Optional.empty();
        Set<Fabricator> fabricators = read();
        for (Fabricator f : fabricators) {
            if (Objects.equals(f.getName(), name)){
                fabricator = Optional.of(f);
            }
        }
        return fabricator;
    }

    @Override
    public boolean update(String name, String newName, String newCountry,
                          String newPaymentDetails) {
        Set<Fabricator> fabricators = read();
        //Fabricator byName = .get();
        if(getByName(name).isEmpty()){
            return false;
        }
//        if (byName == null) {
//
//        }
        Fabricator f = fabricators.stream().
                filter(data -> Objects.equals(data.getName(), name)).
                findFirst().get();
        Fabricator temp = f;
        temp.setName(newName);
        temp.setCountry(newCountry);
        temp.setPaymentDetails(newPaymentDetails);
        if(fabricators.add(temp)){
            fabricators.remove(f);
            writeToFileWithoutAppend(fabricators);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean delete(String name){
        Set<Fabricator> fabricators = read();
        if(!fabricators.remove(fabricators.stream().
                filter(data -> Objects.equals(data.getName(), name)).
                findFirst().get())){
            return false;
        }else{
            return writeToFileWithoutAppend(fabricators);
        }
    }

    /**
     * This function write to file collection without append.
     * It's a helper function, that helps to avoid code duplication
     * in delete and update methods.
     */
    private boolean writeToFileWithoutAppend(Set<Fabricator> fabricators) {
        try (FileWriter writer = new FileWriter(FABRICATORS)) {
            int counter = 0;
            for (Fabricator fabricator : fabricators) {
                counter++;
                writer.write(fabricator.getName() + "-" + fabricator.getCountry() + "-"
                            + fabricator.getPaymentDetails());
                if(counter != fabricators.size()) {
                    writer.write("\n");
                }
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
