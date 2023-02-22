package repositories;

import entity.Souvenir;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.*;

public class SouvenirReporitoryImpl implements SouvenirRepository {
    @Override
    public @NotNull List<Souvenir> read() {
        List<Souvenir> souvenirs = new ArrayList<>();
        GregorianCalendar calendar = new GregorianCalendar();
        try (BufferedReader br = new BufferedReader(new FileReader(SOUVENIRS))) {
            var line = "";
            while ((line = br.readLine()) != null) {
                String[] newLines = line.split("_");
                Souvenir temp = new Souvenir();
                temp.setVendorCode(Integer.parseInt(newLines[0]));
                temp.setName(newLines[1]);
                temp.setPaymentDetails(newLines[2]);
                String[] partsOfDate = newLines[3].split("\\.");
                calendar.set(Calendar.DATE, Integer.parseInt(partsOfDate[0]));
                calendar.set(Calendar.MONTH, Integer.parseInt(partsOfDate[1]) - 1);
                calendar.set(Calendar.YEAR, Integer.parseInt(partsOfDate[2]));
                temp.setDateOfIssue(calendar.getTime());
                temp.setPrice(Double.parseDouble(newLines[4]));
                temp.setCurrency(newLines[5]);
                souvenirs.add(temp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return souvenirs;
    }

    @Override
    public boolean add(Souvenir souvenir) {
        try (FileWriter writer = new FileWriter(SOUVENIRS, true);
             PrintWriter pw = new PrintWriter(writer);
             BufferedReader br = new BufferedReader(new FileReader(SOUVENIRS))) {
            if (br.readLine() != null) {
                pw.print("\n");
            }
            pw.print(souvenir.getVendorCode() + "_" + souvenir.getName() + "_" + souvenir.getPaymentDetails() + "_"
                    + souvenir.getDateOfIssue().getDate() + "." + souvenir.getDateOfIssue().getMonth() + "."
                    + souvenir.getDateOfIssue().getYear() + "_" + souvenir.getPrice() + "_" + souvenir.getCurrency());
            System.out.println(souvenir.getDateOfIssue().getYear());
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Souvenir getByVendorCode(int vendorCode) {
        Souvenir souvenir = null;
        List<Souvenir> souvenirs = read();
        for (Souvenir s : souvenirs) {
            if (s.getVendorCode() == vendorCode) {
                souvenir = s;
            }
        }
        if (souvenir == null) {
            System.out.println("Нет сувениров с заданим штрих-кодом");
        }
        return souvenir;
    }

    @Override
    public boolean update(int vendorCode, String name, String paymentDetails, Date dateOfIssue,
                          double price, String currency) {
        List<Souvenir> souvenirs = read();
        Souvenir byVendorCode = getByVendorCode(vendorCode);
        if(byVendorCode == null){
            return false;
        }
        for (Souvenir s : souvenirs) {
            if (s.getVendorCode() == vendorCode ) {
                s.setName(name);
                s.setPaymentDetails(paymentDetails);
                s.setDateOfIssue(dateOfIssue);
                s.setPrice(price);
                s.setCurrency(currency);
                System.out.println(s);
            }
        }
        try (FileWriter writer = new FileWriter(SOUVENIRS);
             PrintWriter pw = new PrintWriter(writer)) {
            for (Souvenir souvenir : souvenirs) {
                pw.print(souvenir.getVendorCode() + "_" + souvenir.getName() + "_" + souvenir.getPaymentDetails() + "_"
                        + souvenir.getDateOfIssue().getDate() + "." + souvenir.getDateOfIssue().getMonth() + "."
                        + souvenir.getDateOfIssue().getYear() + "_" + souvenir.getPrice() + "_" + souvenir.getCurrency() + "\n");
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(int vendorCode) {
        List<Souvenir> souvenirs = read();
        Souvenir byVendorCode = getByVendorCode(vendorCode);
        if(byVendorCode == null){
            return false;
        }
        for (int i = 0; i < souvenirs.size(); i++) {
            if (souvenirs.get(i).getVendorCode() == vendorCode) {
                souvenirs.remove(souvenirs.get(i));
            }
        }
        try (FileWriter writer = new FileWriter(SOUVENIRS)) {
            for (Souvenir souvenir : souvenirs) {
                writer.write(souvenir.getVendorCode() + "_" + souvenir.getName() + "_" + souvenir.getPaymentDetails() + "_"
                        + souvenir.getDateOfIssue().getDate() + "." + souvenir.getDateOfIssue().getMonth() + "."
                        + souvenir.getDateOfIssue().getYear() + "_" + souvenir.getPrice() + "_" + souvenir.getCurrency() + "\n");
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
