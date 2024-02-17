package com.example.raspunsurimultiple;

import com.example.raspunsurimultiple.Domain.Intrebare;
import com.example.raspunsurimultiple.Repository.MemoryRepository;
import com.example.raspunsurimultiple.Repository.SQLRepository;
import com.example.raspunsurimultiple.Service.Service;
import com.example.raspunsurimultiple.Service.Settings;
import com.example.raspunsurimultiple.UI.UI;

public class Main {
    public static void main(String[] args) {
        Settings settings = Settings.getInstance();
        String fileName = settings.getProduseFile();
        MemoryRepository<Intrebare> repository = new SQLRepository<>(fileName);
        Service service = new Service(repository);
        UI ui = new UI(service);
        ui.menu();
    }
}
