package main.service;

import main.entity.Warehouse1;

import java.util.List;
import java.util.Optional;

public interface Warehouse1Service {
    Optional<Warehouse1> findNoteByID(int id);
    Integer countInWarehouseByGoodID(int goodID);

    List<Warehouse1> listNotesInWarehouse1();
    List<Warehouse1> findNotesByGoodID(int goodID);
    List<Warehouse1> findNotesByCountEqual(int count);
    List<Warehouse1> findNotesByCountMoreThan(int count);
    List<Warehouse1> findNotesByCountLessThan(int count);
}
