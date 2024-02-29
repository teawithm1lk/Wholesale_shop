package main.service.impl;

import javax.persistence.EntityNotFoundException;
import main.entity.Sales;
import main.entity.Warehouse1;
import main.repository.Warehouse1Repository;
import main.service.Warehouse1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Warehouse1ServiceImpl implements Warehouse1Service {
    @Autowired
    Warehouse1Repository repository;

    @Override
    public Warehouse1 findNoteByID(int id) {
        Optional<Warehouse1> optNote = repository.findById(id);
        if (optNote.isPresent()) {
            return optNote.get();
        }
        else {
            throw new EntityNotFoundException("Note with that id in warehouse №1 was not found!");
        }
    }

    @Override
    public Integer countInWarehouseByGoodID(int goodID) {
        return repository.getCountByGoodID(goodID);
    }

    @Override
    public List<Warehouse1> listNotesInWarehouse1() {
        return (List<Warehouse1>) repository.findAll();
    }

    @Override
    public List<Warehouse1> findNotesByGoodID(int goodID) {
        return repository.findByGoodID(goodID);
    }

    @Override
    public List<Warehouse1> findNotesByCountEqual(int count) {
        return repository.findByGoodCountEquals(count);
    }

    @Override
    public List<Warehouse1> findNotesByCountMoreThan(int count) {
        return repository.findByGoodCountGreaterThan(count);
    }

    @Override
    public List<Warehouse1> findNotesByCountLessThan(int count) {
        return repository.findByGoodCountLessThan(count);
    }

    @Override
    public Warehouse1 addNote(Warehouse1 note) {
        return repository.save(note);
    }

    @Override
    public List<Warehouse1> addListNotes(List<Warehouse1> notes) {
        return (List<Warehouse1>) repository.saveAll(notes);
    }
}
