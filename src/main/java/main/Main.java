package main;

import entity.Fabricator;
import entity.Souvenir;
import repositories.FabricatorRepository;
import repositories.FabricatorRepositoryImpl;
import repositories.SouvenirReporitoryImpl;
import repositories.SouvenirRepository;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Main {
    static SouvenirRepository souvenirRepository;
    static FabricatorRepository fabricatorRepository;
    public static void main(String[] args) {
        //fabricatorRepository = new FabricatorRepositoryImpl();
        souvenirRepository = new SouvenirReporitoryImpl();

        //???
        souvenirRepository.getByVendorCode("В12");
        //System.out.println(fabricatorRepository.update("Aliexpress", "Aliexpress", "USA"));

//        LocalDate localDate = LocalDate.of(2020,02,02);
//        LocalDate localDate1 = LocalDate.of(2023,4,10);
//        System.out.println(localDate.getMonth().getValue());

//        Souvenir s = new Souvenir("Книга \"Україна\"", "UA945493759331338818768838212",
//                localDate, 1200, "UAN");
//        souvenirRepository.add(s);

//        System.out.println(souvenirRepository.update
//                ("К78","y","U",localDate1,123.56,"UAN"));
//        List<Souvenir> souvenirs = souvenirRepository.read();
//        System.out.println(souvenirs);

//        souvenirRepository.update(3,"y","U",
//                new Date(2020, Calendar.FEBRUARY, 01),123,"U");

//        System.out.println(fabricatorRepository.read());
//        Fabricator fabricator = new Fabricator("FR", "UA");
//        fabricatorRepository.add(fabricator);
//        System.out.println(fabricatorRepository.getByName("FR"));
//        System.out.println(fabricatorRepository.read());

    }
}
