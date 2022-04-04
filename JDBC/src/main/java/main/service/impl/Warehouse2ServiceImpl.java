package main.service.impl;

import main.entity.Warehouse2;
import main.repository.Warehouse2Repository;
import main.service.Warehouse2Service;

import java.util.List;
import java.util.Optional;

public class Warehouse2ServiceImpl implements Warehouse2Service {
    Warehouse2Repository repository;

    @Override
    public Optional<Warehouse2> findNoteByID(int id) {
        return repository.findById(id);
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
}
