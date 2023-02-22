package menu;


import entity.Fabricator;
import entity.Souvenir;
import input.Input;
import repositories.FabricatorRepository;
import repositories.FabricatorRepositoryImpl;
import repositories.SouvenirReporitoryImpl;
import repositories.SouvenirRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Menu {
    //public static final int EXIT = 0;
    public static final int ADD_FABRICATOR = 1;
    public static final int UPDATE_FABRICATOR = 2;
    public static final int SHOW_ALL_FABRICATORS = 3;
    public static final int ADD_SOUVENIR = 4;
    public static final int UPDATE_SOUVENIR  = 5;
    public static final int SHOW_ALL_SOUVENIRS = 6;
    public static final int SHOW_SOUVENIRS_BY_FABRICATOR = 7;
    public static final int SHOW_SOUVENIRS_BY_COUNTRY = 8;
    public static final int SHOW_FABRICATORS_BY_LESS_SOUVENIR_PRICE = 9;
    public static final int SHOW_FABRICATORS_AND_THEIR_SOUVENIRS = 10;
    public static final int SHOW_FABRICATORS_BY_SOUVENIR_AND_YEAR = 11;
    public static final int SHOW_SOUVENIRS_BY_YEAR = 12;
    public static final int DELETE_FABRICATOR_AND_THEIR_SOUVENIRS = 13;

    private SouvenirRepository souvenirRepository;
    private FabricatorRepository fabricatorRepository;

    private Scanner scanner;
    private Input input;

    public Menu() {
        souvenirRepository = new SouvenirReporitoryImpl();
        fabricatorRepository = new FabricatorRepositoryImpl();
        scanner = new Scanner(System.in);
        input = new Input();
    }

    private int menu(){
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
        int res = scanner.nextInt();
        scanner.nextLine();
        return res;
    }
    public void run(){
        int choice;
        while ((choice = menu()) != 0) {
            switch (choice) {
                case ADD_FABRICATOR -> {
                    fabricatorRepository.add(input.inputFabricator());
                }
                case UPDATE_FABRICATOR -> {
                    input.showMessage("What fabricator do you want to update? (name)");
                    String name = input.inputFabricatorName();
                    if(fabricatorRepository.getByName(name) != null) {
                        System.out.println(fabricatorRepository.getByName(name));
                        Fabricator newFabricator = input.inputFabricator();
                        fabricatorRepository.update
                                (name, newFabricator.getName(), newFabricator.getCountry(),
                                        newFabricator.getPaymentDetails());
                    }else{
                        input.showMessage("There are no fabricators with such name");
                    }
                }
                case SHOW_ALL_FABRICATORS -> {
                    fabricatorRepository.read().forEach(System.out::println);
                }
                case ADD_SOUVENIR -> {
                    souvenirRepository.add(input.inputSouvenir());
                }
                case UPDATE_SOUVENIR -> {
                    input.showMessage("What souvenir do you want to update? (vendor code)");
                    String vendorCode = input.inputSouvenirVendorCode();
                    if(souvenirRepository.getByVendorCode(vendorCode) != null){
                        System.out.println(souvenirRepository.getByVendorCode(vendorCode));
                        Souvenir souvenir = input.inputSouvenir();
                        souvenirRepository.update
                                (vendorCode, souvenir.getName(), souvenir.getPaymentDetails(),
                                        souvenir.getDateOfIssue(), souvenir.getPrice(),
                                        souvenir.getCurrency());
                    }else{
                        input.showMessage("There are no souvenirs with such vendor code");
                    }
                }
                case SHOW_ALL_SOUVENIRS -> {
                    souvenirRepository.read().forEach(System.out::println);
                }
                case SHOW_SOUVENIRS_BY_FABRICATOR -> {
                    String name = input.inputFabricatorName();
                    Fabricator fabricator = fabricatorRepository.getByName(name);
                    if(fabricator != null) {
                        List<Souvenir> souvenirList = souvenirRepository.read();
                        List<Souvenir> souvenirsByFabricator = null;
                        for (Souvenir souvenir: souvenirList) {
                            if(Objects.equals(souvenir.getPaymentDetails(),
                                    fabricator.getPaymentDetails())){
                                souvenirsByFabricator = new ArrayList<>();
                                souvenirsByFabricator.add(souvenir);
                            }
                        }
                        if(souvenirsByFabricator != null){
                            souvenirsByFabricator.forEach(System.out::println);
                        }else {
                            input.showMessage("There are no souvenirs for such fabricator");
                        }
                    }else{
                        input.showMessage("There are no fabricators with such name");
                    }
                }
            }

        }
    }

}
