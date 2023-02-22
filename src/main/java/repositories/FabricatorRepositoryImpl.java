package repositories;

import entity.Fabricator;
import entity.Souvenir;
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
        try(FileWriter writer = new FileWriter(FABRICATORS, true);
            PrintWriter pw = new PrintWriter(writer);
            BufferedReader br = new BufferedReader(new FileReader(FABRICATORS))) {
            if (br.readLine() != null) {
                pw.print("\n");
            }
            pw.print(fabricator.getName() + "-" + fabricator.getCountry());
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
        if(fabricator == null) {
            System.out.println("Нет производителей с заданым именем");
        }
        return fabricator;
    }
    @Override
    public boolean update(String name, String newName, String country){
        List<Fabricator> fabricators = read();
        Fabricator byName = getByName(name);
        if(byName == null){
            return false;
        }
        for (Fabricator f : fabricators) {
            if (Objects.equals(f.getName(),name)) {
                f.setName(newName);
                f.setCountry(country);
            }
        }
        try (FileWriter writer = new FileWriter(FABRICATORS);
             PrintWriter pw = new PrintWriter(writer)) {
            for (Fabricator fabricator : fabricators) {
                pw.print(fabricator.getName() + "-" + fabricator.getCountry() + "\n");
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
                writer.write( fabricator.getName() + "-" + fabricator.getCountry() + "\n");
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
