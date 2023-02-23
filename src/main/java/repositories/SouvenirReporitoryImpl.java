package repositories;

import entity.Souvenir;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class SouvenirReporitoryImpl implements SouvenirRepository {
    @Override
    public @NotNull Map<String, Souvenir> read() {
        Map<String, Souvenir> souvenirs = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(SOUVENIRS))) {
            var line = "";
            while ((line = br.readLine()) != null) {
                String[] newLines = line.split("_");
                Souvenir temp = new Souvenir();
                temp.setName(newLines[1]);
                temp.setPaymentDetails(newLines[2]);
                String[] partsOfDate = newLines[3].split("\\.");
                LocalDate localDate = LocalDate.of
                        (Integer.parseInt(partsOfDate[2]), Integer.parseInt(partsOfDate[1]),
                                Integer.parseInt(partsOfDate[0]));
                temp.setDateOfIssue(localDate);
                temp.setPrice(Double.parseDouble(newLines[4]));
                temp.setCurrency(newLines[5]);
                souvenirs.put(newLines[0], temp) ;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return souvenirs;
    }

    @Override
    public boolean add(Souvenir souvenir) {
        try (FileWriter writer = new FileWriter(SOUVENIRS, true)) {
            writer.write("\n"
                    + souvenir.getName().charAt(0)
                    + "" + (int) (Math.random()*100 + 1) + "_"
                    + souvenir.getName() + "_"
                    + souvenir.getPaymentDetails() + "_"
                    + souvenir.getDateOfIssue().getDayOfMonth()+ "."
                    + souvenir.getDateOfIssue().getMonth().getValue() + "."
                    + souvenir.getDateOfIssue().getYear() + "_"
                    + souvenir.getPrice() + "_"
                    + souvenir.getCurrency());
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean writeToFileWithoutAppend(Map<String, Souvenir> souvenirs){
        try (FileWriter writer = new FileWriter(SOUVENIRS)) {
            for (Map.Entry<String, Souvenir> entry : souvenirs.entrySet()){
                writer.write(entry.getKey() + "_"
                        + entry.getValue().getName() + "_"
                        + entry.getValue().getPaymentDetails() + "_"
                        + entry.getValue().getDateOfIssue().getDayOfMonth() + "."
                        + entry.getValue().getDateOfIssue().getMonth().getValue() + "."
                        + entry.getValue().getDateOfIssue().getYear() + "_"
                        + entry.getValue().getPrice() + "_"
                        + entry.getValue().getCurrency() + "\n");
            }

            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public boolean update(String vendorCode, String newName, String newPaymentDetails,
                          LocalDate newDateOfIssue, double newPrice, String newCurrency) {
        Map<String, Souvenir> souvenirs = read();
        Souvenir souvenir = souvenirs.get(vendorCode);
        if(souvenir != null){
            souvenir.setName(newName);
            souvenir.setPaymentDetails(newPaymentDetails);
            souvenir.setDateOfIssue(newDateOfIssue);
            souvenir.setPrice(newPrice);
            souvenir.setCurrency(newCurrency);
            writeToFileWithoutAppend(souvenirs);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean delete(String paymentDetails) {
        Map<String, Souvenir> souvenirs = read();
        if(!souvenirs.values().removeIf
                (value -> Objects.equals(value.getPaymentDetails(),paymentDetails))){
            return false;
        }else {
            return writeToFileWithoutAppend(souvenirs);
        }
    }
}
