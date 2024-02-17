package com.example.raspunsurimultiple;

import com.example.raspunsurimultiple.Domain.Intrebare;
import com.example.raspunsurimultiple.Repository.MemoryRepository;
import com.example.raspunsurimultiple.Repository.SQLRepository;
import com.example.raspunsurimultiple.Service.Service;
import javafx.collections.FXCollections;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SQLControllerTest {

    private SQLController sqlController;
    private Service service;

    @BeforeEach
    public void setUp() {
        sqlController = new SQLController();
        MemoryRepository<Intrebare> memoryRepository = new SQLRepository<>("testFile"); // Provide a test file
        sqlController.memoryRepository = memoryRepository;
        service = new Service(memoryRepository);
        sqlController.service = service;
        sqlController.observableList = FXCollections.observableList(new ArrayList<>());

        // Other setup if needed
    }

    @Test
    public void testLoadPatients() {
        sqlController.observableList.clear();
        // Test data
        Intrebare intrebare1 = new Intrebare(1, "Intrebare 1", "A", "B", "C", "A", 10);
        Intrebare intrebare2 = new Intrebare(2, "Intrebare 2", "A", "B", "C", "B", 20);
        List<Intrebare> testData = List.of(intrebare1, intrebare2);

        try {
            sqlController.memoryRepository.add(intrebare1);
            sqlController.memoryRepository.add(intrebare2);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }


        try {
            sqlController.loadPatients();
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
        // Test

        // Verify
        assertEquals(2, sqlController.observableList.size());
        assertEquals(intrebare1, sqlController.observableList.get(0));
        assertEquals(intrebare2, sqlController.observableList.get(1));
    }

    @Test
    public void testOnAddButtonClick() {
        // Test data
        String text = "Test Intrebare";
        String raspunsA = "A";
        String raspunsB = "B";
        String raspunsC = "C";
        String raspunsCorect = "A";
        int punctaj = 10;

        sqlController.initializeProducts();

        // Set text fields in the controller
        sqlController.txtText.setText(text);
        sqlController.txtRaspunsA.setText(raspunsA);
        sqlController.txtRaspunsB.setText(raspunsB);
        sqlController.txtRaspunsC.setText(raspunsC);
        sqlController.txtRaspunsCorect.setText(raspunsCorect);
        sqlController.txtPunctaj.setText(String.valueOf(punctaj));

        // Invoke the method
        sqlController.onAddButtonClick();

        // Verify
        assertEquals(1, sqlController.observableList.size());
        assertEquals(text, sqlController.observableList.get(0).getText());
        assertEquals(raspunsA, sqlController.observableList.get(0).getRaspunsA());
        assertEquals(raspunsB, sqlController.observableList.get(0).getRaspunsB());
        assertEquals(raspunsC, sqlController.observableList.get(0).getRaspunsC());
        assertEquals(raspunsCorect, sqlController.observableList.get(0).getRaspunsCorect());
        assertEquals(punctaj, sqlController.observableList.get(0).getPunctaj());
    }

}
