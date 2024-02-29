package main.service.impl;

import javax.persistence.EntityNotFoundException;
import main.entity.Warehouse2;
import main.repository.Warehouse2Repository;
import main.service.Warehouse2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Warehouse2ServiceImpl implements Warehouse2Service {
    @Autowired
    Warehouse2Repository repository;

    @Override
    public Warehouse2 findNoteByID(int id) {
        Optional<Warehouse2> optNote = repository.findById(id);
        if (optNote.isPresent()) {
            return optNote.get();
        }
        else {
            throw new EntityNotFoundException("Note with that id in warehouse №2 was not found!");
        }
    }

    @Override
    public Integer countInWarehouseByGoodID(int goodID) {
        return repository.getCountByGoodID(goodID);
    }

    @Override
    public List<Warehouse2> listNotesInWarehouse2() {
        return (List<Warehouse2>) repository.findAll();
    }

    @Override
    public List<Warehouse2> findNotesByGoodID(int goodID) {
        return repository.findByGoodID(goodID);
    }

    @Override
    public List<Warehouse2> findNotesByCountEqual(int count) {
        return repository.findByGoodCountEquals(count);
    }

    @Override
    public List<Warehouse2> findNotesByCountMoreThan(int count) {
        return repository.findByGoodCountGreaterThan(count);
    }

    @Override
    public List<Warehouse2> findNotesByCountLessThan(int count) {
        return repository.findByGoodCountLessThan(count);
    }

    @Override
    public Warehouse2 addNote(Warehouse2 note) {
        return repository.save(note);
    }

    @Override
    public List<Warehouse2> addListNotes(List<Warehouse2> notes) {
        return (List<Warehouse2>) repository.saveAll(notes);
    }
}
