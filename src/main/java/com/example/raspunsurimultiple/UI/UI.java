package com.example.raspunsurimultiple.UI;

import com.example.raspunsurimultiple.Domain.Intrebare;
import com.example.raspunsurimultiple.Service.Service;

import java.util.List;
import java.util.Scanner;

public class UI {
    private Service service;
    private final Scanner scanner;

    public UI(Service service) {
        this.service = service;
        this.scanner = new Scanner(System.in);
    }

    public void load_entities() {
        service.getAllEntitiesList();
    }

    public void getAll() {
        List<Intrebare> produsList = service.getAll();
        for (Intrebare building : produsList) {
            System.out.println(building.toString());
        }
    }

    public void menu() {
        try {
            service.addEntities();
        } catch (RuntimeException exception) {
            System.out.println(exception.getMessage());
        }

        boolean exit = false;
        while (!exit) {
            System.out.println("Meniu:");
            System.out.println("1. Afisare date");
            System.out.println("2. Exit");

            System.out.print("Alegeti o optiune: ");
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    getAll();
                    break;
                case 2:
                    exit = true;
                    break;
                default:
                    System.out.println("Optiune invalida. Va rugam sa alegeti din nou.");
            }
        }
    }
}
