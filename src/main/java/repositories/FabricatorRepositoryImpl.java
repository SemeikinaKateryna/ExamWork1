package repositories;

import entity.Fabricator;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.*;

public class FabricatorRepositoryImpl implements FabricatorRepository{
    @Override
    public @NotNull List<Fabricator> read(){
        List<Fabricator> fabricators = new ArrayList<>();
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
        try( FileWriter writer = new FileWriter(FABRICATORS, true);
            BufferedReader br = new BufferedReader(new FileReader(FABRICATORS)) ){
            if (br.readLine() != null) {
                writer.write("\n");
            }
            writer.write(fabricator.getName() + "-" + fabricator.getCountry() + "-"
            + fabricator.getPaymentDetails());
            return true;
        } catch (IOException e){
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public Fabricator getByName(String name){
        Fabricator fabricator = null;
        List<Fabricator> fabricators = read();
        for (Fabricator f : fabricators) {
            if (Objects.equals(f.getName(), name)){
                fabricator = f;
            }
        }
        return fabricator;
    }
    @Override
    public boolean update(String name, String newName, String country, String paymentDetails) {
        List<Fabricator> fabricators = read();
        Fabricator byName = getByName(name);
        if (byName == null) {
            return false;
        }
        for (Fabricator f : fabricators) {
            if (Objects.equals(f.getName(), name)) {
                f.setName(newName);
                f.setCountry(country);
                f.setPaymentDetails(paymentDetails);
            }
        }
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
    @Override
    public boolean delete(String name){
        List<Fabricator> fabricators = read();
        Fabricator byName = getByName(name);
        if(byName == null){
            return false;
        }
        for (int i = 0; i < fabricators.size(); i++) {
            if (Objects.equals(fabricators.get(i).getName(), name)) {
                fabricators.remove(fabricators.get(i));
            }
        }
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
