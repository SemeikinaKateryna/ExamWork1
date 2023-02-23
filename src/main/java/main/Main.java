package main;

import entity.Fabricator;
import menu.Menu;
import repositories.FabricatorRepository;
import repositories.FabricatorRepositoryImpl;

import java.io.*;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.run();
    }
}
