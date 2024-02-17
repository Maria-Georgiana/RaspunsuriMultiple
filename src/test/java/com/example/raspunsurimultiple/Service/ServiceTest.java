package com.example.raspunsurimultiple.Service;

import com.example.raspunsurimultiple.Domain.Intrebare;
import com.example.raspunsurimultiple.Repository.MemoryRepository;
import com.example.raspunsurimultiple.Repository.SQLRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ServiceTest {
    private MemoryRepository<Intrebare> memoryRepository = new SQLRepository<>("testDB.sqlite");
    Service service = new Service(memoryRepository);

    @Test
    public void testAdd() {
        Intrebare intrebare1 = new Intrebare(1, "Ce este Java?", "Limbaj de programare", "O fructă tropicală", "Un animal exotic", "Limbaj de programare", 10);
        Intrebare intrebare2 = new Intrebare(6, "Cine este autorul piesei 'Romeo și Julieta'?", "William Shakespeare", "Charles Dickens", "Jane Austen", "William Shakespeare", 9);
        service.add(intrebare1);
        service.add(intrebare2);
        assertEquals(memoryRepository.size(), 2);
        assertEquals(memoryRepository.findById(6).getPunctaj(), 9);
    }




}