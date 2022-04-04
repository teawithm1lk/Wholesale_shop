package main.service;

import main.entity.Warehouse2;

import java.util.List;
import java.util.Optional;

public interface Warehouse2Service {
    Optional<Warehouse2> findNoteByID(int id);
    Integer countInWarehouseByGoodID(int goodID);

    List<Warehouse2> listNotesInWarehouse2();
    List<Warehouse2> findNotesByGoodID(int goodID);
    List<Warehouse2> findNotesByCountEqual(int count);
    List<Warehouse2> findNotesByCountMoreThan(int count);
    List<Warehouse2> findNotesByCountLessThan(int count);
}
