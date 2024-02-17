package com.example.raspunsurimultiple.Service;

import com.example.raspunsurimultiple.Domain.Intrebare;
import com.example.raspunsurimultiple.Repository.MemoryRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Service {
    private MemoryRepository<Intrebare> memoryRepository;

    public Service(MemoryRepository<Intrebare> memoryRepository) {
        this.memoryRepository = memoryRepository;
    }

    public void add(Intrebare produs) {
        memoryRepository.add(produs);
    }
    public List<Intrebare> getAll() {
        return memoryRepository.getAll();
    }

    public void addEntities() {
        Random random = new Random();
        Intrebare intrebare1 = new Intrebare(random.nextInt(1001) + 1000, "Ce este Java?", "Limbaj de programare", "O fructă tropicală", "Un animal exotic", "Limbaj de programare", "usor");
        Intrebare intrebare2 = new Intrebare(random.nextInt(1001) + 1000, "Care este capitala Franței?", "Londra", "Paris", "Berlin", "Paris", "mediu");
        Intrebare intrebare3 = new Intrebare(random.nextInt(1001) + 1000, "Câte planete există în sistemul solar?", "7", "8", "9", "8", "greu");
        Intrebare intrebare4 = new Intrebare(random.nextInt(1001) + 1000, "Ce este un algoritm?", "Un tip de ciocolată", "Un pas de dans", "O metodă matematică", "O metodă matematică", "mediu");
        Intrebare intrebare5 = new Intrebare(random.nextInt(1001) + 1000, "Care este formula pentru calcularea perimetrului unui cerc?", "P = 2 * r", "P = π * r", "P = r^2", "P = 2 * π * r", "greu");
        Intrebare intrebare6 = new Intrebare(random.nextInt(1001) + 1000, "Cine este autorul piesei 'Romeo și Julieta'?", "William Shakespeare", "Charles Dickens", "Jane Austen", "William Shakespeare", "usor");
        Intrebare intrebare7 = new Intrebare(random.nextInt(1001) + 1000, "Cum se numește cel mai mare ocean al Pământului?", "Atlantic", "Indian", "Pacific", "Pacific", "mediu");
        Intrebare intrebare8 = new Intrebare(random.nextInt(1001) + 1000, "Ce este programarea orientată pe obiect?", "Un film de acțiune", "Un stil de programare", "Un sport extrem", "Un stil de programare", "greu");
        Intrebare intrebare9 = new Intrebare(random.nextInt(1001) + 1000, "Cine a inventat becul electric?", "Thomas Edison", "Nikola Tesla", "Albert Einstein", "Thomas Edison", "usor");
        Intrebare intrebare10 = new Intrebare(random.nextInt(1001) + 1000, "Ce este efectul de seră?", "O metodă de cultivare a plantelor", "Un fenomen atmosferic", "O boală a plantelor", "Un fenomen atmosferic", "mediu");

        memoryRepository.add(intrebare1);
        memoryRepository.add(intrebare2);
        memoryRepository.add(intrebare3);
        memoryRepository.add(intrebare4);
        memoryRepository.add(intrebare5);
        memoryRepository.add(intrebare6);
        memoryRepository.add(intrebare7);
        memoryRepository.add(intrebare8);
        memoryRepository.add(intrebare9);
        memoryRepository.add(intrebare10);

    }

    public ArrayList<Intrebare> getAllEntitiesList() {
        ArrayList<Intrebare> entitiesList = new ArrayList<>();
        for (Intrebare entity : memoryRepository.getAll()) {
            entitiesList.add(entity);
        }
        return entitiesList;
    }

    public List<Intrebare> obtineSubListaDupaPunctaj(String dificultate, int numarIntrebari) {
        List<Intrebare> allIntrebari = getAllEntitiesList();
        int lungimeLista = allIntrebari.size();

        if (numarIntrebari > lungimeLista) {
            throw new IllegalArgumentException("Numărul de elemente cerut depășește lungimea listei.");
        }

        Random random = new Random();

        List<Intrebare> subLista = new ArrayList<>();

        int numarMinimIntrebariDificultateSpecificata = (int) Math.ceil(numarIntrebari / 2.0);
        int numarIntrebariDificultateSpecificata = 0;

        int maxAttempts = 3 * numarIntrebari;

        while (numarIntrebariDificultateSpecificata < numarMinimIntrebariDificultateSpecificata ) {
            int attempts = 0;
            int indexAleator;

            do {
                if (attempts >= maxAttempts) {
                    throw new RuntimeException("Nu s-au putut găsi suficiente întrebări conform criteriilor specificate.");
                }

                indexAleator = random.nextInt(lungimeLista);
                attempts++;
            } while (subLista.contains(allIntrebari.get(indexAleator)) ||
                    !allIntrebari.get(indexAleator).getDificultate().equals(dificultate));

            numarIntrebariDificultateSpecificata++;
            subLista.add(allIntrebari.get(indexAleator));
        }

        if (numarIntrebariDificultateSpecificata < numarIntrebari) {
            int restIntrebari = numarIntrebari - numarIntrebariDificultateSpecificata;
            int nrIntrebari = 0;
            while (nrIntrebari < restIntrebari ) {
                int attempts = 0;
                int indexAleator;

                do {
                    if (attempts >= maxAttempts) {
                        throw new RuntimeException("Nu s-au putut găsi suficiente întrebări conform criteriilor specificate.");
                    }
                    indexAleator = random.nextInt(lungimeLista);
                    attempts++;
                } while (subLista.contains(allIntrebari.get(indexAleator)));

                nrIntrebari++;
                subLista.add(allIntrebari.get(indexAleator));
            }
        }
        return subLista;
    }



}
