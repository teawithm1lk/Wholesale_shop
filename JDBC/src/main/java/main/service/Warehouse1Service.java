package main.service;

import main.entity.Warehouse1;

import java.util.List;

public interface Warehouse1Service {
    Warehouse1 findNoteByID(int id);
    Integer countInWarehouseByGoodID(int goodID);

    List<Warehouse1> listNotesInWarehouse1();
    List<Warehouse1> findNotesByGoodID(int goodID);
    List<Warehouse1> findNotesByCountEqual(int count);
    List<Warehouse1> findNotesByCountMoreThan(int count);
    List<Warehouse1> findNotesByCountLessThan(int count);

    Warehouse1 addNote(Warehouse1 note);
    List<Warehouse1> addListNotes(List<Warehouse1> notes);
}
