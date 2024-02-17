package com.example.raspunsurimultiple.Repository;

import com.example.raspunsurimultiple.Domain.Intrebare;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MemoryRepository<T extends Intrebare> implements Iterable<T>{
    final protected ArrayList<T> data;

    public MemoryRepository() {
        this.data = new ArrayList<>();
    }


    public void add(T entity) {
        int entityExist = returnPosition(entity.getID());
        if (entityExist == -1) {
            data.add(entity);
        } else {
            throw new RuntimeException("Avem deja o entitate cu acest ID!");
        }
    }

    public void delete(T entity) {
        int entityExist = returnPosition(entity.getID());
        if (entityExist == -1) {
            // Nu avem aceasta entitate adaugata in lista noastra generica
            throw new RuntimeException("Nu putem sterge o entitate a carui id nu exista in lista noastra");
        } else {
            data.remove(entityExist);
        }
    }

    public void update(T entity, T newEntity) {
        int existaEntitate = returnPosition(entity.getID());

        if (existaEntitate == -1) {
            throw new RuntimeException("Nu putem modifica atributele acestei entitati, pentru ca nu este inca adaugata");
        } else {
            data.set(existaEntitate, newEntity);
        }
    }

    public T findById(int id) {
        for (T entity : data) {
            if (entity.getID() == id)
                return entity;
        }
        return null;
    }

    public int returnPosition(int id) {
        if (findById(id) != null) {
            for (int i = 0; i < data.size(); i++) {
                if (data.get(i).getID() == id)
                    return i;
            }
        }
        return -1;
    }

    public int size() {
        return data.size();
    }

    public List<T> getAll() {
        return data;
    }

    public Iterator<T> iterator() {
        return data.iterator();
    }
}
