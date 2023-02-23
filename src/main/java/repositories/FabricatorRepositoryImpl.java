package repositories;

import entity.Fabricator;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

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
                writer.write("\n" + fabricator.getName() + "-" + fabricator.getCountry() + "-"
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


    public Fabricator getByName(String name){
        Fabricator fabricator = null;
        Set<Fabricator> fabricators = read();
        for (Fabricator f : fabricators) {
            if (Objects.equals(f.getName(), name)){
                fabricator = f;
            }
        }
        return fabricator;
    }

    @Override
    public boolean update(String name, String newName, String newCountry,
                          String newPaymentDetails) {
        Set<Fabricator> fabricators = read();
        Fabricator byName = getByName(name);
        if (byName == null) {
            return false;
        }
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
        Fabricator byName = getByName(name);
        if(byName == null){
            return false;
        }
        fabricators.remove(fabricators.stream().
                filter(data -> Objects.equals(data.getName(), name)).
                findFirst().get());
        return writeToFileWithoutAppend(fabricators);
    }

    private boolean writeToFileWithoutAppend(Set<Fabricator> fabricators) {
        try (FileWriter writer = new FileWriter(FABRICATORS)) {
            for (Fabricator fabricator : fabricators) {
                writer.write(fabricator.getName() + "-" + fabricator.getCountry() + "-"
                        + fabricator.getPaymentDetails() + "\n");
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
