package main.service.impl;

import main.entity.Warehouse1;
import main.repository.Warehouse1Repository;
import main.service.Warehouse1Service;

import java.util.List;
import java.util.Optional;

public class Warehouse1ServiceImpl implements Warehouse1Service {
    Warehouse1Repository repository;

    @Override
    public Optional<Warehouse1> findNoteByID(int id) {
        return repository.findById(id);
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
}
