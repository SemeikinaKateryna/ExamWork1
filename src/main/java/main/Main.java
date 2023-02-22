package main;

import entity.Fabricator;
import entity.Souvenir;
import repositories.FabricatorRepository;
import repositories.FabricatorRepositoryImpl;
import repositories.SouvenirReporitoryImpl;
import repositories.SouvenirRepository;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Main {
    static SouvenirRepository souvenirRepository;
    static FabricatorRepository fabricatorRepository;
    public static void main(String[] args) {
        fabricatorRepository = new FabricatorRepositoryImpl();
        souvenirRepository = new SouvenirReporitoryImpl();
//        Souvenir s = new Souvenir("Книга \"Україна\"", "UA945493759331338818768838212",
//                new Date(2022, Calendar.FEBRUARY, 21), 1200, "UAN");
//        souvenirRepository.add(s);

//        souvenirRepository.update(3,"y","U",
//                new Date(2020, Calendar.FEBRUARY, 01),123,"U");

//        System.out.println(fabricatorRepository.read());
//        Fabricator fabricator = new Fabricator("FR", "UA");
//        fabricatorRepository.add(fabricator);
//        System.out.println(fabricatorRepository.getByName("FR"));
//        System.out.println(fabricatorRepository.read());

//        souvenirRepository.update(78, "y","U",
//               new Date(2020, Calendar.FEBRUARY, 01),123,"U");
    }
}
