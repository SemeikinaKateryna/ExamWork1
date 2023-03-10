package menu;

import entity.Fabricator;
import entity.Souvenir;
import input.Input;
import repositories.FabricatorRepository;
import repositories.FabricatorRepositoryImpl;
import repositories.SouvenirRepositoryImpl;
import repositories.SouvenirRepository;
import util.PriceConverter;

import java.util.*;

/**
 * Class Menu is designed for display of all program functions.
 * This class contains choice variables
 * and methods which works with objects through their repositories.
 */

public class Menu {
    public static final int ADD_FABRICATOR = 1;
    public static final int UPDATE_FABRICATOR = 2;
    public static final int SHOW_ALL_FABRICATORS = 3;
    public static final int ADD_SOUVENIR = 4;
    public static final int UPDATE_SOUVENIR = 5;
    public static final int SHOW_ALL_SOUVENIRS = 6;
    public static final int SHOW_SOUVENIRS_BY_FABRICATOR = 7;
    public static final int SHOW_SOUVENIRS_BY_COUNTRY = 8;
    public static final int SHOW_FABRICATORS_BY_LESS_SOUVENIR_PRICE = 9;
    public static final int SHOW_FABRICATORS_AND_THEIR_SOUVENIRS = 10;
    public static final int SHOW_FABRICATORS_BY_SOUVENIR_AND_YEAR = 11;
    public static final int SHOW_SOUVENIRS_BY_YEAR = 12;
    public static final int DELETE_FABRICATOR_AND_THEIR_SOUVENIRS = 13;

    private final SouvenirRepository souvenirRepository;
    private final FabricatorRepository fabricatorRepository;

    private final Input input;

    public Menu() {
        souvenirRepository = new SouvenirRepositoryImpl();
        fabricatorRepository = new FabricatorRepositoryImpl();
        input = new Input();
    }

    public int menu() {
        int choice = -1;
        try {
            System.out.println("""
                    1.  ADD_FABRICATOR
                    2.  UPDATE_FABRICATOR
                    3.  SHOW_ALL_FABRICATORS
                    4.  ADD_SOUVENIR
                    5.  UPDATE_SOUVENIR
                    6.  SHOW_ALL_SOUVENIRS
                    7.  SHOW_SOUVENIRS_BY_FABRICATOR
                    8.  SHOW_SOUVENIRS_BY_COUNTRY
                    9.  SHOW_FABRICATORS_BY_LESS_SOUVENIR_PRICE
                    10. SHOW_FABRICATORS_AND_THEIR_SOUVENIRS
                    11. SHOW_FABRICATORS_BY_SOUVENIR_AND_YEAR
                    12. SHOW_SOUVENIRS_BY_YEAR
                    13. DELETE_FABRICATOR_AND_THEIR_SOUVENIRS
                    """);
            choice = Integer.parseInt(input.input("choice "));
        } catch (NumberFormatException e) {
            input.showMessage("Please, enter number of choice not string");
        }
        return choice;
    }

    public void run() {
        int choice;
        while ((choice = menu()) != 0) {
            switch (choice) {
                case ADD_FABRICATOR -> addFabricator();

                case UPDATE_FABRICATOR -> updateFabricator();

                case SHOW_ALL_FABRICATORS -> showAllFabricators();

                case ADD_SOUVENIR -> addSouvenir();

                case UPDATE_SOUVENIR -> updateSouvenir();

                case SHOW_ALL_SOUVENIRS -> showAllSouvenirs();

                case SHOW_SOUVENIRS_BY_FABRICATOR -> showSouvenirsByFabricator();

                case SHOW_SOUVENIRS_BY_COUNTRY -> showSouvenirsByCountry();

                case SHOW_FABRICATORS_BY_LESS_SOUVENIR_PRICE -> showFabricatorsByLessSouvenirPrice();

                case SHOW_FABRICATORS_AND_THEIR_SOUVENIRS -> showFabricatorsAndTheirSouvenirs();

                case SHOW_FABRICATORS_BY_SOUVENIR_AND_YEAR -> showFabricatorsBySouvenirAndYear();

                case SHOW_SOUVENIRS_BY_YEAR -> showSouvenirsByYear();

                case DELETE_FABRICATOR_AND_THEIR_SOUVENIRS -> deleteFabricatorAndTheirSouvenirs();

                default -> input.showMessage("There are no cases for " + choice + ". Try again!");
            }
        }

    }
    public void addFabricator() {
        if (!fabricatorRepository.add(input.inputFabricator())) {
            input.showMessage("This fabricator is already exists");
        }
    }

    public void updateFabricator () {
        input.showMessage("What fabricator do you want to update?");
        String name = input.input("fabricator name");
        if (fabricatorRepository.getByName(name).isPresent()) {
            System.out.println(fabricatorRepository.getByName(name));
            Fabricator newFabricator = input.inputFabricator();
            if (!fabricatorRepository.update
                    (name, newFabricator.getName(), newFabricator.getCountry(),
                            newFabricator.getPaymentDetails())) {
                input.showMessage("This fabricator is already exists");
            }
        } else {
            input.showMessage("There are no fabricators with name " + name);
        }
    }

    public void showAllFabricators () {
        fabricatorRepository.read().forEach(System.out::println);
    }

    public void addSouvenir(){
        souvenirRepository.add(input.inputSouvenir());
    }
    public void updateSouvenir(){
        input.showMessage("What souvenir do you want to update?");
        String vendorCode = input.input("vendor code");
        Souvenir souvenir = input.inputSouvenir();
        if (!souvenirRepository.update(vendorCode, souvenir.getName(),
                souvenir.getPaymentDetails(), souvenir.getDateOfIssue(),
                souvenir.getPrice(), souvenir.getCurrency())) {
            input.showMessage("There are no souvenirs with vendor code " + vendorCode);
        }
    }
    public void showAllSouvenirs(){
        souvenirRepository.read().forEach((k, v) -> System.out.println((k + ":" + v)));
    }
    public void showSouvenirsByFabricator(){
        String name = input.input("fabricator name");
        Optional<Fabricator> fabricator = fabricatorRepository.getByName(name);
        if (fabricator.isPresent()) {
            input.showMessage("Souvenirs by " + name + ":");
            List<Souvenir> souvenirsByFabricator = new ArrayList<>();
            Map<String, Souvenir> souvenirMap = souvenirRepository.read();
            for (Souvenir souvenir : souvenirMap.values()) {
                if (Objects.equals(souvenir.getPaymentDetails(),
                        fabricator.get().getPaymentDetails())) {
                    souvenirsByFabricator.add(souvenir);
                }
            }
            if (!souvenirsByFabricator.isEmpty()) {
                souvenirsByFabricator.forEach(System.out::println);
            } else {
                input.showMessage("There are no souvenirs for fabricator " + fabricator);
            }
        } else {
            input.showMessage("There are no fabricators with name " + name);
        }
    }
    public void showSouvenirsByCountry() {
        String country = input.input("country");
        List<Fabricator> countryFabricator = new ArrayList<>();
        Set<Fabricator> fabricatorSet = fabricatorRepository.read();
        for (Fabricator fabricator : fabricatorSet) {
            if (Objects.equals(fabricator.getCountry(), country)) {
                countryFabricator.add(fabricator);
            }
        }
        if (!countryFabricator.isEmpty()) {
            Map<String, Souvenir> souvenirMap = souvenirRepository.read();
            List<Souvenir> souvenirsByCountry = new ArrayList<>();
            for (Fabricator fabricator : countryFabricator) {
                for (Souvenir souvenir : souvenirMap.values()) {
                    if (Objects.equals(souvenir.getPaymentDetails(),
                            fabricator.getPaymentDetails())) {
                        souvenirsByCountry.add(souvenir);
                    }
                }
            }
            if (souvenirsByCountry.isEmpty()) {
                input.showMessage("There are no souvenirs for country " + country);
            } else {
                input.showMessage("Souvenirs by " + country + ":");
                souvenirsByCountry.forEach(System.out::println);
            }
        } else {
            input.showMessage("There are no fabricators for country " + country);
        }
    }
    public void showFabricatorsByLessSouvenirPrice() {
        double price = Double.parseDouble(input.input("price in UAN"));
        Map<String, Souvenir> souvenirMap = souvenirRepository.read();
        Set<Fabricator> fabricatorSet = fabricatorRepository.read();
        Set<Fabricator> fabricatorLessPriceSet = new HashSet<>();
        PriceConverter conventor = new PriceConverter();
        double UANprice;
        for (Souvenir souvenir : souvenirMap.values()) {
            UANprice = 0;
            if (souvenir.getCurrency().equals("USD")) {
                UANprice = conventor.usdToUan(souvenir.getPrice());
            } else if (souvenir.getCurrency().equals("EUR")) {
                UANprice = conventor.eurToUan(souvenir.getPrice());
            } else {
                UANprice = souvenir.getPrice();
            }
            if (UANprice < price) {
                for (Fabricator fabr : fabricatorSet) {
                    if (Objects.equals(fabr.getPaymentDetails(),
                            souvenir.getPaymentDetails())) {
                        fabricatorLessPriceSet.add(fabr);
                    }
                }
            }
        }
        if (fabricatorLessPriceSet.isEmpty()) {
            input.showMessage("There are no fabricators with souvenirs less then "
                    + price + "UAN");
        } else {
            fabricatorLessPriceSet.forEach(System.out::println);
        }
    }
    public void showFabricatorsAndTheirSouvenirs () {
        Map<String, Souvenir> souvenirMap = souvenirRepository.read();
        Set<Fabricator> fabricatorSet = fabricatorRepository.read();
        for (Fabricator fabricator : fabricatorSet) {
            System.out.println(fabricator);
            for (Souvenir souvenir : souvenirMap.values()) {
                if (Objects.equals(fabricator.getPaymentDetails(),
                        souvenir.getPaymentDetails())) {
                    System.out.println(souvenir);
                }
            }
            System.out.println();
        }
    }

    public void showFabricatorsBySouvenirAndYear(){
        String souvenirName = input.input("name");
        String year = input.input("year");
        Map<String, Souvenir> souvenirMap = souvenirRepository.read();
        Set<Fabricator> fabricatorSet = fabricatorRepository.read();
        List<Fabricator> fabricators = new ArrayList<>();
        for (Souvenir souvenir : souvenirMap.values()) {
            if (Objects.equals(souvenir.getName(), souvenirName) &&
                    Objects.equals(souvenir.getDateOfIssue().getYear(), Integer.parseInt(year))) {
                for (Fabricator fabricator : fabricatorSet) {
                    if (Objects.equals(fabricator.getPaymentDetails(),
                            souvenir.getPaymentDetails())) {
                        fabricators.add(fabricator);
                    }
                }
            }

        }
        if (fabricators.isEmpty()) {
            input.showMessage("There are no fabricators for souvenir " + souvenirName
                    + " produced in " + year);
        } else {
            input.showMessage("Fabricator for souvenir " + souvenirName
                    + " produced in " + year + ":");
            fabricators.forEach(System.out::println);
        }
    }
    public void showSouvenirsByYear () {
        String year = input.input("year");
        Map<String, Souvenir> souvenirMap = souvenirRepository.read();
        List<Souvenir> souvenirs = new ArrayList<>();
        for (Souvenir souvenir : souvenirMap.values()) {
            if (Objects.equals(souvenir.getDateOfIssue().getYear(), Integer.parseInt(year))) {
                souvenirs.add(souvenir);
            }
        }
        if (souvenirs.isEmpty()) {
            input.showMessage("There are no souvenirs" + " produced in " + year);
        } else {
            input.showMessage("Souvenirs produced in " + year + ":");
            souvenirs.forEach(System.out::println);
        }
    }

    public void deleteFabricatorAndTheirSouvenirs () {
        String fabricator = input.input("fabricator");
        Optional<Fabricator> fabricatorEntity = fabricatorRepository.getByName(fabricator);
        if (fabricatorEntity.isPresent()) {
            String paymentDetails = fabricatorEntity.get().getPaymentDetails();
            fabricatorRepository.delete(fabricator);
            if (!souvenirRepository.delete(paymentDetails)) {
                input.showMessage("There no souvenirs for fabricator " + fabricator);
            }
        } else {
            input.showMessage("There are no fabricator with name " + fabricator);
        }
    }


}
